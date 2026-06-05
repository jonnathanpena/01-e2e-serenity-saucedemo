package com.sofka.qa.saucedemo.tasks;

import com.sofka.qa.saucedemo.userinterface.InventoryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task Screenplay: agrega al carrito cada producto de la lista por su nombre visible.
 */
public class AddItemsToCart implements Performable {

    private final List<String> productNames;

    public AddItemsToCart(List<String> productNames) {
        this.productNames = productNames;
    }

    public static AddItemsToCart withNames(List<String> productNames) {
        return instrumented(AddItemsToCart.class, productNames);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        productNames.forEach(name ->
                actor.attemptsTo(Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(name)))
        );
    }
}
