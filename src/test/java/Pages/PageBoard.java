package Pages;

import Configuration.Configurations;
import org.openqa.selenium.By;
import org.testng.Assert;

//Clase base para navegación general, validación de menú superior, verificación de carga, y validación de URL.

public class PageBoard extends Configurations {
    private By elemFish = By.cssSelector("#QuickLinks > a:nth-child(1)");
    private By elemDog = By.cssSelector("#QuickLinks > a:nth-child(3)");
    private By elemReptiles = By.cssSelector("#QuickLinks > a:nth-child(5)");
    private By elemCats = By.cssSelector("#QuickLinks > a:nth-child(7)");
    private By elemBirds = By.cssSelector("#QuickLinks > a:nth-child(9)");
    private By imageLocatorFish = By.xpath("//img[@src='../images/sm_fish.gif']");
    private By imageLocatorDogs = By.xpath("//img[@src='../images/sm_dogs.gif']");
    private By imageLocatorReptiles = By.xpath("//img[@src='../images/sm_reptiles.gif']");
    private By imageLocatorCats = By.xpath("//img[@src='../images/sm_cats.gif']");
    private By imageLocatorBirds = By.xpath("//img[@src='../images/sm_birds.gif']");
    private By returnToMainPage = By.linkText("Return to Main Menu");

    public void accessPageURL(String url) {
        accederAPagina(url);
    }

    public void getMenuName(String petType) {
        By imageLocator = null;
        switch (petType) {
            case "Fish":
                imageLocator = imageLocatorFish;
                break;
            case "Dogs":
                imageLocator = imageLocatorDogs;
                break;
            case "Reptiles":
                imageLocator = imageLocatorReptiles;
                break;
            case "Cats":
                imageLocator = imageLocatorCats;
                break;
            case "Birds":
                imageLocator = imageLocatorBirds;
                break;
            default:
                System.out.println("La mascota no existe");
                break;
        }
        if (imageLocator != null) {
            String nombreImg = getNombreImagen(imageLocator);
            Assert.assertEquals(petType.toLowerCase(), nombreImg.toLowerCase());
        }


    }

    public void ClickOnMenuOption(String petName) {
        switch (petName) {
            case "Fish":
                clickElement(elemFish);
                break;
            case "Dogs":
                clickElement(elemDog);
                break;
            case "Reptiles":
                clickElement(elemReptiles);
                break;
            case "Cats":
                clickElement(elemCats);
                break;
            case "Birds":
                clickElement(elemBirds);
                break;
            default:
                System.out.println("La mascota no existe");
                break;
        }
    }

    public void validateURL(String currentUrl) {
        String pageURL = getCurrentUrlResult().toString();
        Assert.assertTrue(pageURL.contains(currentUrl), "No es la página correcta.");
    }

    //metodo para validar que la página cargo completamente
    public void verifyCatalogLoaded() {
        Assert.assertTrue(driver.getTitle().contains("JPetStore Demo"));
    }

    //metodo para hacer click en el elemento Return to main menu
    public void goToMainPage(){
        clickElement(returnToMainPage);
    }

}
