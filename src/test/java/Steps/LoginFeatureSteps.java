package Steps;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import Pages.PageBoard;

public class LoginFeatureSteps {

    PageBoard pb = new PageBoard();
    LoginPage loginPage = new LoginPage();

    @Given("Access to the page {string}")
    public void accessToThePage(String url) {
        pb.accessPageURL(url);
    }

    @And("Verify that {string} is visible")
    public void verifyThatIsVisible(String element) {
        String resultadObtenido = pb.obtenerElementoVisible(element);
        Assert.assertTrue(pb.verifyElementoVisible(element));
        Assert.assertEquals(element, resultadObtenido);
    }

    @And("The {string} button is clickable")
    public void theButtonIsClickable(String buttonText) {
        boolean clickable;
        if ("Login".equalsIgnoreCase(buttonText)) {
            clickable = loginPage.isLoginButtonClickable();
        } else {
            clickable = pb.isButtonClickable(buttonText);
        }
        Assert.assertTrue("El botón no es clickable: " + buttonText, clickable);
    }

    @And("I click in the {string} button")
    public void iClickInTheButton(String buttonText) {
        if ("Login".equalsIgnoreCase(buttonText)) {
            loginPage.clickLoginButton();
        } else {
            pb.clickButton(buttonText);
        }
    }

    @And("Verify that the currentURL {string}")
    public void verifyThatTheCurrentURL(String fragment) {
        //Assert.assertTrue("La URL actual no contiene: " + fragment,
        //pb.verifyCurrentUrl(fragment));
        String currentUrl = pb.currentURL();
        System.out.println("DEBUG Current URL: " + currentUrl);
        // Ignorar el jsessionid si está presente
        String normalizedUrl = currentUrl.replaceAll(";jsessionid=[^?]*", "");
        Assert.assertTrue("La URL actual no contiene: " + fragment,
                normalizedUrl.contains(fragment));
    }

    // ----------- Login Scenario -----------

    @Given("Access to the user {string}")
    public void accessToTheUser(String username) {
        loginPage.enterUsername(username);
    }

    @And("Access to the password {string}")
    public void accessToThePassword(String password) {
        loginPage.enterPassword(password);
    }


    @And("Verify that the currentURL after login contains {string}")
    public void verifyThatTheCurrentURLAfterLogin(String expectedUrl) {
        if (expectedUrl == null || expectedUrl.trim().isEmpty() ||
                "NONE".equalsIgnoreCase(expectedUrl.trim())) {
            return; // No validar URL en casos como login fallido
        }
        Assert.assertTrue(loginPage.verifyCurrentUrl(expectedUrl));
    }

    @Then("Verify that the error message {string} is visible")
    public void verifyThatTheErrorMessageIsVisible(String expectedMessage) {
        if (expectedMessage == null || expectedMessage.trim().isEmpty() ||
                "NONE".equalsIgnoreCase(expectedMessage.trim())) {
            Assert.assertFalse("Unexpected error message displayed",
                    loginPage.hasErrorMessage());
        } else {
            Assert.assertTrue("El mensaje de error no es el esperado",
                    loginPage.isErrorMessageVisible(expectedMessage));
        }
    }

}
