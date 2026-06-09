package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.userinterface.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Task Screenplay: abre el carrito de compras.
 */
public class OpenTheCart implements Performable {

    public static OpenTheCart now() {
        return instrumented(OpenTheCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.relativeUrl("/cart.html"),
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
