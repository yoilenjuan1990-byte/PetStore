package Steps;

import Pages.CatalogNavigationPage;
import Pages.PageBoard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CatalogNavigationFeatureSteps {

    PageBoard pb = new PageBoard();
    CatalogNavigationPage catalogNavPage = new CatalogNavigationPage();

    // ========== Pasos para el Menú Lateral ==========

    @Then("Verificar que el menú lateral está desplegado")
    public void verificarQueElMenuLateralEstaDesplegado() {
        Assert.assertTrue("El menú lateral no está visible", 
            catalogNavPage.isSidebarMenuDisplayed());
    }

    @Given("Validar que el enlace lateral {string} es visible")
    public void validarQueElEnlaceLateralEsVisible(String categoryName) {
        Assert.assertTrue("El enlace lateral " + categoryName + " no es visible",
            catalogNavPage.isSidebarLinkVisible(categoryName));
    }

    @When("Click en el enlace lateral {string}")
    public void clickEnElEnlaceLateral(String categoryName) {
        catalogNavPage.clickSidebarLink(categoryName);
    }

    // ========== Pasos para la Barra de Búsqueda ==========

    @Then("Verificar que la barra de búsqueda está desplegada")
    public void verificarQueLaBarraDeBusquedaEstaDesplegada() {
        Assert.assertTrue("La barra de búsqueda no está visible",
            catalogNavPage.isSearchBarDisplayed());
    }

    @Given("Validar que la barra de búsqueda es visible")
    public void validarQueLaBarraDeBusquedaEsVisible() {
        Assert.assertTrue("La barra de búsqueda no es visible",
            catalogNavPage.isSearchBarDisplayed());
    }

    @When("Ingresar palabra clave de búsqueda {string}")
    public void ingresarPalabraClaveDeBusqueda(String keyword) {
        catalogNavPage.enterSearchKeyword(keyword);
    }

    @And("Click en el botón de búsqueda")
    public void clickEnElBotonDeBusqueda() {
        catalogNavPage.clickSearchButton();
    }

    @Then("Validar que los resultados de búsqueda se muestran")
    public void validarQueLosResultadosDeBusquedaSeMuestran() {
        Assert.assertTrue("Los resultados de búsqueda no se muestran",
            catalogNavPage.areSearchResultsDisplayed());
    }

    @Then("Validar que los resultados contienen el producto {string}")
    public void validarQueLosResultadosContienenElProducto(String productId) {
        Assert.assertTrue("Los resultados no contienen el producto " + productId,
            catalogNavPage.searchResultsContainProduct(productId));
    }

    @And("Validar que los resultados contienen {string}")
    public void validarQueLosResultadosContienen(String expectedText) {
        Assert.assertTrue("Los resultados no contienen el texto: " + expectedText,
            catalogNavPage.searchResultsContainText(expectedText));
    }

    @Then("Validar que se muestra mensaje de sin resultados")
    public void validarQueSeMuestraMensajeDeSinResultados() {
        Assert.assertTrue("No se muestra el mensaje de sin resultados",
            catalogNavPage.isNoResultsMessageDisplayed());
    }
}
