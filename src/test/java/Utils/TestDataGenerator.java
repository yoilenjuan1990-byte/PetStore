package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Clase utilitaria para generar datos de prueba aleatorios y únicos
 * Útil para evitar conflictos con datos existentes en las pruebas
 */
public class TestDataGenerator {

    private static final Random random = new Random();

    // Arrays de datos de muestra
    private static final String[] FIRST_NAMES = {
            "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona", "George", "Hannah",
            "Ian", "Julia", "Kevin", "Laura", "Michael", "Nancy", "Oliver", "Patricia"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    };

    private static final String[] CITIES = {
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia",
            "San Antonio", "San Diego", "Dallas", "San Jose", "Austin", "Jacksonville",
            "Fort Worth", "Columbus", "Charlotte", "Seattle", "Denver", "Boston", "Miami"
    };

    private static final String[] STATES = {
            "NY", "CA", "IL", "TX", "AZ", "PA", "FL", "OH", "NC", "WA", "CO", "MA", "GA"
    };

    private static final String[] STREETS = {
            "Main St", "Oak Ave", "Pine St", "Maple Dr", "Cedar Ln", "Elm Blvd",
            "Birch Way", "Walnut Ct", "Cherry Rd", "Willow Pl", "Spruce Ave"
    };

    /**
     * Genera un timestamp único basado en la fecha y hora actual
     * Formato: yyyyMMddHHmmss
     */
    public static String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * Genera un username único con timestamp
     * Formato: user_20231015143022
     */
    public static String generateUniqueUsername() {
        return "user_" + generateTimestamp();
    }

    /**
     * Genera un username único con prefijo personalizado
     * Formato: prefix_20231015143022
     */
    public static String generateUniqueUsername(String prefix) {
        return prefix + "_" + generateTimestamp();
    }

    /**
     * Genera un email único basado en username con timestamp
     * Formato: user_20231015143022@testmail.com
     */
    public static String generateUniqueEmail() {
        return "user_" + generateTimestamp() + "@testmail.com";
    }

    /**
     * Genera un email único con dominio personalizado
     */
    public static String generateUniqueEmail(String domain) {
        return "user_" + generateTimestamp() + "@" + domain;
    }

    /**
     * Genera un nombre aleatorio de la lista predefinida
     */
    public static String generateRandomFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    /**
     * Genera un apellido aleatorio de la lista predefinida
     */
    public static String generateRandomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    /**
     * Genera un nombre completo aleatorio
     */
    public static String generateRandomFullName() {
        return generateRandomFirstName() + " " + generateRandomLastName();
    }

    /**
     * Genera un número de teléfono aleatorio
     * Formato: 555-XXXX (donde X es un dígito aleatorio)
     */
    public static String generateRandomPhone() {
        int number = 1000 + random.nextInt(9000); // Genera número entre 1000-9999
        return "555-" + number;
    }

    /**
     * Genera una dirección aleatoria
     * Formato: [número] [calle]
     */
    public static String generateRandomAddress() {
        int streetNumber = 100 + random.nextInt(900); // Número entre 100-999
        String street = STREETS[random.nextInt(STREETS.length)];
        return streetNumber + " " + street;
    }

    /**
     * Genera una ciudad aleatoria de la lista predefinida
     */
    public static String generateRandomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }

    /**
     * Genera un estado aleatorio de la lista predefinida
     */
    public static String generateRandomState() {
        return STATES[random.nextInt(STATES.length)];
    }

    /**
     * Genera un código postal aleatorio
     * Formato: XXXXX (5 dígitos)
     */
    public static String generateRandomZipCode() {
        int zip = 10000 + random.nextInt(90000); // Genera número entre 10000-99999
        return String.valueOf(zip);
    }

    /**
     * Genera una contraseña segura aleatoria
     * Contiene mayúsculas, minúsculas, números y caracteres especiales
     */
    public static String generateSecurePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    /**
     * Genera una contraseña débil (solo números o muy corta)
     */
    public static String generateWeakPassword() {
        return "12345";
    }

    /**
     * Genera un email con formato inválido (sin @)
     */
    public static String generateInvalidEmail() {
        return "invalid.email.com";
    }

    /**
     * Genera un objeto completo de datos de usuario para registro
     */
    public static UserTestData generateCompleteUserData() {
        return new UserTestData(
                generateUniqueUsername(),
                generateSecurePassword(),
                generateRandomFirstName(),
                generateRandomLastName(),
                generateUniqueEmail(),
                generateRandomPhone(),
                generateRandomAddress(),
                generateRandomCity(),
                generateRandomState(),
                generateRandomZipCode(),
                "USA"
        );
    }

    /**
     * Clase interna para encapsular datos de usuario
     */
    public static class UserTestData {
        public String username;
        public String password;
        public String firstName;
        public String lastName;
        public String email;
        public String phone;
        public String address;
        public String city;
        public String state;
        public String zip;
        public String country;

        public UserTestData(String username, String password, String firstName,
                            String lastName, String email, String phone, String address,
                            String city, String state, String zip, String country) {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.country = country;
        }
    }
}
