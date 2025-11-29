# 🚀 Quick Start - Allure Report

## ✅ ¡Allure ya está configurado!

Tu proyecto tiene **TODA** la configuración de Allure lista para usar.

## 📝 Pasos para ver tu primer reporte

### 1️⃣ Ejecutar Tests
```powershell
.\gradlew testWithAllure
```

**Tiempo estimado**: 2-5 minutos (dependiendo de cuántos tests tengas)

### 2️⃣ Ver Reporte
```powershell
.\gradlew allureServe
```

**El reporte se abrirá automáticamente en tu navegador** 🎉

---

## 🎯 ¿Qué verás en el reporte?

### Dashboard Principal
```
┌──────────────────────────────────┐
│  📊 OVERVIEW                     │
├──────────────────────────────────┤
│  Total Tests: X                  │
│  ✅ Passed: X%                   │
│  ❌ Failed: X%                   │
│  ⏭️ Skipped: X%                  │
│                                  │
│  [Gráficos de torta y barras]   │
└──────────────────────────────────┘
```

### Navegación
- **Suites**: Tests organizados por archivos `.feature`
- **Behaviors**: Vista BDD (Features → Scenarios → Steps)
- **Graphs**: Gráficos de distribución y duración
- **Timeline**: Línea temporal de ejecución
- **Categories**: Errores clasificados automáticamente

### Para cada Test Fallido
- ❌ Mensaje de error detallado
- 🖼️ Screenshot automático del momento del fallo
- 📝 Stack trace completo
- ⏱️ Duración de cada step

---

## 📋 Comandos Esenciales

```powershell
# Ejecutar tests + limpiar resultados anteriores
.\gradlew testWithAllure

# Ver reporte en navegador (modo interactivo)
.\gradlew allureServe

# Generar reporte HTML estático (para compartir)
.\gradlew allureReport

# Limpiar resultados anteriores
.\gradlew cleanAllureResults

# Full cycle: limpiar → ejecutar → ver reporte
.\gradlew clean testWithAllure allureServe
```

---

## 🎓 Tips Rápidos

### ✅ Para ejecutar solo ciertos tests

Edita `src/test/java/runners/CucumberTestRunner.java`:

```java
// Descomentar y modificar esta línea:
tags = "@smoke"  // Solo tests con tag @smoke
```

Luego ejecuta:
```powershell
.\gradlew testWithAllure
```

### ✅ Para compartir el reporte

```powershell
# Generar reporte estático
.\gradlew allureReport

# El reporte está en:
# build\reports\allure-report\index.html

# Comprimir para enviar
Compress-Archive -Path build\reports\allure-report -DestinationPath allure-report.zip
```

### ✅ Screenshots automáticos

**Ya está configurado** ✅

Cuando un test falla:
1. Se captura screenshot automáticamente
2. Se adjunta al reporte
3. Aparece en la sección "Attachments"

---

## 🔧 Troubleshooting Rápido

### ❌ Error: "No tests found"
**Solución**: Asegúrate de que hay archivos `.feature` en `src/test/resources/`

### ❌ Error: "Port already in use"
**Solución**: Presiona `Ctrl+C` en la terminal para detener el servidor anterior

### ❌ El reporte está vacío
**Solución**: 
```powershell
# Verifica que hay resultados
dir build\allure-results

# Si está vacío, ejecuta primero los tests
.\gradlew testWithAllure
```

---

## 📚 Más Información

- **Guía Completa**: [ALLURE_GUIDE.md](./ALLURE_GUIDE.md)
- **Documentación Oficial**: https://docs.qameta.io/allure/
- **Demo Online**: https://demo.qameta.io/allure/

---

## 🎉 ¡Listo para empezar!

```powershell
# Copia y pega estos dos comandos:
.\gradlew testWithAllure
.\gradlew allureServe
```

**En 2 minutos tendrás tu reporte HTML interactivo funcionando** 🚀

---

**Última actualización**: 2025-11-29
**Versión de Allure**: 2.29.0
