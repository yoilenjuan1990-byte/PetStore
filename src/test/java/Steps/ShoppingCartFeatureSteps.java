package Steps;

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

    @And("Save price on Item Id Page")
    public void savePriceOnItemIdPage() {
        fishItIdPage.guardarPrecio();
        savedPricesList.add(fishItIdPage.getPrecioGuardado());
    }

    @Then("Validar que se muestra el producto {string} en el carrito")
    public void validarQueSeMuestraElProductoEnElCarrito(String prodId) {
        shoppCart.validateProductInCart(prodId);
    }

    @And("Validar que el precio del producto {string} en el carrito es el mismo que en item page")
    public void validarQueElPrecioDelProductoEnElCarritoEsElMismoQueEnItemPage(String prodID) {
        double expectedPrice = fishItIdPage.getPrecioGuardado();
        shoppCart.validateCartTotalMatches(prodID, expectedPrice);
    }

    @And("Validar monto total en el carrito coincide con la suma de todos los productos")
    public void validarMontoTotalEnElCarrito() {
        double sumaEsperada = savedPricesList.stream().mapToDouble(Double::doubleValue).sum();
        /*
         * alternativa para el stream
         * double sumaEsperada = 0.0;
         * for (Double precio : preciosGuardados) {
         *     sumaEsperada += precio;
         * }
         * */
        shoppCart.validateCartTotalMatches(sumaEsperada);
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
}
