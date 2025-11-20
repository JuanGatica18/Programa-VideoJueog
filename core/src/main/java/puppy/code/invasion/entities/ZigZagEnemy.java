package puppy.code.invasion.entities;

import com.badlogic.gdx.Gdx;
import  puppy.code.invasion.managers.TextureManager;

public class ZigZagEnemy extends Enemy {
    private float horizontalSpeed = 150f; // Velocidad horizontal
    private int direction = 1; // 1 = derecha, -1 = izquierda
    private float zigzagTimer = 0;
    private float zigzagInterval = 1f; // Cambia de dirección cada 1 segundo

    public ZigZagEnemy(float x, float y) {
        // 100 de vida, vale 20 puntos 
        super(x, y, 100, TextureManager.getInstance().getTexture("zigzag_enemy"), 20);
    }

    @Override
    protected void moveBehavior(float delta) {
        // Comportamiento específico: Movimiento complejo en zigzag
        y -= 120 * delta; // Cae
        x += horizontalSpeed * direction * delta; // Se mueve lateralmente

        // Lógica del zigzag
        zigzagTimer += delta;
        if (zigzagTimer >= zigzagInterval) {
            direction *= -1;
            zigzagTimer = 0;
        }

        // Mantener dentro de la pantalla (rebote simple)
        if (x < 0) {
            x = 0;
            direction = 1;
        } else if (x > Gdx.graphics.getWidth() - texture.getWidth()) {
            x = Gdx.graphics.getWidth() - texture.getWidth();
            direction = -1;
        }
    }
}