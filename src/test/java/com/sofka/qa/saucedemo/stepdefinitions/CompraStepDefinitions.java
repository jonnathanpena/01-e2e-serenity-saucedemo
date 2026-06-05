package com.sofka.qa.saucedemo.stepdefinitions;

import com.sofka.qa.saucedemo.questions.CartItemCount;
import com.sofka.qa.saucedemo.questions.ConfirmationMessage;
import com.sofka.qa.saucedemo.questions.LoginErrorMessage;
import com.sofka.qa.saucedemo.tasks.AddItemsToCart;
import com.sofka.qa.saucedemo.tasks.Checkout;
import com.sofka.qa.saucedemo.tasks.FinishThePurchase;
import com.sofka.qa.saucedemo.tasks.Login;
import com.sofka.qa.saucedemo.tasks.OpenTheCart;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/**
 * Step definitions (glue) que orquestan los Tasks y Questions del patron Screenplay.
 */
public class CompraStepDefinitions {

    @Dado("que {string} se autentica en SauceDemo con el usuario {string} y la clave {string}")
    public void seAutentica(String actor, String usuario, String clave) {
        theActorCalled(actor).attemptsTo(Login.withCredentials(usuario, clave));
    }

    @Dado("que {string} intenta autenticarse en SauceDemo con el usuario {string} y la clave {string}")
    public void intentaAutenticarse(String actor, String usuario, String clave) {
        theActorCalled(actor).attemptsTo(Login.withCredentials(usuario, clave));
    }

    @Cuando("agrega al carrito los siguientes productos:")
    public void agregaProductos(DataTable productos) {
        List<String> nombres = productos.asList();
        theActorInTheSpotlight().attemptsTo(AddItemsToCart.withNames(nombres));
    }

    @Y("visualiza el carrito")
    public void visualizaElCarrito() {
        theActorInTheSpotlight().attemptsTo(OpenTheCart.now());
    }

    @Entonces("el carrito debe contener {int} productos")
    public void elCarritoContiene(int cantidad) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CartItemCount.displayed()).isEqualTo(cantidad)
        );
    }

    @Cuando("diligencia el formulario de compra con los datos:")
    public void diligenciaFormulario(DataTable datos) {
        Map<String, String> fila = datos.asMaps().get(0);
        theActorInTheSpotlight().attemptsTo(
                Checkout.withInformation(
                        fila.get("nombre"),
                        fila.get("apellido"),
                        fila.get("codigoPostal"))
        );
    }

    @Y("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(FinishThePurchase.now());
    }

    @Entonces("se muestra el mensaje de confirmación {string}")
    public void seMuestraConfirmacion(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ConfirmationMessage.displayed()).containsIgnoringCase(mensajeEsperado)
        );
    }

    @Entonces("se muestra un mensaje de error de autenticación")
    public void seMuestraErrorAutenticacion() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(LoginErrorMessage.displayed()).isNotEmpty()
        );
    }
}
