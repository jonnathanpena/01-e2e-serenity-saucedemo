package com.sofka.qa.saucedemo.userinterface;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Localizadores de la pantalla de autenticacion de SauceDemo.
 */
public class LoginPage {

    public static final Target USERNAME_FIELD =
            Target.the("campo de usuario").locatedBy("#user-name");

    public static final Target PASSWORD_FIELD =
            Target.the("campo de contrasena").locatedBy("#password");

    public static final Target LOGIN_BUTTON =
            Target.the("boton de login").locatedBy("#login-button");

    public static final Target ERROR_MESSAGE =
            Target.the("mensaje de error").locatedBy("h3[data-test='error']");

    private LoginPage() {
    }
}
