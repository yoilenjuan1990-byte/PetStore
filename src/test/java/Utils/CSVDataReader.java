package Utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase utilitaria para leer datos de archivos CSV
 * Utiliza la librería OpenCSV (ya incluida en build.gradle)
 */
public class CSVDataReader {

    /**
     * Lee un archivo CSV y devuelve una lista de mapas
     * Cada mapa representa una fila, con las claves siendo los nombres de las columnas
     *
     * @param filePath Ruta completa al archivo CSV
     * @return Lista de mapas con los datos del CSV
     */
    public static List<Map<String, String>> readCSV(String filePath) {
        List<Map<String, String>> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();

            if (rows.isEmpty()) {
                System.out.println("El archivo CSV está vacío: " + filePath);
                return data;
            }

            // Primera fila contiene los headers
            String[] headers = rows.get(0);

            // Procesar las filas restantes (datos)
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < headers.length; j++) {
                    if (j < row.length) {
                        rowData.put(headers[j], row[j]);
                    } else {
                        rowData.put(headers[j], ""); // Campo vacío si no hay dato
                    }
                }

                data.add(rowData);
            }

            System.out.println("CSV leído exitosamente: " + filePath + " (" + data.size() + " registros)");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        } catch (CsvException e) {
            System.err.println("Error al parsear el CSV: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Lee un archivo CSV desde la ruta de resources
     *
     * @param resourcePath Ruta relativa dentro de src/test/resources
     * @return Lista de mapas con los datos del CSV
     */
    public static List<Map<String, String>> readCSVFromResources(String resourcePath) {
        try {
            String fullPath = CSVDataReader.class.getClassLoader()
                    .getResource(resourcePath).getPath();
            // Limpiar el path en Windows (eliminar el "/" inicial si existe)
            if (fullPath.startsWith("/") && fullPath.contains(":")) {
                fullPath = fullPath.substring(1);
            }
            return readCSV(fullPath);
        } catch (NullPointerException e) {
            System.err.println("No se encontró el archivo CSV en resources: " + resourcePath);
            return new ArrayList<>();
        }
    }

    /**
     * Obtiene una fila específica del CSV por índice
     *
     * @param filePath Ruta al archivo CSV
     * @param rowIndex Índice de la fila (0-based, sin contar el header)
     * @return Mapa con los datos de la fila, o null si no existe
     */
    public static Map<String, String> getRowByIndex(String filePath, int rowIndex) {
        List<Map<String, String>> data = readCSV(filePath);
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }

    /**
     * Filtra filas del CSV que cumplan con un criterio específico
     *
     * @param filePath Ruta al archivo CSV
     * @param columnName Nombre de la columna para filtrar
     * @param value Valor que debe tener la columna
     * @return Lista de filas que cumplen el criterio
     */
    public static List<Map<String, String>> filterRows(String filePath, String columnName, String value) {
        List<Map<String, String>> allData = readCSV(filePath);
        List<Map<String, String>> filtered = new ArrayList<>();

        for (Map<String, String> row : allData) {
            if (row.containsKey(columnName) && row.get(columnName).equals(value)) {
                filtered.add(row);
            }
        }

        return filtered;
    }

    /**
     * Convierte los datos del CSV en un objeto UserTestData
     *
     * @param rowData Mapa con los datos de una fila del CSV
     * @return Objeto UserTestData con los datos parseados
     */
    public static TestDataGenerator.UserTestData convertToUserData(Map<String, String> rowData) {
        return new TestDataGenerator.UserTestData(
                rowData.getOrDefault("username", ""),
                rowData.getOrDefault("password", ""),
                rowData.getOrDefault("firstName", ""),
                rowData.getOrDefault("lastName", ""),
                rowData.getOrDefault("email", ""),
                rowData.getOrDefault("phone", ""),
                rowData.getOrDefault("address1", ""),
                rowData.getOrDefault("city", ""),
                rowData.getOrDefault("state", ""),
                rowData.getOrDefault("zip", ""),
                rowData.getOrDefault("country", "USA")
        );
    }
}
