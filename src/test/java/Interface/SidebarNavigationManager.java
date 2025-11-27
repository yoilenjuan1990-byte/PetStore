package Interface;

//Interface para manejar la navegación del menú lateral del catálogo

public interface SidebarNavigationManager {

    /**
     * Verifica si el enlace de una categoría está visible en el sidebar
     */
    boolean isCategoryLinkVisible(String categoryName);

    /**
     * Navega a una categoría específica desde el sidebar
     */
    void navigateToCategory(String categoryName);

    /**
     * Obtiene la URL de la categoría actual
     */
    String getCurrentCategoryUrl();

    /**
     * Verifica si el sidebar está completamente cargado
     */
    boolean isSidebarLoaded();
}
