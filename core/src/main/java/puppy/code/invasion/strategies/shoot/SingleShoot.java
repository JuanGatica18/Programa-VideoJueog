package puppy.code.invasion.strategies.shoot;

import com.badlogic.gdx.utils.Array;
import  puppy.code.invasion.entities.Bullet; // Importa la bala

public class SingleShoot implements ShootStrategy {
    @Override
    public void shoot(float x, float y, Array<Bullet> bullets) {
        bullets.add(new Bullet(x, y));
    }
}