package Pages;

import Configuration.Configurations;
import Interface.PetNavigationProdIdManager;
import org.openqa.selenium.By;

//Página específica para seleccionar el producto "FI-SW-01" de la categoría Fish. Implementa la interfaz PetNavigationProdIdManager.

public class FishProductIdPage extends Configurations implements PetNavigationProdIdManager {

    private By fishProductId_1 = By.xpath("//a[normalize-space()='FI-SW-01']");

    @Override
    public void seleccionarProductId(String petProdId) {
        clickElement(fishProductId_1);
    }

    @Override
    public void obtenerProductId() {

    }

    @Override
    public void obtenerPetName() {

    }
}
