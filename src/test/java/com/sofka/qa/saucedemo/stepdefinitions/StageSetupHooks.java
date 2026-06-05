package com.sofka.qa.saucedemo.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;

/**
 * Configura el escenario (Stage) de Screenplay antes de cada prueba.
 * OnlineCast provee actores con la habilidad de navegar la web.
 */
public class StageSetupHooks {

    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
