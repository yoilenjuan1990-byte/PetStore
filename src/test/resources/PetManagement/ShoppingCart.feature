Feature: ShoppingCart management

  #Como usuario del sitio quiero agregar productos al carrito para luego comprarlos

  Background:
    Given Access page "https://petstore.octoperf.com/actions/Catalog.action"
    And El catalogo esta completamente cargado


  Scenario: Agregar 1 producto al carrito desde la categoría Fish
    Given Click on superior menu "Fish"
    When Click on id product "FI-SW-01"
    And Save price on Item Id Page
    And Click on Add to Cart Button
    Then Validar que se muestra el producto "FI-SW-01" en el carrito
    And Validar que el precio del producto "FI-SW-01" en el carrito es el mismo que en item page
    And Validar monto total en el carrito coincide con la suma de todos los productos


  Scenario: Add 2 different pets products to Shopping cart
    When Click on superior menu "Fish"
    And Click on id product "FI-SW-01"
    And Save price on Item Id Page
    And Click on Add to Cart Button
    And Back to main page
    And Click on superior menu "Dogs"
    And Click on id product "K9-BD-01"
    And Save price on Item Id Page
    And Click on Add to Cart Button
    Then Validar que se muestren los siguientes productos
      | Producto   |
      | "FI-SW-01" |
      | "K9-BD-01" |

  Scenario: validar que si no hay items en el carrito, no se pueda comprar

  Scenario: validar precio de lista con el precio de lista mostrado en el carrito

  Scenario: validar precio de lista con el precio de lista en la página de descripcion de la mascota

  Scenario: validar valor total al comprar 1 mascota

  Scenario: validar valor total al comprar 2 mascotas del mismo tipo

  Scenario: validar valor total al comprar 2 mascotas diferentes

  Scenario: validar que se actualiza el precio total al modificar la cantidad de un item en el carrito con enter

  Scenario: validar que se actualiza el precio total al modificar la cantidad de un item en el carrito con el boton "update cart"

  Scenario: validar boton "Remove" para eliminar un item
  #Then validar que actualiza el precio sub total

  Scenario: Verificar que el carrito esté vacío inicialmente
    Then Validarr que el carrito debería estar vacío
