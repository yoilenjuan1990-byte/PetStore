package Steps.Register;

import Page.HomePage;
import Page.LoginPage;
import Page.RegisterPage;
import Utils.CSVDataReader;
import Utils.TestDataGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class RegisterFeatureSteps {

    HomePage homePage = new HomePage();
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
        String currentUrl = homePage.getCurrentURL();
        System.out.println("DEBUG Current URL after register: " + currentUrl);

        // Normalizar la URL eliminando jsessionid si existe
        String normalizedUrl = currentUrl.replaceAll(";jsessionid=[^?]*", "");

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
            homePage.clickButton(linkText);
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
}
