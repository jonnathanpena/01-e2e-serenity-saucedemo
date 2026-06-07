INSTRUCCIONES DE EJECUCION - E2E Serenity Screenplay (SauceDemo)
================================================================

REQUISITOS
----------
- Java 21+          (verificar: java -version)
- Maven Wrapper     (incluido: ./mvnw - no requiere instalacion global)
- Google Chrome     (instalado en el sistema)
- Conexion a internet (prueba contra www.saucedemo.com)

VERSIONES CLAVE
---------------
- Serenity BDD : 5.3.10
- Cucumber     : 7.34.2
- Selenium     : 4.41.0 (via serenity-core)
- JUnit        : 5.13.4
- Java target  : 21

EJECUCION
---------
1. Clonar el repositorio y ubicarse en la raiz del proyecto.

2. Ejecutar todas las pruebas (headless por defecto):
   ./mvnw verify

3. Ejecutar solo pruebas @smoke:
   ./mvnw verify -Dtags="@smoke"

4. Ejecutar en modo con navegador visible (headed):
   ./mvnw verify -Denvironment=headed

5. Ver reporte HTML de Serenity:
   Abrir en el navegador: target/site/serenity/index.html

NOTA SOBRE CHROME
-----------------
Selenium Manager (incluido en Selenium 4) descarga automaticamente
el chromedriver compatible. No se requiere configuracion adicional.

ESTADO DEL TEST PRINCIPAL (@smoke)
------------------------------------
El flujo completo de compra esta implementado en codigo Screenplay:
  Login → Agregar productos → Carrito → Checkout → Confirmacion

El test @smoke falla actualmente en el paso de finalizacion de compra
por un bug de React controlled input en el campo #postal-code.
Causa: EnterWithJS.java no usa el native value setter de React,
por lo que el estado interno de React no reconoce el valor ingresado
y la navegacion al overview (donde aparece #finish) no ocurre.
Ver conclusiones.txt para el detalle tecnico y la solucion recomendada.

PIPELINE CI/CD (GitHub Actions)
---------------------------------
El proyecto incluye .github/workflows/ci.yml que:
- Ejecuta ./mvnw verify en ubuntu-latest con Java 21 y Chrome headless.
- Sube el reporte Serenity como artifact incluso cuando las pruebas fallan
  (directiva "if: always()").
- Sube los resultados de Failsafe para diagnostico.

El pipeline esta rojo por el bug de React descrito arriba. El reporte
Serenity queda disponible como artifact descargable para diagnostico.
