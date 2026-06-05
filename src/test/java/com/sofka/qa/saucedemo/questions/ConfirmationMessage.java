package com.sofka.qa.saucedemo.questions;

import com.sofka.qa.saucedemo.userinterface.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Question Screenplay: mensaje de confirmacion mostrado tras finalizar la compra.
 */
public class ConfirmationMessage implements Question<String> {

    public static ConfirmationMessage displayed() {
        return new ConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CheckoutPage.COMPLETE_HEADER).answeredBy(actor).trim();
    }
}
