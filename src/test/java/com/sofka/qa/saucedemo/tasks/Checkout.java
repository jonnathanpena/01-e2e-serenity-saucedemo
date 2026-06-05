package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.userinterface.CartPage;
import com.sofka.qa.saucedemo.userinterface.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task Screenplay: parte del carrito, diligencia el formulario de compra y avanza al resumen.
 */
public class Checkout implements Performable {

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public Checkout(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public static Checkout withInformation(String firstName, String lastName, String postalCode) {
        return instrumented(Checkout.class, firstName, lastName, postalCode);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPage.CHECKOUT_BUTTON),
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME_FIELD).thenHit(Keys.TAB),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME_FIELD).thenHit(Keys.TAB),
                Enter.theValue(postalCode).into(CheckoutPage.POSTAL_CODE_FIELD).thenHit(Keys.TAB),
                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );
    }
}
