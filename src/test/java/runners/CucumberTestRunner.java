package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Cucumber TestNG Runner con integración de Allure Report
 * 
 * Este runner ejecuta todos los features de Cucumber y genera reportes de Allure automáticamente
 * 
 * Características:
 * - Ejecuta todos los archivos .feature en src/test/resources
 * - Genera reportes de Allure en build/allure-results
 * - Incluye pretty format para consola
 * - Soporta ejecución paralela con DataProvider
 * 
 * Uso:
 * - Desde Gradle: ./gradlew testWithAllure
 * - Desde TestNG: Click derecho -> Run As -> TestNG Test
 */
@CucumberOptions(
        features = "src/test/resources",              // Ubicación de archivos .feature
        glue = {"Steps", "hooks"},                     // Paquetes con Step Definitions y Hooks
        plugin = {
                "pretty",                              // Output formateado en consola
                "html:target/cucumber-reports.html",  // Reporte HTML básico de Cucumber
                "json:target/cucumber.json",           // JSON para otros reportes
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Plugin de Allure
        },
        monochrome = true,                             // Output legible en consola
        dryRun = false,                                // false = ejecuta tests, true = solo valida steps
        publish = false                                 // No publicar en Cucumber Reports online
        // tags = "@smoke"                             // Descomentar para ejecutar solo tests con tag específico
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    
    /**
     * DataProvider para ejecución paralela de scenarios
     * Si deseas ejecutar scenarios en paralelo, descomenta este método
     */
    @Override
    @DataProvider(parallel = false)  // Cambiar a true para ejecución paralela
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
