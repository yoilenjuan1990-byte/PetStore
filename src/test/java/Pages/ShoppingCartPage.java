package Pages;

import Configuration.Configurations;
import org.testng.Assert;
import org.openqa.selenium.By;

public class ShoppingCartPage extends Configurations {
    // Localizadores
    private By cartTable = By.xpath("//table[@id='Cart']");
    private By cartTotal = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    private By emptyCartMessage = By.xpath("//div[contains(text(),'Your cart is empty')]");

    // Validar que un producto específico está en el carrito
    public void validateProductInCart(String productID) {
        By cartProductId = By.xpath("//td[contains(text(),'" + productID + "')]");
        Assert.assertTrue(isElementPresent(cartProductId), "El producto '" + productID + "' no está en el carrito.");
    }

    // Validar que el total del carrito sea mayor a cero
    public void validateCartTotalGreaterThanZero() {
        String totalText = getElementText(cartTotal).replace("$", "").trim();
        double total = Double.parseDouble(totalText);
        Assert.assertTrue(total > 0, "El total del carrito no es mayor a cero.");
    }

    // Validar que el carrito esté vacío
    public void validateCartIsEmpty() {
        Assert.assertTrue(isElementPresent(emptyCartMessage), "El carrito no está vacío.");
    }

    // Obtener el total del carrito como texto
    public String getCartTotal() {
        return getElementText(cartTotal);
    }

    // Verificar si el carrito contiene un número específico de productos
    public void validateNumberOfItemsInCart(int expectedCount) {
        int actualCount = driver.findElements(By.xpath("//table[@id='Cart']//tr")).size() - 1; // excluye encabezado
        Assert.assertEquals(actualCount, expectedCount, "Cantidad de productos en el carrito incorrecta.");
    }

    // Valida que el precio del item en el carrito es el mismo que en la página del item
    public void validateCartTotalMatches(String prodID, double expectedPrice) {
        double actualPrice = getProductPriceFromCart(prodID);
        Assert.assertEquals(actualPrice, expectedPrice, "El precio en el carrito no coincide con el precio del item.");
    }

    // Busca dentro de la tabla un producto que el xpath contenga el prodID y toma la celda siguiente que contiene el precio
    private double getProductPriceFromCart(String prodID) {
        By priceLocator = By.xpath("//td[contains(text(),'" + prodID + "')]/following-sibling::td[1]");
        String price = getElementText(priceLocator).replace("$", "").trim();
        return Double.parseDouble(price);

    }

    public void validateCartTotalMatches(double sumaEsperada) {
        String rawTotal = getElementText(cartTotal).replace("$", "").trim();
        double actualTotal = Double.parseDouble(rawTotal);
        Assert.assertEquals(actualTotal, sumaEsperada, "El total del carrito no coincide con la suma de los precios guardados.");

    }

}
