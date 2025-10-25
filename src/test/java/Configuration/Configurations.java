package Configuration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

//Contiene la lógica base para WebDriver, esperas, acciones comunes, manejo de ventanas, alertas, scroll, etc.

public class Configurations {
    protected static WebDriver driver;
    private static WebDriverWait wait;

    static {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera explícita
    }

    //Escribir en un campo de texto
    public void writeText(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear(); // Limpiar el campo antes de escribir
        element.sendKeys(text);
    }

    //Seleccionar una fecha en un campo de fecha datapicker
    public void selectDate(By datePickerLocator, String date) {
        WebElement datePicker = waitForElement(datePickerLocator);
        datePicker.click(); // Abrir el datepicker
        // Aquí puedes agregar lógica para seleccionar la fecha específica
        // Por ejemplo, si el datepicker tiene un formato específico, puedes buscar el elemento correspondiente
        // y hacer clic en él.
        // Esto depende de cómo esté implementado el datepicker en tu aplicación.
    }

    //Seleccionar un elemento de un dropdown
    public void selectDropdownOption(By dropdownLocator, String optionText) {
        WebElement dropdown = waitForElement(dropdownLocator);
        dropdown.click(); // Abrir el dropdown
        // Aquí puedes buscar el elemento de opción por su texto y hacer clic en él
        By optionLocator = By.xpath(".//option[text()='" + optionText + "']");
        WebElement option = waitForElement(optionLocator);
        option.click();
    }


    //Esperar hasta que un elemento sea visible
    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Esperar hasta que un elemento sea visible en un tiempo determinado
    public WebElement waitForElementTime(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // 30 s ahora
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Esperar hasta que un elemento sea clickeable
    public WebElement waitForClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Hacer clic en un elemento después de esperar
    public void clickElement(By locator) {
        waitForClickableElement(locator).click();
    }

    //Hacer enter en un elemento después de esperar
    public void pressEnterElement(By locator) {
        WebElement element = waitForElement(locator);
        element.sendKeys(Keys.ENTER);
    }

    //Dar doble clic en un elemento
    public void doubleClickElement(By locator) {
        WebElement element = waitForClickableElement(locator);
        new Actions(driver).doubleClick(element).perform();
    }

    //Obtener el texto de un elemento después de esperar
    public String getElementText(By locator) {
        return waitForElement(locator).getText();
    }

    /** Capturar pantalla **/
        /*
        public void takeScreenshot(String fileName) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUt ils.copyFile(srcFile, new File("screenshots/" + fileName + ".png"));
            } catch (IOException e) {
                System.out.println("Error al guardar captura de pantalla: " + e.getMessage());
            }
        }*/

    /**
     * Desplazar hasta un elemento
     **/
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Manejar alertas
     **/
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    /**
     * Obtener el texto de una alerta
     **/
    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    /**
     * Ingresar texto en un elemento después de esperar
     **/
    public void enterText(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Verificar si un elemento está presente en la página
     **/
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //validar que el elemento no está visible
    public Boolean isElementNotVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /*
     Verificar si un elemento NO está presente en la página
    public boolean isElementNotPresent(By locator) {
        try {
            driver.findElement(locator);
            return false; // Está presente → retorna falso
        } catch (NoSuchElementException e) {
            return true; // No está presente → retorna verdadero
        }
    }
    */


    //Obligar clic por JavaScript (cuando el click normal falla)

    public void jsClick(By locator) {
        WebElement element = waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    //Mover el cursor y hacer hover sobre un elemento
    public void hoverOverElement(By locator) {
        WebElement element = waitForElement(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    //Desplazarse a la parte inferior de la página
    public void scrollToBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    //Arrastrar y soltar (drag and drop)
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitForElement(sourceLocator);
        WebElement target = waitForElement(targetLocator);
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    //Enviar teclas especiales (ENTER, ESCAPE, etc.)
    public void pressKey(By locator, Keys key) {
        WebElement element = waitForElement(locator);
        element.sendKeys(key);
    }

    //Manejo de frames, ventanas y pestañas
    //Cambiar a un frame por locator
    public void switchToFrame(By locator) {
        WebElement frame = waitForElement(locator);
        driver.switchTo().frame(frame);
    }

    //Volver al contenido principal (default content)
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    //Cambiar a una nueva ventana o pestaña
    public void switchToNewWindow() {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
    }

    //Abrir nueva pestaña/ventana y cambiar a ella
    public void switchToNewTab() {
        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    //Cerrar la ventana actual y volver a la ventana original
    public void closeCurrentWindowAndSwitchToOriginal() {
        String originalHandle = driver.getWindowHandle();
        driver.close();
        driver.switchTo().window(originalHandle);
    }

    //Cerrar la ventana actual y volver a la anterior
    public void closeCurrentWindowAndSwitchBack() {
        driver.close();
        switchToDefaultContent();
    }


    public void accederAPagina(String url) {
        driver.get(url);
    }

    public String obtenerTitulo(By locator) {
        // Espera hasta que el elemento esté presente y visible
        WebElement element = waitForElementTime(locator);
        return driver.findElement(locator).getText();
    }

    protected String getCurrentUrlResult() {
        return driver.getCurrentUrl();
    }

    public String getNombreImagen(By locator) {
        WebElement imagen = waitForElement(locator);
        String ruta = imagen.getAttribute("src");   // devuelve la ruta completa

// Si solo quieres el nombre (sm_fish.gif):
        String nombre = ruta.substring(ruta.lastIndexOf("/") + 1);
        String nombreImagen = nombre.replace("sm_", "").replace(".gif", "");
        return nombreImagen;
    }
    public boolean currentURLContains(String fragment) {
        return driver.getCurrentUrl().contains(fragment);
    }
}
