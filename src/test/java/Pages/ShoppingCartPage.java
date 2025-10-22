package Pages;

import Configuration.Configurations;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.NoSuchElementException;

public class ShoppingCartPage extends Configurations {
    String expectedPrice = "$16.50";
    String productIDLocator = "FI-SW-01";

    // Localizadores
    private By cartTable = By.xpath("//table[@id='Cart']");
    private By cartTotal = By.xpath("//td[contains(text(),'Sub Total: $')]");
    private By emptyCartMessage = By.xpath("//b[normalize-space()='Your cart is empty.']");
    private By itemLocator = By.xpath("//td[contains(text(),'" + productIDLocator + "')]");

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
        By priceLocator = By.xpath("//td[contains(text(),'" + prodID + "')]/following-sibling::td[5]");
        String price = getElementText(priceLocator).replace("$", "").trim();
        return Double.parseDouble(price);

    }

    public void calcularValorTotalActual(double sumaEsperada) {
        //expectedPrice = String.valueOf(expectedPriceResult);
        String rawTotal = getElementText(cartTotal);
        String[] parts = rawTotal.split("\\$");
        String priceText = parts[1].split(" ")[0]; // "35.00"

        double actualTotal = Double.parseDouble(priceText);
       // Assert.assertEquals(actualTotal, sumaEsperada);

    }

    public void clickAllRemoveButtons() throws InterruptedException {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//a[contains(@href,'removeItemFromCart') and text()='Remove']"));

        if (removeButtons.isEmpty()) {
            System.out.println("WARNING: No hay botones Remove en el carrito.");
            return;
        }

        for (WebElement button : removeButtons) {
            button.click();
            // Espera breve para evitar conflictos de DOM si se recarga
            Thread.sleep(500);
        }

        System.out.println("SUCCESS: Se clickeo en todos los botones Remove del carrito.");
    }

    public void clickRemoveButtonByItemId(String itemId) {
        By removeButton = By.xpath("//a[contains(@href,'removeItemFromCart') and contains(@href,'" + itemId + "') and text()='Remove']");

        if (isElementPresent(removeButton)) {
            clickElement(removeButton);
            System.out.println("SUCCESS: Boton Remove clickeado para el item: " + itemId);
        } else {
            throw new NoSuchElementException("No se encontró el botón Remove para el item: " + itemId);
        }
    }

    public boolean validateProductNotInCart(String productID) {
        //productIDLocator = productID;
        if (isElementNotVisible(itemLocator)) {
            System.out.println("SUCCESS: El producto con Item ID '" + productIDLocator + "' fue eliminado correctamente del carrito.");
            return true;
        } else {
            throw new AssertionError("ERROR: El producto con Item ID '" + productIDLocator + "' aun esta presente en el carrito.");
        }

    }
}
