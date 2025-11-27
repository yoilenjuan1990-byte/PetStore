package Pages;

import Configuration.Configurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

//Clase para manejar la navegación del catálogo: menú lateral y barra de búsqueda

public class CatalogNavigationPage extends Configurations {

    // Locators para el menú lateral (Sidebar)
    private By sidebarMenu = By.cssSelector("#SidebarContent");
    
    // Método genérico para obtener el enlace del sidebar por categoría
    private By getSidebarLink(String categoryName) {
        return By.xpath("//div[@id='Sidebar']//a[contains(text(), '" + categoryName + "')]");
    }

    // Locators para la barra de búsqueda
    private By searchBar = By.name("keyword");
    private By searchButton = By.name("searchProducts");
    
    // Locators para resultados de búsqueda
    private By searchResultsTable = By.xpath("//table[contains(@align,'left')]");
    private By noResultsMessage = By.xpath("//*[contains(text(), 'no products')]");

    // ========== Métodos para el Menú Lateral ==========

    /**
     * Verifica si el menú lateral está desplegado
     */
    public boolean isSidebarMenuDisplayed() {
        return isElementPresent(sidebarMenu) && waitForElement(sidebarMenu).isDisplayed();
    }

    /**
     * Verifica si un enlace lateral específico es visible
     */
    public boolean isSidebarLinkVisible(String categoryName) {
        By linkLocator = getSidebarLink(categoryName);
        return isElementPresent(linkLocator) && waitForElement(linkLocator).isDisplayed();
    }

    /**
     * Hace clic en un enlace del menú lateral
     */
    public void clickSidebarLink(String categoryName) {
        By linkLocator = getSidebarLink(categoryName);
        clickElement(linkLocator);
    }

    // ========== Métodos para la Barra de Búsqueda ==========

    /**
     * Verifica si la barra de búsqueda está desplegada
     */
    public boolean isSearchBarDisplayed() {
        return isElementPresent(searchBar) && waitForElement(searchBar).isDisplayed();
    }

    /**
     * Ingresa una palabra clave en la barra de búsqueda
     */
    public void enterSearchKeyword(String keyword) {
        WebElement searchField = waitForElement(searchBar);
        searchField.clear();
        searchField.sendKeys(keyword);
    }

    /**
     * Hace clic en el botón de búsqueda
     */
    public void clickSearchButton() {
        clickElement(searchButton);
    }

    /**
     * Verifica si los resultados de búsqueda se muestran
     */
    public boolean areSearchResultsDisplayed() {
        try {
            return waitForElement(searchResultsTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si los resultados de búsqueda contienen un producto específico
     */
    public boolean searchResultsContainProduct(String productId) {
        try {
            By productLocator = By.xpath("//table//td[contains(text(), '" + productId + "')]");
            return waitForElement(productLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si los resultados de búsqueda contienen un texto específico
     */
    public boolean searchResultsContainText(String expectedText) {
        try {
            By textLocator = By.xpath("//table//td[contains(text(), '" + expectedText + "')]");
            return waitForElement(textLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica si se muestra el mensaje de "sin resultados"
     */
    public boolean isNoResultsMessageDisplayed() {
        try {
            return waitForElement(noResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene todos los enlaces del menú lateral
     */
    public List<WebElement> getAllSidebarLinks() {
        By allLinks = By.xpath("//div[@id='Sidebar']//a");
        return driver.findElements(allLinks);
    }
}
