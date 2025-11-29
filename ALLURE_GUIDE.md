# 📊 Guía Completa de Allure Report - PetStore Automation Framework

## 📑 Índice
- [¿Qué es Allure Report?](#qué-es-allure-report)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [Generación de Reportes](#generación-de-reportes)
- [Navegación por el Reporte](#navegación-por-el-reporte)
- [Características Avanzadas](#características-avanzadas)
- [Troubleshooting](#troubleshooting)
- [Ejemplos Prácticos](#ejemplos-prácticos)

---

## 🎯 ¿Qué es Allure Report?

Allure Report es un framework de reportes de código abierto que genera reportes HTML interactivos y visuales para pruebas automatizadas.

### Características Principales del Reporte

✅ **Dashboard Interactivo** con métricas en tiempo real
✅ **Navegación por Scenarios** - Ver cada test con sus steps
✅ **Gráficos y Métricas** - Pass/Fail rate, duración, tendencias
✅ **Screenshots Automáticos** - Capturas en fallos
✅ **Categorización de Errores** - Clasificación automática de defectos
✅ **Histórico** - Comparación entre ejecuciones
✅ **Filtros y Búsqueda** - Encuentra tests específicos rápidamente

---

## 🔧 Instalación y Configuración

### ¿Qué está ya configurado en tu proyecto?

Tu proyecto **YA TIENE** Allure completamente configurado:

1. ✅ **Plugin de Allure** en `build.gradle`
2. ✅ **Dependencias** de Allure para Cucumber
3. ✅ **TestRunner** con integración de Allure
4. ✅ **Hooks** para screenshots automáticos
5. ✅ **Categorías** de errores predefinidas
6. ✅ **Propiedades** de Allure configuradas

### Requisitos Previos

Solo necesitas tener instalado:
- ✅ Java 8 o superior
- ✅ Gradle (ya incluido en el proyecto)

**NO necesitas instalar Allure CLI** - El plugin de Gradle lo descarga automáticamente.

---

## ▶️ Ejecución de Pruebas

### Opción 1: Ejecutar Tests con Allure (Recomendado)

```bash
# Windows PowerShell
.\gradlew testWithAllure

# Windows CMD
gradlew.bat testWithAllure

# Linux/Mac
./gradlew testWithAllure
```

Este comando:
- ✅ Limpia resultados anteriores
- ✅ Ejecuta todos los tests de Cucumber
- ✅ Genera resultados en `build/allure-results`
- ✅ Muestra resumen en consola

### Opción 2: Ejecutar Tests Normales

```bash
.\gradlew test
```

También genera resultados de Allure, pero sin limpiar los anteriores.

### Opción 3: Ejecutar Tests con Tags Específicos

Edita `CucumberTestRunner.java` y descomenta/modifica la línea de tags:

```java
tags = "@smoke"  // Solo ejecutar tests con @smoke
tags = "@login or @register"  // Ejecutar tests de login o register
tags = "not @wip"  // Excluir tests marcados como @wip (Work In Progress)
```

Luego ejecuta:
```bash
.\gradlew testWithAllure
```

---

## 📊 Generación de Reportes

### 1. Ver Reporte HTML Interactivo (Modo Servidor)

Después de ejecutar los tests:

```bash
.\gradlew allureServe
```

**¿Qué hace este comando?**
- ✅ Genera el reporte HTML completo
- ✅ Inicia un servidor local
- ✅ Abre automáticamente el reporte en tu navegador
- ✅ URL: http://localhost:xxxxx

**Ventaja**: Reporte dinámico con todas las características interactivas.

### 2. Generar Reporte HTML Estático

Si quieres un reporte que puedas compartir o abrir sin servidor:

```bash
# Generar reporte estático
.\gradlew allureReport

# El reporte estará en: build/reports/allure-report/index.html
```

Abre `index.html` directamente en tu navegador.

### 3. Limpiar Resultados Anteriores

```bash
.\gradlew cleanAllureResults
```

Útil cuando quieres empezar desde cero.

---

## 🧭 Navegación por el Reporte

### Vista Principal - Overview

Al abrir el reporte verás:

```
┌─────────────────────────────────────────────────────┐
│  🏠 OVERVIEW                                         │
├─────────────────────────────────────────────────────┤
│                                                      │
│  📊 Total Tests: 25                                 │
│  ✅ Passed: 20 (80%)                                │
│  ❌ Failed: 3 (12%)                                 │
│  ⏭️ Skipped: 2 (8%)                                 │
│                                                      │
│  [Gráfico de Torta]    [Gráfico de Barras]         │
│                                                      │
│  ⏱️ Duración Total: 5m 30s                          │
│  📅 Fecha: 2025-11-29 16:00                         │
└─────────────────────────────────────────────────────┘
```

### Secciones del Menú Lateral

#### 1. 📊 **Overview**
- Resumen general de la ejecución
- Gráficos de pass/fail rate
- Métricas de duración
- Tendencias (si hay histórico)

#### 2. 📂 **Suites**
- Organización por archivos `.feature`
- Ejemplo:
  ```
  📁 Login
    └── ✅ login.feature (2 tests)
  📁 Register  
    └── ❌ Register.feature (5 tests, 1 failed)
  📁 PetManagement
    ├── ✅ CatalogNavigation.feature
    ├── ✅ PetSelect.feature
    └── ✅ ShoppingCart.feature
  ```

#### 3. 📋 **Behaviors**
- Organización por Features y Scenarios
- Vista estilo BDD
- Muestra estructura de Gherkin

#### 4. 📈 **Graphs**
- Status Chart (pass/fail distribution)
- Severity Chart (P0, P1, P2, etc.)
- Duration Chart (tests más lentos)
- Retry Trend (si hay reintentos)

#### 5. ⏱️ **Timeline**
- Vista cronológica de ejecución
- Muestra tests ejecutados en paralelo
- Útil para identificar cuellos de botella

#### 6. 🗂️ **Categories**
- Clasificación automática de errores
- Categorías definidas en `categories.json`:
  - Product Defects (errores del producto)
  - Test Defects (errores de automatización)
  - Timeout Issues
  - Browser Issues
  - Environment Issues

### Navegación dentro de un Test

Al hacer click en un test individual verás:

```
┌─────────────────────────────────────────────────────┐
│  Scenario: Valid user login                         │
│  Feature: Login                                      │
│  Status: ✅ PASSED                                   │
│  Duration: 8.5s                                      │
├─────────────────────────────────────────────────────┤
│  STEPS:                                              │
│  ✅ Given Access to the page "..."      (1.2s)      │
│  ✅ And Verify that "Sign In" is visible (0.5s)     │
│  ✅ And The "Login" button is clickable  (0.3s)     │
│  ✅ When I click in the "Login" button   (0.8s)     │
│  ✅ And Access to the user "j2ee"        (0.4s)     │
│  ✅ And Access to the password "j2ee"    (0.3s)     │
│  ✅ Then Verify successful login         (5.0s)     │
├─────────────────────────────────────────────────────┤
│  📎 ATTACHMENTS: Ninguno (test pasó)                │
└─────────────────────────────────────────────────────┘
```

Para un test **FALLIDO**:

```
┌─────────────────────────────────────────────────────┐
│  Scenario: Invalid user login                       │
│  Status: ❌ FAILED                                   │
│  Duration: 4.2s                                      │
├─────────────────────────────────────────────────────┤
│  STEPS:                                              │
│  ✅ Given Access to the page "..."                  │
│  ✅ When I enter invalid credentials                │
│  ❌ Then I should see error message                 │
│                                                      │
│  ERROR MESSAGE:                                      │
│  AssertionError: Expected error message not found   │
│  at LoginFeatureSteps.verifyError(Line 45)          │
├─────────────────────────────────────────────────────┤
│  📎 ATTACHMENTS:                                     │
│  🖼️ Screenshot en Fallo                             │
│     [Imagen del momento del fallo]                   │
└─────────────────────────────────────────────────────┘
```

---

## 🚀 Características Avanzadas

### 1. Screenshots Automáticos en Fallos

**Ya está configurado** en tu proyecto mediante `Hooks.java`.

Cuando un test falla:
- ✅ Captura screenshot automáticamente
- ✅ Lo adjunta al reporte de Allure
- ✅ Aparece en la sección "Attachments" del test

### 2. Categorización de Errores

El archivo `categories.json` clasifica automáticamente los errores:

```json
{
  "Product Defects": "Errores del producto (AssertionError)",
  "Test Defects": "Errores de automatización (NullPointer, NoSuchElement)",
  "Timeout Issues": "Problemas de timeout",
  "Browser Issues": "Problemas del navegador/driver",
  "Environment Issues": "Problemas de red/conexión"
}
```

### 3. Filtros y Búsqueda

En el reporte puedes:
- 🔍 Buscar por nombre de test
- 🏷️ Filtrar por status (Passed/Failed/Broken/Skipped)
- 📂 Filtrar por suite o feature
- ⚠️ Filtrar por categoría de error
- ⏱️ Ordenar por duración

### 4. Histórico de Ejecuciones

Para habilitar el histórico:

```bash
# Copia los resultados anteriores a la carpeta history
mkdir -p build/allure-results/history
cp -r build/reports/allure-report/history/* build/allure-results/history/

# Ejecuta las nuevas pruebas
.\gradlew testWithAllure

# Genera el reporte (incluirá tendencias)
.\gradlew allureServe
```

El reporte mostrará:
- 📈 Gráfico de tendencias (pass rate over time)
- 📊 Comparación entre ejecuciones
- 🔄 Retries de tests que fallaron

### 5. Exportar Reporte

Para compartir el reporte:

```bash
# Generar reporte estático
.\gradlew allureReport

# Comprimir la carpeta
Compress-Archive -Path build\reports\allure-report -DestinationPath allure-report.zip

# Enviar allure-report.zip por email o subirlo a servidor
```

El receptor solo necesita:
1. Descomprimir el archivo
2. Abrir `index.html` en navegador

---

## 🔧 Troubleshooting

### Problema 1: "No se genera el reporte"

**Síntoma**: Al ejecutar `allureServe` no se abre nada.

**Solución**:
```bash
# Verificar que hay resultados
dir build\allure-results

# Si está vacío, ejecuta primero los tests
.\gradlew testWithAllure

# Luego genera el reporte
.\gradlew allureServe
```

### Problema 2: "El reporte está vacío"

**Síntoma**: El reporte se abre pero no hay tests.

**Soluciones**:
1. Verifica que el plugin de Allure está configurado en `CucumberTestRunner.java`:
   ```java
   plugin = {
       "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
   }
   ```

2. Verifica que los tests se están ejecutando:
   ```bash
   .\gradlew test --info
   ```

### Problema 3: "Screenshots no aparecen"

**Síntoma**: Los tests fallan pero no hay screenshots adjuntos.

**Verificaciones**:
1. ¿Está la clase `Hooks.java` en el paquete `hooks`?
2. ¿El `glue` del runner incluye `"hooks"`?
   ```java
   glue = {"Steps", "hooks"}
   ```
3. ¿El driver está inicializado cuando falla el test?

### Problema 4: "Puerto en uso"

**Síntoma**: Error `Port already in use` al ejecutar `allureServe`.

**Solución**:
```bash
# Detener el servidor anterior (Ctrl+C en la terminal)
# O cambiar el puerto
.\gradlew allureServe --port 8080
```

### Problema 5: "Categorías no aparecen"

**Solución**:
```bash
# Copiar categories.json a la carpeta de resultados
copy src\test\resources\categories.json build\allure-results\

# Regenerar reporte
.\gradlew allureServe
```

---

## 💡 Ejemplos Prácticos

### Ejemplo 1: Primera Ejecución

```bash
# 1. Ejecutar tests con Allure
.\gradlew testWithAllure

# Output esperado:
# ✅ Tests ejecutados. Resultados guardados en: build/allure-results
# 📊 Para ver el reporte ejecuta: gradle allureServe

# 2. Ver reporte
.\gradlew allureServe

# Se abrirá automáticamente en: http://localhost:XXXXX
```

### Ejemplo 2: Ejecutar solo tests de Login

```java
// En CucumberTestRunner.java, descomentar:
tags = "@login"
```

```bash
.\gradlew testWithAllure
.\gradlew allureServe
```

### Ejemplo 3: Comparar dos ejecuciones

```bash
# Primera ejecución
.\gradlew testWithAllure
.\gradlew allureReport

# Guardar histórico
mkdir build\allure-results\history
xcopy /E build\reports\allure-report\history build\allure-results\history

# Segunda ejecución (después de cambios en código)
.\gradlew testWithAllure

# Ver reporte con tendencias
.\gradlew allureServe
```

### Ejemplo 4: Generar reporte para compartir

```bash
# Ejecutar tests
.\gradlew testWithAllure

# Generar reporte estático
.\gradlew allureReport

# El reporte está en: build\reports\allure-report\index.html

# Comprimir para enviar
Compress-Archive -Path build\reports\allure-report -DestinationPath allure-report-$(Get-Date -Format 'yyyy-MM-dd').zip
```

---

## 📋 Comandos de Referencia Rápida

```bash
# ====== EJECUCIÓN DE TESTS ======
.\gradlew testWithAllure          # Ejecutar tests + limpiar resultados anteriores
.\gradlew test                    # Ejecutar tests sin limpiar
.\gradlew cleanAllureResults      # Solo limpiar resultados

# ====== GENERACIÓN DE REPORTES ======
.\gradlew allureServe             # Generar y abrir reporte en navegador (modo servidor)
.\gradlew allureReport            # Generar reporte estático (HTML)

# ====== COMBINACIONES ÚTILES ======
.\gradlew clean testWithAllure allureServe     # Full cycle: limpiar, ejecutar, ver reporte
.\gradlew testWithAllure --tests "*LoginTest"  # Ejecutar solo tests específicos

# ====== DEBUGGING ======
.\gradlew test --info             # Ejecución con logs detallados
.\gradlew test --debug            # Ejecución con máximo detalle
.\gradlew dependencies            # Ver todas las dependencias
```

---

## 📊 Interpretación de Métricas

### Pass Rate (Tasa de Éxito)

```
✅ 90-100% = Excelente
⚠️ 80-89% = Aceptable (requiere atención)
❌ <80% = Crítico (requiere acción inmediata)
```

### Duración de Tests

```
⚡ <5s = Rápido
⏱️ 5-15s = Normal
⚠️ 15-30s = Lento (considerar optimizar)
❌ >30s = Muy lento (requiere refactorización)
```

### Categorías de Errores

Revisa la distribución:
- **Product Defects dominante** → Bugs en la aplicación
- **Test Defects dominante** → Problemas en automatización
- **Timeout Issues dominante** → Problemas de performance o waits
- **Browser/Environment Issues** → Problemas de infraestructura

---

## 🎯 Mejores Prácticas

1. ✅ **Ejecuta `cleanAllureResults` antes de ejecuciones importantes**
   - Evita confusión con resultados antiguos

2. ✅ **Revisa el reporte inmediatamente después de la ejecución**
   - Los problemas se identifican más rápido

3. ✅ **Usa tags para organizar tests**
   - `@smoke`, `@regression`, `@critical`

4. ✅ **Mantén el histórico para ver tendencias**
   - Ayuda a identificar tests flaky

5. ✅ **Comparte reportes estáticos con stakeholders**
   - Más fácil que explicar logs de consola

6. ✅ **Revisa la sección "Categories" regularmente**
   - Identifica patrones de fallos

---

## 🔗 Recursos Adicionales

- **Documentación Oficial**: https://docs.qameta.io/allure/
- **GitHub**: https://github.com/allure-framework
- **Ejemplos**: https://demo.qameta.io/allure/

---

**Última Actualización**: 2025-11-29
**Versión del Framework**: 1.0.0
**Allure Version**: 2.29.0
