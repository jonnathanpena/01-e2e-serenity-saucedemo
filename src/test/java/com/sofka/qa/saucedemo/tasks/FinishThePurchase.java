package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.interactions.ClickWithJS;
import com.sofka.qa.saucedemo.userinterface.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task Screenplay: confirma la compra desde la pantalla de resumen.
 */
public class FinishThePurchase implements Performable {

    public static FinishThePurchase now() {
        return instrumented(FinishThePurchase.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.FINISH_BUTTON, WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
                ClickWithJS.on(CheckoutPage.FINISH_BUTTON)
        );
    }
}
