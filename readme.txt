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

TAREA PENDIENTE - BUG CONOCIDO
-------------------------------
El ultimo campo del formulario de checkout de SauceDemo (React SPA)
no siempre retiene su valor con sendKeys en headless. 
Ver PRESENTACION-TECNICA.md en la raiz del workspace para detalles
y el prompt OpenSpec para resolverlo.
