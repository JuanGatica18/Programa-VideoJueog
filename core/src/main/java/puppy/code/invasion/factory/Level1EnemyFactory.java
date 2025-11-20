package puppy.code.invasion.factory;

import puppy.code.invasion.entities.*;

public class Level1EnemyFactory implements EnemyFactory {

    @Override
    public Enemy createFastEnemy(float x, float y) {
        return new FastEnemy(x, y);
    }

    @Override
    public Enemy createTankEnemy(float x, float y) {
        return new TankEnemy(x, y);
    }

    @Override
    public Enemy createZigZagEnemy(float x, float y) {
        return new ZigZagEnemy(x, y);
    }
}
