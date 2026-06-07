## ADDED Requirements

### Requirement: EnterWithJS uses native HTMLInputElement prototype setter
`EnterWithJS` SHALL assign input values by invoking the native `HTMLInputElement.prototype` value setter retrieved via `Object.getOwnPropertyDescriptor`, so that React's internal fiber state is updated before any DOM event is dispatched.

#### Scenario: Value is set via native prototype setter
- **WHEN** `EnterWithJS.theValue(value, target)` is executed on a React-managed input element
- **THEN** the JavaScript executor retrieves the native setter with `Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set` and calls it with the target element and the given value

#### Scenario: React internal state reflects the entered value
- **WHEN** the native setter is called with a non-empty string
- **THEN** React's synthetic event system registers the value change so that subsequent form validation does not treat the field as empty

### Requirement: EnterWithJS dispatches only a single input event after assignment
`EnterWithJS` SHALL dispatch exactly one `input` DOM event with `bubbles: true` after the native setter call, and SHALL NOT dispatch a `change` event or call `element.blur()`.

#### Scenario: Only input event is dispatched
- **WHEN** `EnterWithJS.theValue(value, target)` completes the native setter call
- **THEN** a single `input` event with `bubbles: true` is dispatched on the element, and no `change` event is dispatched

#### Scenario: No blur call is made
- **WHEN** `EnterWithJS.theValue(value, target)` executes
- **THEN** `element.blur()` is not invoked, allowing a subsequent Task step to trigger form validation naturally

### Requirement: EnterWithJS removes the preliminary value reset
`EnterWithJS` SHALL NOT perform an `element.value = ''` reset before setting the target value, because the native setter replaces the value atomically.

#### Scenario: No empty-string reset occurs
- **WHEN** `EnterWithJS.theValue(value, target)` is called on a field that already has content
- **THEN** the field value transitions directly to the new value without an intermediate empty-string state visible to the React component

### Requirement: EnterWithJS public API is unchanged
The static factory method `EnterWithJS.theValue(String value, Target target)` SHALL retain its existing signature so that no caller — including `Checkout.java` — requires modification.

#### Scenario: Caller invokes theValue with same arguments
- **WHEN** `Checkout.java` calls `EnterWithJS.theValue(postalCode, POSTAL_CODE_FIELD)`
- **THEN** the interaction executes without any change to the call site

### Requirement: Checkout postal code step passes form validation after fix
The checkout form's postal code field SHALL be accepted by SauceDemo's React validation after `EnterWithJS` sets its value using the native setter path.

#### Scenario: Postal code field passes React validation
- **WHEN** the actor performs `EnterWithJS.theValue("12345", POSTAL_CODE_FIELD)` followed by clicking the Continue button
- **THEN** the checkout overview page is displayed and no validation error message appears for the postal code field
