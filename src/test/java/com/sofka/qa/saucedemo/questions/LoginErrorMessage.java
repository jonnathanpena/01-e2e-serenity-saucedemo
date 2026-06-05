package com.sofka.qa.saucedemo.questions;

import com.sofka.qa.saucedemo.userinterface.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Question Screenplay: texto del mensaje de error de autenticacion.
 */
public class LoginErrorMessage implements Question<String> {

    public static LoginErrorMessage displayed() {
        return new LoginErrorMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor).trim();
    }
}
