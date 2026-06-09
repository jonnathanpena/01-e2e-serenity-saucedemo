# language: es
@e2e @login
Característica: Autenticación fallida en SauceDemo
  Como sistema de control de acceso
  Quiero rechazar credenciales inválidas
  Para proteger el acceso a la aplicación

  @regresion
  Esquema del escenario: Login fallido con credenciales inválidas - <descripcion>
    Dado que "Intruso" intenta autenticarse en SauceDemo con el usuario "<usuario>" y la clave "<clave>"
    Entonces se muestra un mensaje de error de autenticación

    Ejemplos:
      | usuario          | clave          | descripcion                              |
      | locked_out_user  | secret_sauce   | usuario bloqueado con clave correcta     |
      | standard_user    | wrong_password | usuario valido con contrasena incorrecta |
