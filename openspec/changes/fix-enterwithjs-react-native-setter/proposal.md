## Why

SauceDemo is built with React, which overrides the native `HTMLInputElement` value setter with its own internal implementation. When `EnterWithJS` assigns `element.value = value` directly via JavaScript, React's synthetic event system does not register the change — the field appears filled visually but remains empty from React's perspective, causing checkout form validation to fail on the postal code step.

## What Changes

- Replace the direct `element.value = value` assignment in `EnterWithJS` with a call through the native `HTMLInputElement.prototype` value setter retrieved via `Object.getOwnPropertyDescriptor`.
- Dispatch the `input` event only after setting via the native setter, so React's internal state reconciliation detects the value change.
- Remove redundant `change` event dispatch and the leading `element.value = ''` reset, which are no longer necessary once the native setter path is used.

## Capabilities

### New Capabilities
- `enter-with-js-react-native-setter`: Corrects the `EnterWithJS` Screenplay interaction to use the native `HTMLInputElement` prototype setter so that React-based input fields correctly register value changes and pass form validation.

### Modified Capabilities

## Impact

- `src/test/java/com/sofka/qa/saucedemo/interactions/EnterWithJS.java` — the JavaScript snippet inside `performAs` is the only code that changes.
- `Checkout.java` is unaffected; it already calls `EnterWithJS.theValue(postalCode, ...)` with the same API.
- No Maven dependencies or Serenity configuration changes required.
