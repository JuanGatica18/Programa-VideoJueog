package puppy.code.invasion.factory;

import puppy.code.invasion.entities.Enemy;

public interface EnemyFactory {
    Enemy createFastEnemy(float x, float y);
    Enemy createTankEnemy(float x, float y);
    Enemy createZigZagEnemy(float x, float y);
    // Aquí podrías añadir en el futuro: Sound createExplosionSound();
}