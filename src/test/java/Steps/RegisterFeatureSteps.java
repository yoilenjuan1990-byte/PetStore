package Steps;


import Pages.LoginPage;
import Pages.PageBoard;
import Pages.RegisterPage;
import Utils.CSVDataReader;
import Utils.TestDataGenerator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class RegisterFeatureSteps {

    PageBoard pb = new PageBoard();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    // Variables para almacenar datos generados dinámicamente
    private TestDataGenerator.UserTestData randomUserData;
    private Map<String, String> csvUserData;

    // ========== Steps de navegación ==========

    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {
        if ("Register Now!".equalsIgnoreCase(linkText)) {
            registerPage.clickRegisterNowLink();
        }
    }

    // ========== Steps para ingresar datos en los campos ==========

    @Given("I enter the user ID {string}")
    public void iEnterTheUserID(String userId) {
        registerPage.enterUserId(userId);
    }

    @And("I enter the password {string}")
    public void iEnterThePassword(String password) {
        registerPage.enterPassword(password);
    }

    @And("I enter the repeat password {string}")
    public void iEnterTheRepeatPassword(String repeatPassword) {
        registerPage.enterRepeatPassword(repeatPassword);
    }

    @And("I enter the first name {string}")
    public void iEnterTheFirstName(String firstName) {
        registerPage.enterFirstName(firstName);
    }

    @And("I enter the last name {string}")
    public void iEnterTheLastName(String lastName) {
        registerPage.enterLastName(lastName);
    }

    @And("I enter the email {string}")
    public void iEnterTheEmail(String email) {
        registerPage.enterEmail(email);
    }

    @And("I enter the phone {string}")
    public void iEnterThePhone(String phone) {
        registerPage.enterPhone(phone);
    }

    @And("I enter the address1 {string}")
    public void iEnterTheAddress1(String address1) {
        registerPage.enterAddress1(address1);
    }

    @And("I enter the address2 {string}")
    public void iEnterTheAddress2(String address2) {
        registerPage.enterAddress2(address2);
    }

    @And("I enter the city {string}")
    public void iEnterTheCity(String city) {
        registerPage.enterCity(city);
    }

    @And("I enter the state {string}")
    public void iEnterTheState(String state) {
        registerPage.enterState(state);
    }

    @And("I enter the zip {string}")
    public void iEnterTheZip(String zip) {
        registerPage.enterZip(zip);
    }

    @And("I enter the country {string}")
    public void iEnterTheCountry(String country) {
        registerPage.enterCountry(country);
    }

    // ========== Steps para acciones ==========

    @And("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        if ("Save Account Information".equalsIgnoreCase(buttonText)) {
            registerPage.clickSaveAccountButton();
        }
    }

    // ========== Steps para validaciones ==========

    @Then("Verify successful registration message is displayed")
    public void verifySuccessfulRegistrationMessageIsDisplayed() {
        Assert.assertTrue("Success message should be displayed",
                registerPage.isSuccessMessageDisplayed());
    }

    @And("Verify that the currentURL after register contains {string}")
    public void verifyThatTheCurrentURLAfterRegisterContains(String expectedUrl) {
        String url = pb.currentURL();
        System.out.println("DEBUG Current URL after register: " + url);

        // Normalizar la URL eliminando jsessionid si existe
        String normalizedUrl = url.replaceAll(";jsessionid=[^?]*", "");

        Assert.assertTrue("La URL actual no contiene: " + expectedUrl,
                normalizedUrl.contains(expectedUrl));
    }

    @Then("Verify that the error message register {string} is visible")
    public void verifyThatTheErrorMessageRegisterIsVisible(String expectedMessage) {
        Assert.assertTrue("El mensaje de error de registro no es el esperado: " + expectedMessage,
                registerPage.isErrorMessageVisible(expectedMessage));
    }

    // ========== Steps para Data-Driven Testing con Random Data ==========

    @Given("I generate random user data")
    public void iGenerateRandomUserData() {
        randomUserData = TestDataGenerator.generateCompleteUserData();
        System.out.println("Generated random user: " + randomUserData.username);
    }

    @Given("I generate random user data with specifications:")
    public void iGenerateRandomUserDataWithSpecifications(DataTable dataTable) {
        // Convertir la tabla a un mapa
        Map<String, String> specs = dataTable.asMap(String.class, String.class);

        // Obtener especificaciones del username
        int usernameMinLength = Integer.parseInt(specs.getOrDefault("username_min_length", "2"));
        int usernameMaxLength = Integer.parseInt(specs.getOrDefault("username_max_length", "20"));
        boolean usernameAllowSpecialChars = Boolean.parseBoolean(specs.getOrDefault("username_allow_special_chars", "true"));

        // Obtener especificaciones del password
        int passwordLength = Integer.parseInt(specs.getOrDefault("password_length", "15"));
        boolean passwordUppercase = Boolean.parseBoolean(specs.getOrDefault("password_uppercase", "true"));
        boolean passwordLowercase = Boolean.parseBoolean(specs.getOrDefault("password_lowercase", "true"));
        boolean passwordSpecialChars = Boolean.parseBoolean(specs.getOrDefault("password_special_chars", "true"));
        boolean passwordNumbers = Boolean.parseBoolean(specs.getOrDefault("password_numbers", "true"));

        // Generar username y password personalizados
        String username = TestDataGenerator.generateCustomUsername(usernameMinLength, usernameMaxLength, usernameAllowSpecialChars);
        String password = TestDataGenerator.generateSecurePassword(passwordLength, passwordUppercase, passwordLowercase, passwordSpecialChars, passwordNumbers);

        // Crear objeto de datos de usuario completo
        randomUserData = new TestDataGenerator.UserTestData(
                username,
                password,
                TestDataGenerator.generateRandomFirstName(),
                TestDataGenerator.generateRandomLastName(),
                TestDataGenerator.generateUniqueEmail(),
                TestDataGenerator.generateRandomPhone(),
                TestDataGenerator.generateRandomAddress(),
                TestDataGenerator.generateRandomCity(),
                TestDataGenerator.generateRandomState(),
                TestDataGenerator.generateRandomZipCode(),
                "USA"
        );

        System.out.println("Generated random user with custom specs: " + randomUserData.username);
        System.out.println("Password length: " + randomUserData.password.length());
    }

    @Given("I generate random user data for integration")
    public void iGenerateRandomUserDataForIntegration() {
        randomUserData = TestDataGenerator.generateCompleteUserData();
        System.out.println("Generated random user for integration: " + randomUserData.username);
    }

    @And("I fill the registration form with random data")
    public void iFillTheRegistrationFormWithRandomData() {
        Assert.assertNotNull("Random user data not generated", randomUserData);

        registerPage.fillRegistrationForm(
                randomUserData.username,
                randomUserData.password,
                randomUserData.password, // repeat password igual
                randomUserData.firstName,
                randomUserData.lastName,
                randomUserData.email,
                randomUserData.phone,
                randomUserData.address,
                randomUserData.city,
                randomUserData.state,
                randomUserData.zip,
                randomUserData.country
        );
    }

    // ========== Steps para Data-Driven Testing con CSV ==========

    @Given("I read user data from CSV row {int}")
    public void iReadUserDataFromCSVRow(int rowIndex) {
        List<Map<String, String>> csvData = CSVDataReader.readCSVFromResources("TestData/RegisterTestData.csv");

        if (csvData == null || csvData.isEmpty()) {
            Assert.fail("No se pudo leer el archivo CSV o está vacío");
        }

        if (rowIndex >= csvData.size()) {
            Assert.fail("Índice de fila fuera de rango: " + rowIndex + " (total rows: " + csvData.size() + ")");
        }

        csvUserData = csvData.get(rowIndex);
        System.out.println("CSV user loaded: " + csvUserData.get("username"));
    }

    @And("I fill the registration form with CSV data")
    public void iFillTheRegistrationFormWithCSVData() {
        Assert.assertNotNull("CSV user data not loaded", csvUserData);

        registerPage.fillRegistrationForm(
                csvUserData.get("username"),
                csvUserData.get("password"),
                csvUserData.get("password"), // repeat password igual
                csvUserData.get("firstName"),
                csvUserData.get("lastName"),
                csvUserData.get("email"),
                csvUserData.get("phone"),
                csvUserData.get("address1"),
                csvUserData.get("city"),
                csvUserData.get("state"),
                csvUserData.get("zip"),
                csvUserData.get("country")
        );
    }

    // ========== Steps para Integration Flow (Register → Login) ==========

    @When("I click the {string} link")
    public void iClickTheLink(String linkText) {
        if ("Sign Out".equalsIgnoreCase(linkText)) {
            pb.clickButton(linkText);
        }
    }

    @And("I login with the registered credentials")
    public void iLoginWithTheRegisteredCredentials() {
        Assert.assertNotNull("No user data available for login", randomUserData);

        loginPage.enterUsername(randomUserData.username);
        loginPage.enterPassword(randomUserData.password);
        loginPage.clickLoginButton();
    }

    /*@Then("Verify that the currentURL after login contains {string}")
    public void verifyThatTheCurrentURLAfterLoginContains(String expectedUrl) {
        String currentUrl = homePage.getCurrentURL();
        System.out.println("DEBUG Current URL after login: " + currentUrl);

        String normalizedUrl = currentUrl.replaceAll(";jsessionid=[^?]*", "");

        Assert.assertTrue("La URL actual no contiene: " + expectedUrl,
                normalizedUrl.contains(expectedUrl));
    } */

    // ========== Steps para guardar datos de usuarios generados ==========

    @And("I save the generated user data to CSV file {string}")
    public void iSaveTheGeneratedUserDataToCSVFile(String filename) {
        Assert.assertNotNull("No hay datos de usuario generados para guardar", randomUserData);
        TestDataGenerator.saveUserToCSV(randomUserData, true, filename);
    }

    @And("I save the generated user data to JSON file {string}")
    public void iSaveTheGeneratedUserDataToJSONFile(String filename) {
        Assert.assertNotNull("No hay datos de usuario generados para guardar", randomUserData);
        TestDataGenerator.saveUserToJSON(randomUserData, true, filename);
    }

    @And("I save the generated user data to text file {string}")
    public void iSaveTheGeneratedUserDataToTextFile(String filename) {
        Assert.assertNotNull("No hay datos de usuario generados para guardar", randomUserData);
        TestDataGenerator.saveUserToTextFile(randomUserData, true, filename);
    }

    @And("I save the generated user data to all formats with base name {string}")
    public void iSaveTheGeneratedUserDataToAllFormatsWithBaseName(String baseName) {
        Assert.assertNotNull("No hay datos de usuario generados para guardar", randomUserData);
        TestDataGenerator.saveUserToAllFormats(randomUserData, baseName);
    }

    @Given("I generate and save a complete user with base name {string}")
    public void iGenerateAndSaveACompleteUserWithBaseName(String baseName) {
        randomUserData = TestDataGenerator.generateAndSaveUser(baseName);
        System.out.println("Usuario generado y guardado: " + randomUserData.username);
    }
}
