## Context

`EnterWithJS` is a Screenplay `Interaction` that fills form inputs via `JavascriptExecutor` when standard WebDriver `sendKeys` is unreliable. SauceDemo is a React SPA. React replaces the native `HTMLInputElement` value setter with its own instrumented version that tracks state in the component's internal fiber. Assigning `element.value = value` directly in JavaScript bypasses that instrumented setter, so React never updates its internal state — the field looks filled to the browser but is empty from React's perspective, causing form validation to reject the postal code on the checkout step.

Current implementation (`performAs`):
1. `element.value = ''` — reset (unnecessary with native setter path)
2. `element.value = value` — bypasses React's tracked setter
3. `new Event('input', ...)` + `new Event('change', ...)` — dispatches events, but React's reconciler ignores them because its state was never updated
4. `element.blur()` — triggers validation, which fails because React state is still empty

## Goals / Non-Goals

**Goals:**
- Fix value assignment in `EnterWithJS` so React's internal fiber state reflects the entered value before form validation runs.
- Keep the public API (`EnterWithJS.theValue(value, target)`) unchanged — no callers need to be modified.
- Require only a single-file change with no new dependencies.

**Non-Goals:**
- Rewriting `EnterWithJS` to use `sendKeys` — the interaction exists precisely to bypass WebDriver input limitations.
- Handling non-React frameworks — the fix uses a pattern that is safe for both React and non-React inputs.
- Modifying `Checkout.java` or any other Task/Step class.

## Decisions

### Decision 1 — Use `Object.getOwnPropertyDescriptor` to retrieve the native setter

**Choice**: Replace `element.value = value` with:
```js
var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;
nativeSetter.call(element, value);
```

**Rationale**: React overrides the instance-level value setter but leaves the prototype-level native setter on `HTMLInputElement.prototype` intact. Retrieving the descriptor from the prototype and calling it directly forces React's synthetic event tracking to fire, updating the internal fiber state before the `input` event is dispatched.

**Alternatives considered**:
- `Object.getOwnPropertyDescriptor(element, 'value').set` — operates on the instance, which is already overridden by React; does not fix the issue.
- Simulating `sendKeys` via `element.dispatchEvent(new KeyboardEvent(...))` — unreliable across browsers and React versions; more code for the same outcome.
- `ReactDOM.unstable_batchedUpdates` / calling React internal APIs — tightly couples test code to React internals; breaks on React upgrades.

### Decision 2 — Dispatch only `input` event; remove `change` and the reset

**Choice**: After the native setter call, dispatch one `input` event with `bubbles: true`. Remove `element.value = ''`, the redundant `change` event, and `element.blur()`.

**Rationale**: React's `onChange` synthetic event is driven by the `input` DOM event (React 16+). A `change` event is redundant and can trigger duplicate handler calls. The `element.value = ''` reset is unnecessary once the native setter is used, because the setter replaces the value atomically. `element.blur()` caused the validation to run before React had a chance to re-render; removing it lets the subsequent Task step (`Click.on(CONTINUE_BUTTON)`) trigger validation naturally.

## Risks / Trade-offs

- **React version coupling** → `HTMLInputElement.prototype` value setter retrieval via `Object.getOwnPropertyDescriptor` has been stable since React 16 and is the approach recommended by React Testing Library internals. Risk is low for SauceDemo which targets a fixed React version.
- **Other SPA frameworks (Vue, Angular)** → The native setter approach is safe for non-React inputs too: it sets value and fires `input`, which all frameworks handle. No regression risk.
- **Headless Chrome vs headed Chrome** → `JavascriptExecutor` behavior is identical in both modes; no environment-specific risk.
