Feature: ShoppingCart management

  #Como usuario del sitio quiero agregar productos al carrito para luego comprarlos

  Background:
    Given Acceder a la pagina "https://petstore.octoperf.com/actions/Catalog.action"
    And El catalogo esta completamente cargado


  Scenario: Agregar 1 producto al carrito desde la categoría Fish
    Given Click on superior menu "Fish"
    When Click on id product "FI-SW-01"
    And Save price on Item Id Page
    |Item ID|
    |  EST-1|
   # |  EST-2|
    And Click on Add to Cart Button
    Then Validar que se muestra el producto "FI-SW-01" en el carrito
    And Validar que el precio del producto "FI-SW-01" en el carrito es el mismo que en item page
    And Validar monto total en el carrito coincide con la suma de todos los productos


  Scenario: Add 2 different pets products to Shopping cart
    When Click on superior menu "Fish"
    And Click on id product "FI-SW-01"
    And Save price on Item Id Page
    And Click on Add to Cart Button
    Then Validar que se muestra el producto "FI-SW-01" en el carrito
    And Validar que el precio del producto "FI-SW-01" en el carrito es el mismo que en item page
    Then Back to main page
    And Click on superior menu "Dogs"
    And Click on id product "K9-BD-01"
    And Save price on Item Id Page
    And Click on Add to Cart Button
    Then Validar que se muestren los siguientes productos
      | Producto   |
      | "FI-SW-01" |
      | "K9-BD-01" |
    And Validar que el precio del producto "K9-BD-01" en el carrito es el mismo que en item page
    And Validar monto total en el carrito coincide con la suma de todos los productos


  Scenario: Validar que si no hay items en el carrito, no se pueda comprar
    When Acceder a la pagina "https://petstore.octoperf.com/actions/Cart.action?viewCart="
    Then Validar Si esta el mensaje "Your cart is empty."
    And Validar que boton "Proceed to Checkout" no está visible


  Scenario: validar valor total al comprar 2 mascotas del mismo tipo

  Scenario: validar valor total al comprar 2 mascotas diferentes

  Scenario: validar que se actualiza el precio total al modificar la cantidad de un item en el carrito con enter

  Scenario: validar que se actualiza el precio total al modificar la cantidad de un item en el carrito con el boton "update cart"

  Scenario: Eliminar un item del carrito con el boton "Remove"
    When Click on superior menu "Fish"
    And Click on id product "FI-SW-01"
    And Click on Add to Cart Button
    And Validar monto total en el carrito coincide con la suma de todos los productos
    And Click on Remove button
    Then Validar que se elimino el producto del carrito
    And Validar monto total en el carrito coincide con la suma de todos los productos


  Scenario: Verificar que el carrito este vacio inicialmente
    When Acceder a la pagina "https://petstore.octoperf.com/actions/Cart.action?viewCart="
    Then Validar Si esta el mensaje "Your cart is empty."
