package com.sofka.qa.saucedemo.userinterface;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Localizadores del carrito de compras (cart.html).
 */
public class CartPage {

    public static final Target CART_LIST =
            Target.the("contenedor del carrito").locatedBy(".cart_list");

    public static final Target CART_ITEMS =
            Target.the("items del carrito").locatedBy(".cart_item");

    public static final Target ITEM_NAMES =
            Target.the("nombres de los productos").locatedBy("[data-test='inventory-item-name']");

    public static final Target CHECKOUT_BUTTON =
            Target.the("boton checkout").locatedBy("#checkout");

    private CartPage() {
    }
}
