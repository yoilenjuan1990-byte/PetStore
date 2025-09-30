package Pages;

import Configuration.Configurations;
import Interface.PetNavigationItemIdManager;
import Interface.PricePetManager;
import org.openqa.selenium.By;

public class DogsItemIdPage extends Configurations implements PetNavigationItemIdManager, PricePetManager {
    private By dogItemPrice_1 = By.xpath("//tbody/tr[2]/td[4]");
    private double precioGuardado1;
    private By addToCartButton = By.linkText("Add to Cart");

    public double getPrecioGuardado1(){
        return this.precioGuardado1;
    }

    @Override
    public void seleccionarItemId() {

    }

    @Override
    public void clickOnAddToCart() {

    }

    @Override
    public void guardarPrecio() {
        String dogPrice = getElementText(dogItemPrice_1);
        dogPrice = dogPrice.replace("$", "").trim();
        this.precioGuardado1 = Double.parseDouble(dogPrice);
    }

    @Override
    public double getPrecioGuardado() {
        return this.precioGuardado1;
    }

    @Override
    public void obtenerItemId() {

    }
}
