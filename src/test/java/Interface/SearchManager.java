package Interface;

//Interface para manejar la funcionalidad de búsqueda de productos

public interface SearchManager {

    /**
     * Realiza una búsqueda con la palabra clave proporcionada
     */
    void performSearch(String keyword);

    /**
     * Verifica si los resultados de búsqueda contienen un producto específico
     */
    boolean searchResultsContainProduct(String productId);

    /**
     * Verifica si los resultados de búsqueda contienen texto específico
     */
    boolean searchResultsContainText(String text);

    /**
     * Verifica si hay resultados de búsqueda
     */
    boolean hasSearchResults();

    /**
     * Verifica si se muestra el mensaje de "sin resultados"
     */
    boolean hasNoResultsMessage();

    /**
     * Limpia el campo de búsqueda
     */
    void clearSearchField();

    /**
     * Obtiene el texto del campo de búsqueda actual
     */
    String getSearchKeyword();
}
