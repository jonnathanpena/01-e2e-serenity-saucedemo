package com.sofka.qa.saucedemo.questions;

import com.sofka.qa.saucedemo.userinterface.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Question Screenplay: cantidad de productos presentes en el carrito.
 */
public class CartItemCount implements Question<Integer> {

    public static CartItemCount displayed() {
        return new CartItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return Text.ofEach(CartPage.CART_ITEMS).answeredBy(actor).size();
    }
}
