package Pages;


import Configuration.Configurations;
import org.openqa.selenium.By;

public class LoginPage extends Configurations {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    //private By loginButton = By.name("signon");
    private By loginButton   = By.cssSelector("#MenuContent > a:nth-child(3)");
    private By errorMessage = By.xpath("//*[@id='Content']//li");
// en el PetStore aparece dentro de <li> con el texto de error

    public void enterUsername(String username) {
        writeText(usernameField, username);
    }

    public void enterPassword(String password) {
        writeText(passwordField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public boolean isLoginButtonClickable() {
        return waitForClickableElement(loginButton).isDisplayed();
    }

    public boolean verifyCurrentUrl(String expectedUrl) {
        //   return getCurrentURL().contains(expectedUrl);
        return getCurrentUrlResult(expectedUrl);
    }

    public String getErrorMessage() {
        String text = getElementText(errorMessage);
        System.out.println("Debug Error Message" + text);
        return text;
    }

    public boolean isErrorMessageVisible(String expectedText) {
        return getErrorMessage().contains(expectedText);
    }

    public boolean hasErrorMessage() {
        return isElementPresent(errorMessage);
    }

}

