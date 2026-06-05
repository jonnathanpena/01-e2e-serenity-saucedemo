package com.sofka.qa.saucedemo.userinterface;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Localizadores del flujo de checkout: informacion, resumen y confirmacion.
 */
public class CheckoutPage {

    // Paso 1: Your Information
    public static final Target FIRST_NAME_FIELD =
            Target.the("campo nombre").locatedBy("#first-name");
    public static final Target LAST_NAME_FIELD =
            Target.the("campo apellido").locatedBy("#last-name");
    public static final Target POSTAL_CODE_FIELD =
            Target.the("campo codigo postal").locatedBy("#postal-code");
    public static final Target CONTINUE_BUTTON =
            Target.the("boton continuar").locatedBy("#continue");

    // Paso 2: Overview
    public static final Target FINISH_BUTTON =
            Target.the("boton finalizar").locatedBy("#finish");

    // Paso 3: Complete
    public static final Target COMPLETE_HEADER =
            Target.the("encabezado de confirmacion").locatedBy(".complete-header");

    private CheckoutPage() {
    }
}
