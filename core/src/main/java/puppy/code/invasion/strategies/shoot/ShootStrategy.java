package  puppy.code.invasion.strategies.shoot;

import com.badlogic.gdx.utils.Array;
import  puppy.code.invasion.entities.Bullet; // Importa la bala

public interface ShootStrategy {
    void shoot(float x, float y, Array<Bullet> bullets);
}