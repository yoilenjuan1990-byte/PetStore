package Pages;

import Configuration.Configurations;
import Interface.PetNavigationItemIdManager;
import Interface.PricePetManager;
import org.openqa.selenium.By;

//Página de detalle del producto Fish. Guarda precio, y tiene métodos para agregar al carrito. Implementa PetNavigationItemIdManager y PricePetManager.

public class FishItemIdPage extends Configurations implements PetNavigationItemIdManager, PricePetManager {

    private By fishItemPrice_1 = By.xpath("//tbody/tr[2]/td[4]");
    private double precioGuardado1;
    private By addToCartButton = By.linkText("Add to Cart");
    String fishPrice = null;

    @Override
    public void seleccionarItemId() {

    }

    @Override
    public void clickOnAddToCart() {
        clickElement(addToCartButton);
    }

    @Override
    public void guardarPrecio(String itID) {
        if (itID.equals("EST-1")){
            fishPrice = getElementText(fishItemPrice_1);
        }

        fishPrice = fishPrice.replace("$", "").trim();
        this.precioGuardado1 = Double.parseDouble(fishPrice);
    }

    @Override
    public double getPrecioGuardado() {
        return this.precioGuardado1;
    }

    @Override
    public void obtenerItemId() {

    }
}
