Feature: Navegación del Catálogo

  Background:
    Given Acceder a la pagina "https://petstore.octoperf.com/actions/Catalog.action"
    And El catalogo esta completamente cargado

  # Pruebas del Menú Lateral
  Scenario: Verificar que el menú lateral es visible
    Then Verificar que el menú lateral está desplegado

  Scenario: Navegar a la categoría Fish desde el menú lateral
    Given Validar que el enlace lateral "FISH" es visible
    When Click en el enlace lateral "FISH"
    Then Validate page "viewCategory=&categoryId=FISH"

  Scenario: Navegar a la categoría Dogs desde el menú lateral
    Given Validar que el enlace lateral "DOGS" es visible
    When Click en el enlace lateral "DOGS"
    Then Validate page "viewCategory=&categoryId=DOGS"

  Scenario: Navegar a la categoría Reptiles desde el menú lateral
    Given Validar que el enlace lateral "REPTILES" es visible
    When Click en el enlace lateral "REPTILES"
    Then Validate page "viewCategory=&categoryId=REPTILES"

  Scenario: Navegar a la categoría Cats desde el menú lateral
    Given Validar que el enlace lateral "CATS" es visible
    When Click en el enlace lateral "CATS"
    Then Validate page "viewCategory=&categoryId=CATS"

  Scenario: Navegar a la categoría Birds desde el menú lateral
    Given Validar que el enlace lateral "BIRDS" es visible
    When Click en el enlace lateral "BIRDS"
    Then Validate page "viewCategory=&categoryId=BIRDS"

  # Pruebas de la Barra de Búsqueda
  Scenario: Verificar que la barra de búsqueda es visible
    Then Verificar que la barra de búsqueda está desplegada

  Scenario: Buscar un producto válido por palabra clave
    Given Validar que la barra de búsqueda es visible
    When Ingresar palabra clave de búsqueda "fish"
    And Click en el botón de búsqueda
    Then Validar que los resultados de búsqueda se muestran

  Scenario: Buscar un producto válido por ID
    Given Validar que la barra de búsqueda es visible
    When Ingresar palabra clave de búsqueda "EST-1"
    And Click en el botón de búsqueda
    Then Validar que los resultados contienen el producto "EST-1"

  Scenario Outline: Buscar diferentes categorías de mascotas
    Given Validar que la barra de búsqueda es visible
    When Ingresar palabra clave de búsqueda "<keyword>"
    And Click en el botón de búsqueda
    Then Validar que los resultados de búsqueda se muestran
    And Validar que los resultados contienen "<expectedText>"

    Examples:
      | keyword  | expectedText |
      | fish     | Fish         |
      | dog      | Dog          |
      | cat      | Cat          |
      | bird     | Bird         |
      | reptile  | Reptile      |

  Scenario: Buscar con palabra clave vacía
    Given Validar que la barra de búsqueda es visible
    When Ingresar palabra clave de búsqueda ""
    And Click en el botón de búsqueda
    Then Validate page "Catalog.action"

  Scenario: Buscar un producto inexistente
    Given Validar que la barra de búsqueda es visible
    When Ingresar palabra clave de búsqueda "productonexistente123"
    And Click en el botón de búsqueda
    Then Validar que se muestra mensaje de sin resultados
