package com.sofka.qa.saucedemo.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class EnterWithJS implements Interaction {

    private final String value;
    private final Target target;

    public EnterWithJS(String value, Target target) {
        this.value = value;
        this.target = target;
    }

    public static EnterWithJS theValue(String value, Target target) {
        return new EnterWithJS(value, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        js.executeScript(
            "var element = arguments[0];" +
            "var value = arguments[1];" +
            "element.focus();" +
            "var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeSetter.call(element, value);" +
            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
            "element.dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }
}
