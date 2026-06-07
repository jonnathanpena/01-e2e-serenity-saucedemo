package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.userinterface.CartPage;
import com.sofka.qa.saucedemo.userinterface.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;

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
                Click.on(InventoryPage.CART_LINK),
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, WebElementStateMatchers.isVisible())
        );
    }
}
