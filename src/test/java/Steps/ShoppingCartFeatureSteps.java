package Steps;

import Interface.PetNavigationItemIdManager;
import Interface.PricePetManager;
import Pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCartFeatureSteps {

    ShoppingCartPage shoppCart = new ShoppingCartPage();
    List<Double> savedPricesList = new ArrayList<>();

    FishProductIdPage fishProdIdPage = new FishProductIdPage();
    DogsProductIdPage dogsProdIdP = new DogsProductIdPage();
    ReptilesProductIdPage reptilesProdIdP = new ReptilesProductIdPage();
    CatsProductIdPage catsProdIdP = new CatsProductIdPage();
    BirdsProductIdPage birdsProdIdP = new BirdsProductIdPage();
    FishItemIdPage fishItIdPage = new FishItemIdPage();
    DogsItemIdPage dogsItPage = new DogsItemIdPage();

    private PricePetManager priceManager;
    private PetNavigationItemIdManager itemIdManager;


    @And("Save price on Item Id Page")
    public void savePriceOnItemIdPage(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : items) {
            String itemId = row.get("Item ID").trim();
            guardarPrecioItemId(itemId);
        }

    }

    private void guardarPrecioItemId(String itemId) {
        switch (itemId) {
            case "EST-1":
            case "EST-2":
            case "EST-3":
            case "EST-4":
            case "EST-5":
            case "EST-20":
            case "EST-21":
                fishItIdPage.guardarPrecio(itemId);
                savedPricesList.add(fishItIdPage.getPrecioGuardado());
                break;
            case "EST-6":
            case "EST-7":
            case "EST-8":
                dogsItPage.guardarPrecio(itemId);
                savedPricesList.add(dogsItPage.getPrecioGuardado());
                break;
            default:
                System.out.println("La mascota no existe..");
        }
    }

    @Then("Validar que se muestra el producto {string} en el carrito")
    public void validarQueSeMuestraElProductoEnElCarrito(String prodId) {
        shoppCart.validateProductInCart(prodId);
    }

    @And("Validar que el precio del producto {string} en el carrito es el mismo que en item page")
    public void validarQueElPrecioDelProductoEnElCarritoEsElMismoQueEnItemPage(String prodID) {
        double expectedPrice = 0.0;
        switch (prodID) {
            case "FI-SW-01":
                expectedPrice = fishItIdPage.getPrecioGuardado();
                break;
            case "K9-BD-01":
                expectedPrice = dogsItPage.getPrecioGuardado();
                break;
            default:
                System.out.println("El producto no esta en el carrito.");
        }
        shoppCart.validateCartTotalMatches(prodID, expectedPrice);
    }

    @And("Validar monto total en el carrito coincide con la suma de todos los productos {double}")
    public void validarMontoTotalEnElCarrito(double expectedPriceResult) {
        double sumaEsperada = savedPricesList.stream().mapToDouble(Double::doubleValue).sum();
        shoppCart.validateCartTotalMatches(sumaEsperada, expectedPriceResult);
    }

    @And("Validar que se muestren los siguientes productos")
    public void validarQueSeMuestrenLosSiguientesProductos(DataTable dataTable) {
        // Obtener la lista de productos desde la tabla de datos
        List<Map<String, String>> productos = dataTable.asMaps(String.class, String.class);

        // Recorrer cada producto y validarlo en el carrito
        for (Map<String, String> producto : productos) {
            String productId = producto.get("Producto").replace("\"", ""); // Remover comillas
            shoppCart.validateProductInCart(productId);
            System.out.println("✓ Producto validado en el carrito: " + productId);
        }
    }

    @Then("Validar Si esta el mensaje {string}")
    public void validarSiEstaElMensaje(String emptyCartMsg) {
        shoppCart.validateCartIsEmpty();
    }

    @And("Click on Remove button")
    public void clickOnRemoveButton() throws InterruptedException {
        shoppCart.clickAllRemoveButtons();
    }
}
