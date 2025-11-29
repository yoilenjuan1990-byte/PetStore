# PetStore - Framework de Automatización de Pruebas

Proyecto de automatización completa para el sitio JPetStore Demo utilizando Selenium WebDriver, Cucumber BDD y Java.

## 📋 Tabla de Contenidos
- [Descripción General](#descripción-general)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Arquitectura del Framework](#arquitectura-del-framework)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [Clases y Funciones Principales](#clases-y-funciones-principales)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [Generación de Reportes](#generación-de-reportes)

## 🎯 Descripción General

Framework de automatización de pruebas end-to-end para JPetStore, implementando el patrón Page Object Model (POM) con Cucumber para escribir escenarios de prueba en lenguaje natural (BDD).

### Sitio Web Bajo Prueba
- **URL**: https://petstore.octoperf.com/
- **Tipo**: E-commerce de mascotas
- **Funcionalidades Probadas**: Login, Registro, Navegación de Catálogo, Carrito de Compras, Búsqueda

## 🛠️ Tecnologías Utilizadas

- **Java 8+**: Lenguaje de programación principal
- **Selenium WebDriver 4.20.0**: Automatización de navegadores web
- **Cucumber 7.18.0**: Framework BDD para escribir pruebas en Gherkin
- **TestNG 7.10.2**: Framework de ejecución de pruebas
- **Gradle**: Gestor de dependencias y compilación
- **Allure 2.29.0**: Generación de reportes visuales
- **OpenCSV 5.7.1**: Manejo de archivos CSV para datos de prueba
- **IntelliJ IDEA**: IDE recomendado

## 📁 Estructura del Proyecto

```
PetStore/
├── src/
│   ├── main/java/org/example/
│   │   └── Main.java
│   └── test/java/
│       ├── Configuration/
│       │   └── Configurations.java          # Configuración base de WebDriver
│       ├── Interface/
│       │   ├── PetNavigationItemIdManager.java
│       │   ├── PetNavigationProdIdManager.java
│       │   ├── PricePetManager.java
│       │   ├── SearchManager.java
│       │   └── SidebarNavigationManager.java
│       ├── Pages/                          # Page Object Model
│       │   ├── CatalogNavigationPage.java
│       │   ├── LoginPage.java
│       │   ├── RegisterPage.java
│       │   ├── ShoppingCartPage.java
│       │   ├── PageBoard.java
│       │   ├── BirdsItemIdPage.java
│       │   ├── BirdsProductIdPage.java
│       │   ├── CatsItemIdPage.java
│       │   ├── CatsProductIdPage.java
│       │   ├── DogsItemIdPage.java
│       │   ├── DogsProductIdPage.java
│       │   ├── FishItemIdPage.java
│       │   ├── FishProductIdPage.java
│       │   ├── ReptilesItemIdPage.java
│       │   └── ReptilesProductIdPage.java
│       ├── Steps/                          # Cucumber Step Definitions
│       │   ├── CatalogNavigationFeatureSteps.java
│       │   ├── LoginFeatureSteps.java
│       │   ├── PetFeatureSteps.java
│       │   ├── RegisterFeatureSteps.java
│       │   └── ShoppingCartFeatureSteps.java
│       └── Utils/                          # Utilidades
│           ├── CSVDataReader.java
│           └── TestDataGenerator.java
├── src/test/resources/
│   ├── Login/
│   │   └── login.feature
│   ├── PetManagement/
│   │   ├── CatalogNavigation.feature
│   │   ├── PetSelect.feature
│   │   └── ShoppingCart.feature
│   ├── Register/
│   │   └── Register.feature
│   ├── TestData/
│   │   └── RegisterTestData.csv
│   └── GeneratedTestData/                 # Datos generados dinámicamente
├── build.gradle
└── README.md
```

## 🏗️ Arquitectura del Framework

### Patrón de Diseño: Page Object Model (POM)

**Ventajas**:
- Separación de la lógica de prueba y los elementos de la UI
- Reutilización de código
- Fácil mantenimiento
- Código más legible y escalable

### Capas del Framework

1. **Capa de Configuración** (`Configuration/`)
   - Inicialización de WebDriver
   - Métodos comunes reutilizables
   - Manejo de esperas y sincronización

2. **Capa de Páginas** (`Pages/`)
   - Representación de cada página web
   - Localizadores de elementos
   - Métodos de interacción específicos

3. **Capa de Interfaces** (`Interface/`)
   - Contratos para funcionalidades comunes
   - Polimorfismo para diferentes categorías de productos

4. **Capa de Pasos** (`Steps/`)
   - Definiciones de pasos de Cucumber
   - Vinculación de Gherkin con código Java

5. **Capa de Utilidades** (`Utils/`)
   - Generación de datos de prueba
   - Lectura de archivos CSV
   - Funciones auxiliares

## ⚡ Funcionalidades Implementadas

### 1. Gestión de Autenticación
- ✅ Login con credenciales válidas
- ✅ Login con credenciales inválidas
- ✅ Validación de mensajes de error
- ✅ Registro de nuevos usuarios
- ✅ Validación de formularios de registro
- ✅ Data-driven testing con CSV
- ✅ Generación dinámica de usuarios únicos

### 2. Navegación de Catálogo
- ✅ Navegación por menú superior (Fish, Dogs, Cats, Birds, Reptiles)
- ✅ Navegación por sidebar lateral
- ✅ Búsqueda de productos por palabra clave
- ✅ Validación de resultados de búsqueda
- ✅ Validación de mensajes "sin resultados"

### 3. Selección de Productos
- ✅ Selección de categorías de mascotas
- ✅ Selección de Product ID
- ✅ Selección de Item ID
- ✅ Captura de precios de productos
- ✅ Validación de información de productos

### 4. Carrito de Compras
- ✅ Agregar productos al carrito
- ✅ Validar productos en el carrito
- ✅ Validar precios en el carrito
- ✅ Remover productos del carrito
- ✅ Validar total del carrito
- ✅ Validar carrito vacío

### 5. Generación de Datos de Prueba
- ✅ Generación de usernames únicos con timestamp
- ✅ Generación de contraseñas seguras personalizables
- ✅ Generación de datos completos de usuario (nombre, email, dirección, etc.)
- ✅ Exportación de datos a CSV, JSON y TXT
- ✅ Lectura de datos desde archivos CSV

## 📚 Clases y Funciones Principales

### Configuration Package

#### `Configurations.java`
**Propósito**: Clase base que contiene la configuración de WebDriver y métodos comunes reutilizables.

**Funciones principales**:

```java
// Inicialización y configuración
protected static WebDriver driver              // WebDriver estático compartido
private static WebDriverWait wait              // Espera explícita de 10 segundos

// Métodos de escritura
void writeText(By locator, String text)        // Escribe texto en un campo (con limpieza previa)
void enterText(By locator, String text)        // Alias de writeText

// Métodos de espera
WebElement waitForElement(By locator)          // Espera hasta que elemento sea visible (10s)
WebElement waitForElementTime(By locator)      // Espera hasta que elemento sea visible (30s)
WebElement waitForClickableElement(By locator) // Espera hasta que elemento sea clickeable

// Métodos de interacción con elementos
void clickElement(By locator)                  // Click en elemento después de esperar
void pressEnterElement(By locator)             // Presiona ENTER en un elemento
void doubleClickElement(By locator)            // Doble click en elemento
void jsClick(By locator)                       // Click por JavaScript (cuando falla click normal)
void hoverOverElement(By locator)              // Hover sobre elemento

// Métodos de obtención de información
String getElementText(By locator)              // Obtiene texto de un elemento
String getNombreImagen(By locator)             // Obtiene nombre de imagen (sin prefijo "sm_" y extensión)
String getCurrentUrlResult()                   // Obtiene URL actual
boolean currentURLContains(String fragment)    // Verifica si URL contiene fragmento

// Métodos de validación
boolean isElementPresent(By locator)           // Verifica si elemento está presente en DOM
Boolean isElementNotVisible(By locator)        // Verifica si elemento NO es visible

// Métodos de scroll
void scrollToElement(By locator)               // Scroll hasta elemento específico
void scrollToBottom()                          // Scroll hasta el final de la página

// Métodos de alertas
void acceptAlert()                             // Acepta alerta JavaScript
void dismissAlert()                            // Rechaza alerta JavaScript
String getAlertText()                          // Obtiene texto de alerta

// Métodos de ventanas y frames
void switchToFrame(By locator)                 // Cambia a un frame
void switchToDefaultContent()                  // Vuelve al contenido principal
void switchToNewWindow()                       // Cambia a nueva ventana
void switchToNewTab()                          // Cambia a nueva pestaña
void closeCurrentWindowAndSwitchToOriginal()   // Cierra ventana actual y vuelve a original

// Métodos de acciones avanzadas
void dragAndDrop(By source, By target)         // Arrastra y suelta elementos
void pressKey(By locator, Keys key)            // Envía teclas especiales (ENTER, ESC, etc.)
void selectDropdownOption(By dropdown, String) // Selecciona opción de dropdown
void selectDate(By datePicker, String date)    // Selecciona fecha en datepicker

// Métodos de navegación
void accederAPagina(String url)                // Navega a una URL específica
String obtenerTitulo(By locator)               // Obtiene título de página
```

### Pages Package

#### `LoginPage.java`
**Propósito**: Maneja todas las interacciones con la página de login.

**Funciones principales**:
```java
void enterUsername(String username)            // Ingresa nombre de usuario
void enterPassword(String password)            // Ingresa contraseña
void clickLoginButton()                        // Hace click en botón Login
boolean isLoginButtonClickable()               // Verifica si botón login es clickeable
boolean verifyCurrentUrl(String expectedUrl)   // Verifica URL actual
String getErrorMessage()                       // Obtiene mensaje de error
boolean isErrorMessageVisible(String text)     // Verifica si error contiene texto específico
boolean hasErrorMessage()                      // Verifica si hay mensaje de error presente
```

#### `RegisterPage.java`
**Propósito**: Maneja el proceso completo de registro de usuarios.

**Funciones principales**:
```java
// Navegación
void clickRegisterNowLink()                    // Click en "Register Now!"

// Ingreso de datos individuales
void enterUserId(String userId)
void enterPassword(String password)
void enterRepeatPassword(String repeatPassword)
void enterFirstName(String firstName)
void enterLastName(String lastName)
void enterEmail(String email)
void enterPhone(String phone)
void enterAddress1(String address1)
void enterAddress2(String address2)
void enterCity(String city)
void enterState(String state)
void enterZip(String zip)
void enterCountry(String country)

// Acciones
void clickSaveAccountButton()                  // Click en "Save Account Information"

// Validaciones
boolean isSuccessMessageDisplayed()            // Verifica mensaje de éxito
String getSuccessMessage()                     // Obtiene mensaje de éxito
boolean isErrorMessageDisplayed()              // Verifica mensaje de error
String getErrorMessage()                       // Obtiene mensaje de error
boolean isErrorMessageVisible(String text)     // Verifica error específico
boolean verifyCurrentUrl(String expectedUrl)   // Verifica URL

// Método auxiliar
void fillRegistrationForm(...)                 // Llena formulario completo de registro
```

#### `CatalogNavigationPage.java`
**Propósito**: Maneja la navegación del catálogo mediante sidebar y búsqueda.

**Funciones principales**:
```java
// Métodos de Sidebar
boolean isSidebarMenuDisplayed()               // Verifica si sidebar está visible
boolean isSidebarLinkVisible(String category)  // Verifica si enlace de categoría es visible
void clickSidebarLink(String categoryName)     // Click en enlace del sidebar
List<WebElement> getAllSidebarLinks()          // Obtiene todos los enlaces del sidebar

// Métodos de Búsqueda
boolean isSearchBarDisplayed()                 // Verifica si barra de búsqueda está visible
void enterSearchKeyword(String keyword)        // Ingresa palabra clave en búsqueda
void clickSearchButton()                       // Click en botón de búsqueda

// Métodos de Resultados
boolean areSearchResultsDisplayed()            // Verifica si hay resultados
boolean searchResultsContainProduct(String id) // Verifica si producto está en resultados
boolean searchResultsContainText(String text)  // Verifica si texto está en resultados
boolean isNoResultsMessageDisplayed()          // Verifica mensaje "sin resultados"
```

#### `ShoppingCartPage.java`
**Propósito**: Maneja todas las operaciones del carrito de compras.

**Funciones principales**:
```java
// Validaciones de productos
void validateProductInCart(String productID)         // Valida que producto esté en carrito
void validateProductNotInCart(String productID)      // Valida que producto NO esté en carrito

// Validaciones de totales
void validateCartTotalGreaterThanZero()              // Valida total > 0
void validateCartIsEmpty()                           // Valida carrito vacío
String getCartTotal()                                // Obtiene total del carrito
void validateCartTotalMatches(String id, double p)   // Valida precio coincida con item
void calcularValorTotalActual(double sumaEsperada)   // Calcula total actual

// Validaciones de cantidad
void validateNumberOfItemsInCart(int count)          // Valida cantidad de items

// Acciones de remoción
void clickAllRemoveButtons()                         // Remueve todos los productos
void clickRemoveButtonByItemId(String itemId)        // Remueve producto específico por ID

// Métodos privados auxiliares
private double getProductPriceFromCart(String id)    // Obtiene precio de producto del carrito
```

#### `PageBoard.java`
**Propósito**: Clase base para navegación general y validación de menús.

**Funciones principales**:
```java
// Navegación general
void accessPageURL(String url)                 // Accede a URL específica
void goToMainPage()                            // Vuelve a página principal

// Navegación por menú de mascotas
void getMenuName(String petType)               // Obtiene y valida nombre de menú por tipo
void ClickOnMenuOption(String petName)         // Click en opción de menú (Fish/Dogs/etc.)

// Validaciones generales
void validateURL(String currentUrl)            // Valida URL actual
void verifyCatalogLoaded()                     // Verifica que catálogo cargó completamente

// Métodos de elementos de menú
boolean verifyElementoVisible(String element)  // Verifica elemento visible en menú
String obtenerElementoVisible(String element)  // Obtiene texto de elemento de menú
boolean isButtonClickable(String buttonText)   // Verifica si botón es clickeable
void clickButton(String buttonName)            // Click en botón/enlace del menú
String currentURL()                            // Obtiene URL actual
```

### Utils Package

#### `TestDataGenerator.java`
**Propósito**: Genera datos de prueba aleatorios y únicos para evitar conflictos.

**Funciones principales**:

```java
// Generación de timestamps y usernames
String generateTimestamp()                           // Genera timestamp: yyyyMMddHHmmss
String generateUniqueUsername()                      // Genera username único: user_20231015143022
String generateUniqueUsername(String prefix)         // Username con prefijo personalizado

// Generación de emails
String generateUniqueEmail()                         // Email único: user_timestamp@testmail.com
String generateUniqueEmail(String domain)            // Email con dominio personalizado

// Generación de datos personales
String generateRandomFirstName()                     // Nombre aleatorio de lista predefinida
String generateRandomLastName()                      // Apellido aleatorio de lista predefinida
String generateRandomFullName()                      // Nombre completo aleatorio
String generateRandomPhone()                         // Teléfono: 555-XXXX
String generateRandomAddress()                       // Dirección: [número] [calle]
String generateRandomCity()                          // Ciudad de lista predefinida
String generateRandomState()                         // Estado (abreviatura) de lista
String generateRandomZipCode()                       // Código postal: XXXXX (5 dígitos)

// Generación de contraseñas
String generateSecurePassword()                      // Password segura (15 chars, mixed)
String generateSecurePassword(int length,            // Password personalizada con specs
    boolean uppercase, boolean lowercase, 
    boolean specialChars, boolean numbers)
String generateWeakPassword()                        // Password débil para pruebas negativas

// Generación de usernames personalizados
String generateCustomUsername(int min, int max,      // Username con longitud y chars específicos
    boolean allowSpecialChars)

// Generación de datos completos
UserTestData generateCompleteUserData()              // Genera objeto completo de usuario

// Métodos de guardado
void saveUserToCSV(UserTestData, boolean append,     // Guarda usuario en CSV
    String filename)
void saveUsersToCSV(List<UserTestData>, String)      // Guarda múltiples usuarios en CSV
void saveUserToJSON(UserTestData, boolean append,    // Guarda usuario en JSON
    String filename)
void saveUserToTextFile(UserTestData, boolean,       // Guarda usuario en TXT legible
    String filename)
void saveUserToAllFormats(UserTestData, String)      // Guarda en todos los formatos
UserTestData generateAndSaveUser(String basename)    // Genera y guarda automáticamente

// Clase interna UserTestData
class UserTestData {
    String username, password, firstName, lastName
    String email, phone, address, city, state, zip, country
    
    String toCSV()                                   // Convierte a formato CSV
    String toJSON()                                  // Convierte a formato JSON
    String toReadableText()                          // Convierte a texto legible
}
```

#### `CSVDataReader.java`
**Propósito**: Utilidad para leer y manipular datos desde archivos CSV.

**Funciones principales**:

```java
// Lectura de CSV
List<Map<String,String>> readCSV(String filePath)           // Lee CSV desde ruta absoluta
List<Map<String,String>> readCSVFromResources(String path)  // Lee CSV desde resources

// Obtención de filas específicas
Map<String,String> getRowByIndex(String path, int index)    // Obtiene fila por índice
List<Map<String,String>> filterRows(String path,            // Filtra filas por criterio
    String columnName, String value)

// Conversión de datos
UserTestData convertToUserData(Map<String,String> rowData)  // Convierte mapa a UserTestData

// Generación de usernames únicos
boolean generateRandomUsernames(String filePath,            // Genera usernames únicos en CSV
    String usernameColumn)
boolean generateRandomUsernames(String filePath)            // Sobrecarga con columna por defecto

// Método privado auxiliar
private String generarUsernameAleatorio(Random random)      // Genera username: user_[8chars]
```

### Interfaces Package

#### `PetNavigationItemIdManager.java`
```java
void seleccionarItemId()                       // Selecciona Item ID específico
void clickOnAddToCart()                        // Click en botón "Add to Cart"
void obtenerItemId()                           // Obtiene Item ID actual
```

#### `PetNavigationProdIdManager.java`
```java
void seleccionarProductId(String petProdId)    // Selecciona Product ID
void obtenerProductId()                        // Obtiene Product ID actual
void obtenerPetName()                          // Obtiene nombre de mascota
```

#### `PricePetManager.java`
```java
void guardarPrecio(String itemId)              // Guarda precio de producto
double getPrecioGuardado()                     // Obtiene precio guardado
```

#### `SearchManager.java`
```java
void performSearch(String keyword)                      // Realiza búsqueda
boolean searchResultsContainProduct(String productId)   // Verifica producto en resultados
boolean searchResultsContainText(String text)           // Verifica texto en resultados
boolean hasSearchResults()                              // Verifica si hay resultados
boolean hasNoResultsMessage()                           // Verifica mensaje "sin resultados"
void clearSearchField()                                 // Limpia campo de búsqueda
String getSearchKeyword()                               // Obtiene keyword actual
```

#### `SidebarNavigationManager.java`
```java
boolean isCategoryLinkVisible(String categoryName)  // Verifica enlace visible
void navigateToCategory(String categoryName)        // Navega a categoría
String getCurrentCategoryUrl()                      // Obtiene URL de categoría actual
boolean isSidebarLoaded()                           // Verifica si sidebar cargó
```

### Steps Package

#### `LoginFeatureSteps.java`
**Propósito**: Define los pasos de Cucumber para escenarios de login.

**Anotaciones Cucumber principales**:
```java
@Given("Access to the page {string}")
@And("Verify that {string} is visible")
@And("The {string} button is clickable")
@And("I click in the {string} button")
@And("Verify that the currentURL {string}")
@Given("Access to the user {string}")
@And("Access to the password {string}")
@And("Verify that the currentURL after login contains {string}")
@Then("Verify that the error message {string} is visible")
```

#### `RegisterFeatureSteps.java`
**Propósito**: Define pasos para registro de usuarios (manual, CSV y generación dinámica).

**Anotaciones Cucumber principales**:
```java
// Navegación y acciones
@And("I click on the {string} link")
@And("I click the {string} button")

// Ingreso de datos individuales
@Given("I enter the user ID {string}")
@And("I enter the password {string}")
@And("I enter the repeat password {string}")
// ... (más campos de formulario)

// Validaciones
@Then("Verify successful registration message is displayed")
@And("Verify that the currentURL after register contains {string}")
@Then("Verify that the error message register {string} is visible")

// Data-Driven con generación dinámica
@Given("I generate random user data")
@Given("I generate random user data with specifications:")
@And("I fill the registration form with random data")

// Data-Driven con CSV
@Given("I read user data from CSV row {int}")
@And("I fill the registration form with CSV data")

// Guardado de datos generados
@And("I save the generated user data to CSV file {string}")
@And("I save the generated user data to JSON file {string}")
@And("I save the generated user data to text file {string}")
@And("I save the generated user data to all formats with base name {string}")

// Flujo de integración
@When("I click the {string} link")
@And("I login with the registered credentials")
```

#### `PetFeatureSteps.java`
**Propósito**: Define pasos para navegación y selección de productos.

**Anotaciones Cucumber principales**:
```java
@Given("Acceder a la pagina {string}")
@Given("Validate that there is a superior menu for {string}")
@Given("Click on superior menu {string}")
@Then("Validate page {string}")
@When("Click on id product {string}")
@And("El catalogo esta completamente cargado")
@And("Click on Add to Cart Button")
@And("Back to main page")
```

#### `ShoppingCartFeatureSteps.java`
**Propósito**: Define pasos para operaciones del carrito de compras.

## 🚀 Instalación y Configuración

### Prerrequisitos
1. **Java JDK 8 o superior**
   ```bash
   java -version
   ```

2. **Gradle** (incluido en el proyecto con Gradle Wrapper)
   ```bash
   ./gradlew --version
   ```

3. **ChromeDriver** (se descarga automáticamente con Selenium Manager)

### Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd PetStore
   ```

2. **Compilar el proyecto**
   ```bash
   ./gradlew clean build
   ```

3. **Descargar dependencias**
   ```bash
   ./gradlew build --refresh-dependencies
   ```

## ▶️ Ejecución de Pruebas

### Opción 1: Ejecutar todas las pruebas con Allure (Recomendado)
```bash
# Windows PowerShell
.\gradlew testWithAllure

# Windows CMD
gradlew.bat testWithAllure

# Linux/Mac
./gradlew testWithAllure
```

Este comando:
- ✅ Limpia resultados anteriores
- ✅ Ejecuta todos los tests de Cucumber
- ✅ Genera resultados en `build/allure-results`
- ✅ Muestra resumen en consola

### Opción 2: Ejecutar tests normales
```bash
./gradlew test
```

### Opción 3: Ejecutar tests con Tags específicos

Edita `src/test/java/runners/CucumberTestRunner.java` y modifica la línea de tags:

```java
tags = "@smoke"  // Solo ejecutar tests con @smoke
tags = "@login or @register"  // Ejecutar tests de login o register
tags = "not @wip"  // Excluir tests marcados como @wip
```

Luego ejecuta:
```bash
.\gradlew testWithAllure
```

### Ejecutar desde IntelliJ IDEA
1. Abrir el proyecto en IntelliJ
2. Navegar a `src/test/java/runners/CucumberTestRunner.java`
3. Click derecho → Run 'CucumberTestRunner'

## 📊 Generación de Reportes con Allure

### 🎉 ¿Por qué Allure Report?

Allure genera reportes HTML **interactivos y navegables** con:
- ✅ Dashboard con métricas (pass/fail rate, duración, tendencias)
- ✅ Navegación por scenarios y features
- ✅ Screenshots automáticos en fallos
- ✅ Visualización de cada step de Gherkin
- ✅ Gráficos y estadísticas
- ✅ Categorización de errores
- ✅ Histórico de ejecuciones

### Quick Start - Ver Reporte

```bash
# Paso 1: Ejecutar tests
.\gradlew testWithAllure

# Paso 2: Ver reporte en navegador (se abre automáticamente)
.\gradlew allureServe
```

**👉 El reporte se abrirá automáticamente en tu navegador en http://localhost:XXXXX**

### Comandos de Allure

#### 1. Ver Reporte Interactivo (Modo Servidor)
```bash
.\gradlew allureServe
```
- Genera el reporte HTML completo
- Inicia un servidor local
- Abre automáticamente en navegador
- **Recomendado para desarrollo**

#### 2. Generar Reporte Estático (Para Compartir)
```bash
.\gradlew allureReport
```
- Genera HTML en: `build/reports/allure-report/index.html`
- Puedes abrir `index.html` directamente
- Ideal para enviar por email o compartir

#### 3. Limpiar Resultados Anteriores
```bash
.\gradlew cleanAllureResults
```

#### 4. Full Cycle - Todo en uno
```bash
.\gradlew clean testWithAllure allureServe
```
Limpia, ejecuta tests y abre el reporte.

### Navegación en el Reporte

Cuando abras el reporte de Allure verás:

#### 🏠 Overview (Vista Principal)
- Total de tests ejecutados
- Gráficos de pass/fail rate
- Duración total de ejecución
- Tendencias (si hay histórico)

#### 📂 Suites
- Tests organizados por archivos `.feature`
- Navegación jerárquica
- Estado de cada scenario

#### 📋 Behaviors
- Vista BDD (Features > Scenarios > Steps)
- Estructura de Gherkin
- Estado de cada step

#### 📈 Graphs
- Status Chart (distribución de resultados)
- Severity Chart (prioridades)
- Duration Chart (tests más lentos)

#### ⏱️ Timeline
- Vista cronológica de ejecución
- Identifica tests en paralelo
- Detecta cuellos de botella

#### 🗂️ Categories
- Clasificación automática de errores:
  - Product Defects (errores del producto)
  - Test Defects (errores de automatización)
  - Timeout Issues
  - Browser Issues
  - Environment Issues

### Características Avanzadas

#### Screenshots Automáticos 📸
**Ya está configurado** - Cuando un test falla:
1. Se captura screenshot automáticamente
2. Se adjunta al reporte de Allure
3. Aparece en la sección "Attachments" del test fallido

#### Filtros y Búsqueda 🔍
En el reporte puedes:
- Buscar por nombre de test
- Filtrar por status (Passed/Failed/Broken/Skipped)
- Filtrar por suite o feature
- Filtrar por categoría de error
- Ordenar por duración

### Documentación Completa

Para instrucciones detalladas, troubleshooting y ejemplos avanzados, consulta:

📖 **[ALLURE_GUIDE.md](./ALLURE_GUIDE.md)** - Guía completa de Allure Report

### Ubicación de reportes
- **Resultados Allure**: `build/allure-results/`
- **Reporte HTML Allure**: `build/reports/allure-report/`
- **Test Results TestNG**: `build/test-results/`
- **Cucumber Reports**: `target/cucumber-reports.html`

## 📝 Escribir Nuevas Pruebas

### 1. Crear archivo Feature (Gherkin)

Archivo: `src/test/resources/MiFeature/mifeature.feature`

```gherkin
Feature: Mi nueva funcionalidad
  
  @miTag
  Scenario: Validar nueva funcionalidad
    Given Acceder a la pagina "https://petstore.octoperf.com/"
    When Click en nuevo elemento
    Then Validar resultado esperado
```

### 2. Crear Page Object

Archivo: `src/test/java/Pages/MiNuevaPagina.java`

```java
package Pages;

import Configuration.Configurations;
import org.openqa.selenium.By;

public class MiNuevaPagina extends Configurations {
    private By miElemento = By.id("miId");
    
    public void clickMiElemento() {
        clickElement(miElemento);
    }
}
```

### 3. Crear Step Definitions

Archivo: `src/test/java/Steps/MiFeatureSteps.java`

```java
package Steps;

import Pages.MiNuevaPagina;
import io.cucumber.java.en.When;

public class MiFeatureSteps {
    MiNuevaPagina pagina = new MiNuevaPagina();
    
    @When("Click en nuevo elemento")
    public void clickEnNuevoElemento() {
        pagina.clickMiElemento();
    }
}
```

## 🎓 Buenas Prácticas Implementadas

1. ✅ **Page Object Model (POM)**: Separación clara de páginas y lógica de pruebas
2. ✅ **DRY (Don't Repeat Yourself)**: Métodos reutilizables en clase base
3. ✅ **Esperas explícitas**: Sincronización robusta con `WebDriverWait`
4. ✅ **Data-Driven Testing**: Uso de CSV para múltiples conjuntos de datos
5. ✅ **BDD con Cucumber**: Pruebas legibles en lenguaje natural
6. ✅ **Generación dinámica de datos**: Evita conflictos con datos duplicados
7. ✅ **Interfaces para polimorfismo**: Manejo genérico de diferentes categorías
8. ✅ **Reportes visuales**: Integración con Allure para reportes detallados
9. ✅ **Manejo de excepciones**: Try-catch en operaciones críticas
10. ✅ **Nomenclatura descriptiva**: Métodos y variables con nombres claros

## 🐛 Troubleshooting

### Problema: "Element not clickable"
**Solución**: Usar `jsClick()` en lugar de `clickElement()`

### Problema: "Stale element reference"
**Solución**: Re-localizar el elemento antes de interactuar

### Problema: ChromeDriver no encontrado
**Solución**: Selenium Manager lo descarga automáticamente en versión 4.20+

### Problema: Timeout esperando elemento
**Solución**: Usar `waitForElementTime()` para esperas más largas (30s)

## 📞 Contacto y Soporte

Para preguntas o issues:
- Crear un issue en el repositorio
- Contactar al equipo de QA

## 📄 Licencia

[Especificar licencia del proyecto]

---

**Última actualización**: 2025-11-29
**Versión del Framework**: 1.0.0
