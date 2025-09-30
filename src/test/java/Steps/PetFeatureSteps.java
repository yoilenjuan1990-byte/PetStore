package Steps;

import Interface.PetNavigationItemIdManager;
import Interface.PricePetManager;
import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

//Define los pasos de Cucumber para navegar, seleccionar productos, validar páginas, guardar precios, etc.

public class PetFeatureSteps {
    PageBoard pb = new PageBoard();
    ShoppingCartPage cart = new ShoppingCartPage();
    PricePetManager priceManager;
    PetNavigationItemIdManager petNavItemIdManag;
    List<Double> preciosGuardados = new ArrayList<>();


    FishProductIdPage fishProductIdPage = new FishProductIdPage();
    DogsProductIdPage dogsProductIdP = new DogsProductIdPage();
    ReptilesProductIdPage reptilesProductIdP = new ReptilesProductIdPage();
    CatsProductIdPage catsProductIdP = new CatsProductIdPage();
    BirdsProductIdPage birdsProductIdP = new BirdsProductIdPage();
    FishItemIdPage fishItemIdPage = new FishItemIdPage();


    @Given("Access page {string}")
    public void accessPage(String url) {
        pb.accessPageURL(url);
    }

    @Given("Validate that there is a superior menu for {string}")
    public void validateThatThereIsASuperiorMenuFor(String petType) {
        pb.getMenuName(petType);
    }

    @Given("Click on superior menu {string}")
    public void clickOnSuperiorMenu(String petName) {
        pb.ClickOnMenuOption(petName);
    }

    @Then("Validate page {string}")
    public void validatePage(String currentUrl) {
        pb.validateURL(currentUrl);
    }

    @When("Click on id product {string}")
    public void clickOnIdProduct(String petProdId) {
        switch (petProdId) {
            case "FI-SW-01":
                fishProductIdPage.seleccionarProductId(petProdId);
                break;
            case "K9-BD-01":
                dogsProductIdP.seleccionarProductId(petProdId);
                break;
            case "RP-SN-01":
                reptilesProductIdP.seleccionarProductId(petProdId);
                break;
            case "FL-DSH-01":
                catsProductIdP.seleccionarProductId(petProdId);
                break;
            case "AV-CB-01":
                birdsProductIdP.seleccionarProductId(petProdId);
                break;
            default:
                break;
        }
    }

    @And("Save price on Item Id Page")
    public void savePriceOnItemIdPage() {
        priceManager.guardarPrecio();
        preciosGuardados.add(priceManager.getPrecioGuardado());
    }


    @And("El catalogo esta completamente cargado")
    public void elCatalogoEstaCompletamenteCargado() {
        pb.verifyCatalogLoaded();
    }

    @And("Click on Add to Cart Button")
    public void clickOnAddToCartButton() {
        petNavItemIdManag.clickOnAddToCart();
    }

    @And("Back to main page")
    public void backToMainPage(String mainMenuURL) {
        pb.goToMainPage();
    }


    @Then("Validar que se muestra el producto {string} en el carrito")
    public void validarQueSeMuestraElProductoEnElCarrito(String prodId) {
        cart.validateProductInCart(prodId);
    }

    @And("Validar que el precio del producto {string} en el carrito es el mismo que en item page")
    public void validarQueElPrecioDelProductoEnElCarritoEsElMismoQueEnItemPage(String prodID) {
        double expectedPrice = priceManager.getPrecioGuardado();
        cart.validateCartTotalMatches(prodID, expectedPrice);
    }

    @And("Validar monto total en el carrito coincide con la suma de todos los productos")
    public void validarMontoTotalEnElCarrito() {
        double sumaEsperada = preciosGuardados.stream().mapToDouble(Double::doubleValue).sum();
        /*
        * alternativa para el stream
        * double sumaEsperada = 0.0;
        * for (Double precio : preciosGuardados) {
        *     sumaEsperada += precio;
        * }
        * */
        cart.validateCartTotalMatches(sumaEsperada);
    }
}
