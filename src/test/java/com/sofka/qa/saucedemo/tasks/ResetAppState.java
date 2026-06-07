package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.userinterface.SidebarMenu;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task Screenplay: restablece el estado de la aplicacion via el menu lateral.
 */
public class ResetAppState implements Performable {

    public static ResetAppState now() {
        return instrumented(ResetAppState.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SidebarMenu.MENU_BUTTON),
                WaitUntil.the(SidebarMenu.RESET_APP_STATE_LINK, WebElementStateMatchers.isVisible()),
                Click.on(SidebarMenu.RESET_APP_STATE_LINK),
                Click.on(SidebarMenu.CLOSE_BUTTON)
        );
    }
}
