package Pages;

import Configuration.Configurations;
import Interface.PetNavigationProdIdManager;
import org.openqa.selenium.By;

public class DogsProductIdPage extends Configurations implements PetNavigationProdIdManager {

    private By dogsProductID_1 = By.xpath("//a[normalize-space()='K9-BD-01']");

    @Override
    public void seleccionarProductId(String petProdId) {
        clickElement(dogsProductID_1);
    }

   @Override
    public void obtenerProductId() {

    }

    @Override
    public void obtenerPetName() {

    }

}
