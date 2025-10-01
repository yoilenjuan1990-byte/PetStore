# Implementación de DataTable para Validación de Productos

## ✅ Implementación Realizada

### Archivo: `PetFeatureSteps.java`

Se implementó el método para validar múltiples productos usando Cucumber DataTable:

```java
@And("Validar que se muestren los siguientes productos")
public void validarQueSeMuestrenLosSiguientesProductos(DataTable dataTable) {
    // Obtener la lista de productos desde la tabla de datos
    List<Map<String, String>> productos = dataTable.asMaps(String.class, String.class);
    
    // Recorrer cada producto y validarlo en el carrito
    for (Map<String, String> producto : productos) {
        String productId = producto.get("Producto").replace("\"", ""); // Remover comillas
        cart.validateProductInCart(productId);
        System.out.println("✓ Producto validado en el carrito: " + productId);
    }
}
```

### Imports agregados:
```java
import io.cucumber.datatable.DataTable;
import java.util.Map;
```

---

## 📝 Uso en el Feature File

### Ejemplo en `ShoppingCart.feature`:

```gherkin
Scenario: Add 2 different pets products to Shopping cart
  When Click on superior menu "Fish"
  And Click on id product "FI-SW-01"
  And Save price on Item Id Page
  And Click on Add to Cart Button
  And Back to main page
  And Click on superior menu "Dogs"
  And Click on id product "K9-BD-01"
  And Save price on Item Id Page
  And Click on Add to Cart Button
  And Validar que se muestren los siguientes productos
    | Producto   |
    | "FI-SW-01" |
    | "K9-BD-01" |
```

---

## 🔄 Variantes de Implementación

### Opción 1: For tradicional con índice (implementado)
```java
for (Map<String, String> producto : productos) {
    String productId = producto.get("Producto").replace("\"", "");
    cart.validateProductInCart(productId);
}
```

### Opción 2: For tradicional con índice numérico
```java
for (int i = 0; i < productos.size(); i++) {
    String productId = productos.get(i).get("Producto").replace("\"", "");
    cart.validateProductInCart(productId);
    System.out.println("Validado producto " + (i + 1) + " de " + productos.size());
}
```

### Opción 3: forEach con lambda (moderna)
```java
productos.forEach(producto -> {
    String productId = producto.get("Producto").replace("\"", "");
    cart.validateProductInCart(productId);
});
```

### Opción 4: Stream API (más funcional)
```java
productos.stream()
    .map(producto -> producto.get("Producto").replace("\"", ""))
    .forEach(cart::validateProductInCart);
```

---

## 📊 Otras formas de DataTable

### Sin encabezado (lista simple):
```gherkin
And Validar que se muestren los siguientes productos
  | FI-SW-01 |
  | K9-BD-01 |
  | RP-SN-01 |
```

Implementación:
```java
@And("Validar que se muestren los siguientes productos")
public void validarQueSeMuestrenLosSiguientesProductos(List<String> productos) {
    for (String productId : productos) {
        cart.validateProductInCart(productId);
    }
}
```

### Múltiples columnas:
```gherkin
And Validar los siguientes productos con cantidad
  | Producto   | Cantidad | Precio |
  | FI-SW-01   | 1        | 16.50  |
  | K9-BD-01   | 2        | 18.50  |
```

Implementación:
```java
@And("Validar los siguientes productos con cantidad")
public void validarProductosConCantidad(DataTable dataTable) {
    List<Map<String, String>> productos = dataTable.asMaps();
    
    for (Map<String, String> producto : productos) {
        String productId = producto.get("Producto");
        int cantidad = Integer.parseInt(producto.get("Cantidad"));
        double precio = Double.parseDouble(producto.get("Precio"));
        
        cart.validateProductInCart(productId);
        cart.validateProductQuantity(productId, cantidad);
        cart.validateProductPrice(productId, precio);
    }
}
```

---

## 🐛 Troubleshooting

### Problema: Comillas en los valores
**Solución:** Usar `.replace("\"", "")` para removerlas.

### Problema: DataTable no reconocido
**Solución:** Verificar que el import sea:
```java
import io.cucumber.datatable.DataTable;
```

### Problema: NullPointerException
**Solución:** Verificar que la columna en el feature coincida exactamente con `producto.get("Producto")`.

---

## 💡 Mejora Recomendada

Para mayor robustez, considera agregar manejo de errores:

```java
@And("Validar que se muestren los siguientes productos")
public void validarQueSeMuestrenLosSiguientesProductos(DataTable dataTable) {
    List<Map<String, String>> productos = dataTable.asMaps(String.class, String.class);
    
    if (productos.isEmpty()) {
        throw new IllegalArgumentException("La tabla de productos está vacía");
    }
    
    for (int i = 0; i < productos.size(); i++) {
        Map<String, String> producto = productos.get(i);
        String productId = producto.get("Producto");
        
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Producto en fila " + (i + 1) + " está vacío");
        }
        
        productId = productId.replace("\"", "").trim();
        
        try {
            cart.validateProductInCart(productId);
            System.out.println("✓ [" + (i + 1) + "/" + productos.size() + "] Producto validado: " + productId);
        } catch (AssertionError e) {
            System.err.println("✗ Error validando producto: " + productId);
            throw e;
        }
    }
    
    System.out.println("✅ Todos los productos fueron validados exitosamente");
}
```

---

## 📚 Referencias

- [Cucumber DataTable Documentation](https://cucumber.io/docs/cucumber/api/#data-tables)
- [Cucumber Java Documentation](https://cucumber.io/docs/cucumber/api/#java)

---

**Fecha de implementación:** 2025-10-01  
**Autor:** Análisis automatizado del proyecto PetStore
