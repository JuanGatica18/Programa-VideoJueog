# Space Invasion - Guía de Instalación

## Requisitos Previos

Antes de instalar y ejecutar el juego, asegúrate de tener instalado:

- **Java JDK 17 o superior**
- **Git** (opcional, para clonar el repositorio)
- **Eclipse IDE** (recomendado para desarrollo Java)

---

##  Instalación

### Opción 1: Clonar desde GitHub

1. **Abre Git Bash o CMD** y navega a la carpeta donde quieres instalar el juego
2. **Clona el repositorio**:
```bash
git clone https://github.com/JuanGatica18/Programa-VideoJueog.git
```

3. **Entra a la carpeta del proyecto**:
```bash
cd Programa-VideoJueog
```

### Opción 2: Descargar ZIP

1. Ve al repositorio: [https://github.com/JuanGatica18/Programa-VideoJueog](https://github.com/JuanGatica18/Programa-VideoJueog)
2. Haz clic en el botón verde **"Code"** → **"Download ZIP"**
3. Descomprime el archivo en la ubicación deseada

---

##  Configuración del Proyecto

### Usando Eclipse

1. **Abre Eclipse**
2. Selecciona **File → Import → Existing Projects into Workspace**
3. Haz clic en **Browse** y navega hasta la carpeta del proyecto
4. Marca la casilla del proyecto y haz clic en **Finish**
5. Espera a que Eclipse configure el proyecto (puede tardar unos minutos la primera vez)

---

##  Ejecutar el Juego

### Desde Eclipse

1. En el **Package Explorer**, busca: `lwjgl3 → src/main/java → puppy.code.lwjgl3 → Lwjgl3Launcher.java`
2. Haz clic derecho en el archivo → **Run As → Java Application**
3. ¡El juego debería !

### Desde CMD (Windows)

Si prefieres ejecutarlo desde la línea de comandos:

```bash
gradlew.bat lwjgl3:run
```

---

##  Controles del Juego

### Menú Principal
- **ENTER**: Iniciar nueva partida

### Durante la Partida
- **W** o **↑**: Mover nave hacia arriba
- **S** o **↓**: Mover nave hacia abajo
- **A** o **←**: Mover nave hacia la izquierda
- **D** o **→**: Mover nave hacia la derecha
- **SPACE**: Disparar

### Pantalla de Game Over
- **ENTER**: Volver al menú principal

---

---

### Error: "Falta archivo de recursos (imágenes)"

Asegúrate de que la carpeta `assets/` esté presente en la raíz del proyecto con todos los archivos necesarios:
- nave.png
- enemigo_rapido.png
- enemigo_tanque.png
- enemigo_zigzag.png
- powerup_doble.png
- bala.png

---

##  Estructura del Proyecto

```
Programa-VideoJueog/
├── assets/                 # Recursos del juego (imágenes, sonidos)
├── core/                   # Código principal del juego
│   └── src/main/java/puppy/code/
│       ├── InvasionGame.java
│       └── invasion/
│           ├── entities/   # Jugador, enemigos, balas
│           ├── managers/   # Gestores (texturas, dificultad)
│           ├── screens/    # Pantallas del juego
│           ├── strategies/ # Patrones de disparo
│           └── ui/         # Interfaz de usuario
├── lwjgl3/                 # Launcher de escritorio
├── gradle/                 # Archivos de Gradle
├── build.gradle            # Configuración del proyecto
└── settings.gradle         # Módulos del proyecto
```

---

##  Recursos Adicionales

- **LibGDX Documentación**: [https://libgdx.com/wiki/](https://libgdx.com/wiki/)
- **Java JDK**: [https://adoptium.net/](https://adoptium.net/)

---

##  Autores

- Juan Eduardo Gatica Sánchez
- Hans Alejandro Gonzáles Núñez

**Docente**: Leonardo Cristian Gabriel González Catalán  
**Universidad**: Pontificia Universidad Católica de Valparaíso

---



##  Objetivo del Juego

Sobrevive el mayor tiempo posible mientras eliminas oleadas de enemigos invasores. El juego aumenta su dificultad progresivamente cada 30 segundos, poniendo a prueba tus reflejos y estrategia.

**¡Buena suerte, piloto!** �
