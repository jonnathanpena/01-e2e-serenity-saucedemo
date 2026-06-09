package com.sofka.qa.saucedemo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Configura el escenario (Stage) de Screenplay antes de cada prueba.
 * OnlineCast provee actores con la habilidad de navegar la web.
 */
public class StageSetupHooks {

    @Before(order = 10)
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before(order = 20)
    public void clearBrowserState() {
        WebDriver driver = Serenity.getDriver();
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                ((JavascriptExecutor) driver).executeScript(
                    "localStorage.clear(); sessionStorage.clear();"
                );
            } catch (Exception ignored) {
            }
        }
    }

    @After
    public void tidyStage() {
        OnStage.drawTheCurtain();
    }
}
