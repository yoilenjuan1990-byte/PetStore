package Interface;

//Para guardar y manejar precios de productos.

public interface PricePetManager {

    void guardarPrecio(String itmID);

    double getPrecioGuardado();
}
