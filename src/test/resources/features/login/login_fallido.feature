# language: es
@e2e @login
Característica: Autenticación fallida en SauceDemo
  Como sistema de control de acceso
  Quiero rechazar credenciales inválidas
  Para proteger el acceso a la aplicación

  @regresion
  Escenario: Login fallido con credenciales inválidas
    Dado que "Intruso" intenta autenticarse en SauceDemo con el usuario "locked_out_user" y la clave "wrong_password"
    Entonces se muestra un mensaje de error de autenticación
