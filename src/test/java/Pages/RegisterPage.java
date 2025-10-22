package Page;

import Configuration.Configuracion;
import org.openqa.selenium.By;

public class RegisterPage extends Configuracion {

    // Locators para el formulario de registro
    private By userIdField = By.name("username");
    private By passwordField = By.name("password");
    private By repeatPasswordField = By.name("repeatedPassword");
    private By firstNameField = By.name("account.firstName");
    private By lastNameField = By.name("account.lastName");
    private By emailField = By.name("account.email");
    private By phoneField = By.name("account.phone");
    private By address1Field = By.name("account.address1");
    private By address2Field = By.name("account.address2");
    private By cityField = By.name("account.city");
    private By stateField = By.name("account.state");
    private By zipField = By.name("account.zip");
    private By countryField = By.name("account.country");

    // Botones y enlaces
    private By registerNowLink = By.linkText("Register Now!");
    private By saveAccountButton = By.name("newAccount");

    // Mensajes
    private By successMessage = By.xpath("//*[@id='WelcomeContent']");
    private By errorMessage = By.xpath("//*[@id='Content']//li");

    // ========== Métodos de navegación ==========

    public void clickRegisterNowLink() {
        clickElement(registerNowLink);
    }

    // ========== Métodos para ingresar datos en los campos ==========

    public void enterUserId(String userId) {
        writeText(userIdField, userId);
    }

    public void enterPassword(String password) {
        writeText(passwordField, password);
    }

    public void enterRepeatPassword(String repeatPassword) {
        writeText(repeatPasswordField, repeatPassword);
    }

    public void enterFirstName(String firstName) {
        writeText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        writeText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        writeText(emailField, email);
    }

    public void enterPhone(String phone) {
        writeText(phoneField, phone);
    }

    public void enterAddress1(String address1) {
        writeText(address1Field, address1);
    }

    public void enterAddress2(String address2) {
        writeText(address2Field, address2);
    }

    public void enterCity(String city) {
        writeText(cityField, city);
    }

    public void enterState(String state) {
        writeText(stateField, state);
    }

    public void enterZip(String zip) {
        writeText(zipField, zip);
    }

    public void enterCountry(String country) {
        writeText(countryField, country);
    }

    // ========== Métodos para acciones ==========

    public void clickSaveAccountButton() {
        clickElement(saveAccountButton);
    }

    // ========== Métodos de validación ==========

    public boolean isSuccessMessageDisplayed() {
        return isElementPresent(successMessage);
    }

    public String getSuccessMessage() {
        return getElementText(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementPresent(errorMessage);
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            String text = getElementText(errorMessage);
            System.out.println("Debug Register Error Message: " + text);
            return text;
        }
        return "";
    }

    public boolean isErrorMessageVisible(String expectedText) {
        return getErrorMessage().toLowerCase().contains(expectedText.toLowerCase());
    }

    public boolean verifyCurrentUrl(String expectedUrl) {
        return currentURLContains(expectedUrl);
    }

    // ========== Método auxiliar para registro completo ==========

    public void fillRegistrationForm(String userId, String password, String repeatPassword,
                                     String firstName, String lastName, String email,
                                     String phone, String address1, String city,
                                     String state, String zip, String country) {
        enterUserId(userId);
        enterPassword(password);
        enterRepeatPassword(repeatPassword);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhone(phone);
        enterAddress1(address1);
        enterCity(city);
        enterState(state);
        enterZip(zip);
        enterCountry(country);
    }
}

