package com.sofka.qa.saucedemo.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;

public class ClickWithJS implements Interaction {

    private final Target target;

    public ClickWithJS(Target target) {
        this.target = target;
    }

    public static ClickWithJS on(Target target) {
        return new ClickWithJS(target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", element);
    }
}
