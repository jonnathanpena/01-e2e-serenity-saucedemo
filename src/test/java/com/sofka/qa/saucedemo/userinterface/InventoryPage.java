package com.sofka.qa.saucedemo.userinterface;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Localizadores del catalogo de productos (inventory.html).
 */
public class InventoryPage {

    public static final Target TITLE =
            Target.the("titulo de la pagina").locatedBy(".title");

    /**
     * Boton "Add to cart" del producto cuyo nombre se inyecta con .of(nombre).
     */
    public static final Target ADD_TO_CART_BUTTON = Target.the("boton agregar al carrito de {0}")
            .locatedBy("//div[@class='inventory_item'][.//div[contains(@class,'inventory_item_name') "
                    + "and normalize-space()='{0}']]//button[contains(@class,'btn_inventory')]");

    public static final Target CART_BADGE =
            Target.the("contador del carrito").locatedBy(".shopping_cart_badge");

    public static final Target CART_LINK =
            Target.the("icono del carrito").locatedBy(".shopping_cart_link");

    private InventoryPage() {
    }
}
