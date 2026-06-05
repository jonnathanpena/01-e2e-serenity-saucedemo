#!/bin/bash

# Script para preparar el repositorio para GitHub
# Este script ayuda a verificar los archivos que se subirán y crear el commit inicial

echo "=== Preparando repositorio para GitHub ==="
echo ""

# Verificar que estamos en el directorio correcto
if [ ! -f "pom.xml" ]; then
    echo "❌ Error: No estás en el directorio raíz del proyecto (no se encuentra pom.xml)"
    exit 1
fi

echo "✅ Directorio correcto detectado"
echo ""

# Mostrar contenido del .gitignore
echo "=== Contenido de .gitignore ==="
cat .gitignore
echo ""

# Mostrar archivos que se ignorarán
echo "=== Archivos/carpetas que serán ignorados por .gitignore ==="
git check-ignore -v $(git ls-files) 2>/dev/null || echo "No hay archivos rastreados que coincidan con .gitignore"
echo ""

# Mostrar archivos que se subirán (sin contar los ignorados)
echo "=== Archivos que se agregarán al commit ==="
git ls-files --others --exclude-standard
echo ""

# Preguntar si desea proceder
echo "¿Deseas agregar todos los archivos y crear el commit inicial? (s/n)"
read -r respuesta

if [ "$respuesta" = "s" ] || [ "$respuesta" = "S" ]; then
    echo ""
    echo "Agregando archivos..."
    git add .
    
    echo ""
    echo "Archivos agregados:"
    git status --short
    
    echo ""
    echo "=== Creando commit inicial ==="
    git commit -m "feat: proyecto de automatización E2E con Serenity Screenplay

- Java 21 + Maven Wrapper
- Serenity BDD 5.3.10 con patrón Screenplay
- Cucumber 7 con Gherkin en español
- Selenium 4 con auto-descarga de chromedriver
- Features para el flujo de compra en SauceDemo"
    
    echo ""
    echo "✅ Commit inicial creado exitosamente"
    echo ""
    echo "=== Siguientes pasos ==="
    echo "1. Crea un repositorio nuevo en GitHub"
    echo "2. Agrega el remote con:"
    echo "   git remote add origin https://github.com/jonnathanpena/01-e2e-serenity-saucedemo.git"
    echo "3. Sube el código con:"
    echo "   git branch -M main"
    echo "   git push -u origin main"
else
    echo "❌ Operación cancelada por el usuario"
fi
