# language: es
@e2e @compra
Característica: Compra de productos en SauceDemo
  Como usuario estándar de SauceDemo
  Quiero completar un flujo de compra de extremo a extremo
  Para recibir la confirmación de mi orden

  Antecedentes:
    Dado que "Standard" se autentica en SauceDemo con el usuario "standard_user" y la clave "secret_sauce"

  @smoke
  Escenario: Compra exitosa de dos productos hasta la confirmación
    Cuando agrega al carrito los siguientes productos:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Y visualiza el carrito
    Entonces el carrito debe contener 2 productos
    Cuando diligencia el formulario de compra con los datos:
      | nombre    | apellido | codigoPostal |
      | Jonnathan | Pena     | 110111       |
    Y finaliza la compra
    Entonces se muestra el mensaje de confirmación "Thank you for your order!"

