package hooks;

import Configuration.Configurations;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Cucumber Hooks para integración con Allure Report
 * 
 * Funcionalidades:
 * - Captura automática de screenshots en casos de fallo
 * - Adjunta screenshots a los reportes de Allure
 * - Logging de inicio y fin de scenarios
 * - Cleanup del WebDriver después de cada scenario
 */
public class Hooks extends Configurations {
    
    /**
     * Hook que se ejecuta ANTES de cada Scenario
     * Se usa para logging y preparación
     * 
     * @param scenario Objeto Scenario de Cucumber con información del test
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🚀 INICIANDO SCENARIO: " + scenarioName);
        System.out.println("📋 Tags: " + scenario.getSourceTagNames());
        System.out.println("=".repeat(80) + "\n");
        
        // Agregar información del scenario a Allure
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setName(scenarioName);
        });
    }
    
    /**
     * Hook que se ejecuta DESPUÉS de cada Scenario
     * Captura screenshots si el test falla y limpia recursos
     * 
     * @param scenario Objeto Scenario con estado final del test
     */
    @After
    public void afterScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        String status = scenario.getStatus().toString();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📊 FINALIZANDO SCENARIO: " + scenarioName);
        System.out.println("✓ Estado: " + status);
        
        // Si el scenario falló, capturar screenshot
        if (scenario.isFailed()) {
            System.out.println("❌ SCENARIO FALLIDO - Capturando screenshot...");
            
            try {
                // Captura screenshot y lo adjunta al reporte de Allure
                byte[] screenshot = takeScreenshot();
                
                // Adjuntar a Cucumber (aparece en reportes de Cucumber)
                scenario.attach(screenshot, "image/png", scenarioName + "_failed");
                
                // Adjuntar a Allure (aparece en reportes de Allure)
                Allure.addAttachment(
                    scenarioName + " - Screenshot en Fallo", 
                    "image/png", 
                    new ByteArrayInputStream(screenshot), 
                    ".png"
                );
                
                System.out.println("✅ Screenshot capturado y adjunto al reporte");
                
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo capturar screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("✅ SCENARIO EXITOSO");
        }
        
        System.out.println("=".repeat(80) + "\n");
        
        // Limpiar el WebDriver si está activo
        try {
            if (driver != null) {
                // Nota: No cerrar el driver aquí si lo necesitas para múltiples scenarios
                // driver.quit();
                System.out.println("🧹 Recursos limpiados");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al limpiar recursos: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para capturar screenshots
     * Este método toma una captura de pantalla del WebDriver actual
     * 
     * @return byte[] con los datos de la imagen en formato PNG
     */
    private byte[] takeScreenshot() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver no está inicializado");
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
    /**
     * Método alternativo para adjuntar screenshots a Allure
     * Puede ser llamado manualmente desde los steps si necesitas capturas adicionales
     * 
     * @return byte[] con los datos de la imagen
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure() {
        return takeScreenshot();
    }
    
    /**
     * Método para adjuntar texto a los reportes de Allure
     * Útil para adjuntar logs, JSONs, o información adicional
     * 
     * @param text Texto a adjuntar
     * @return El mismo texto (requerido por la anotación @Attachment)
     */
    @Attachment(value = "Additional Info", type = "text/plain")
    public String attachText(String text) {
        return text;
    }
}
