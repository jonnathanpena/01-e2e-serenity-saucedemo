package com.sofka.qa.saucedemo.userinterface;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Localizadores del menu lateral (hamburger menu).
 */
public class SidebarMenu {

    public static final Target MENU_BUTTON =
            Target.the("boton hamburger").locatedBy("#react-burger-menu-btn");

    public static final Target RESET_APP_STATE_LINK =
            Target.the("link reset app state").locatedBy("#reset_sidebar_link");

    public static final Target CLOSE_BUTTON =
            Target.the("boton cerrar menu").locatedBy("#react-burger-cross-btn");

    private SidebarMenu() {
    }
}
