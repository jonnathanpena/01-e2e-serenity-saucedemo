## 1. Fix JavaScript in EnterWithJS.performAs()

- [x] 1.1 In `src/test/java/com/sofka/qa/saucedemo/interactions/EnterWithJS.java`, replace `element.value = '';` and `element.value = value;` with the native setter call: `var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set; nativeSetter.call(element, value);`
- [x] 1.2 Remove the `change` event construction and `element.dispatchEvent(changeEvent);` lines from the script string
- [x] 1.3 Remove the `element.blur();` call from the script string
- [x] 1.4 Verify the final script retains `element.click()`, `element.focus()`, the native setter call, and a single `input` event dispatch with `bubbles: true`

## 2. Verification

- [x] 2.1 Run `./mvnw clean verify -Dwebdriver.driver=chrome` and confirm the checkout E2E scenario reaches the "Thank you for your order!" confirmation page without a postal code validation error
- [x] 2.2 Open `target/site/serenity/index.html` and confirm the checkout scenario is marked as passing in the Serenity living documentation report
