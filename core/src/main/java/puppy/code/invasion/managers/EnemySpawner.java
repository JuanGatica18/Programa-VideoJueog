package puppy.code.invasion.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import puppy.code.invasion.entities.*;
import puppy.code.invasion.factory.EnemyFactory;
import puppy.code.invasion.factory.Level1EnemyFactory;
import java.util.Random;

public class EnemySpawner {

    private float spawnTimer;
    private Random random;
    private DifficultyManager difficultyManager;
    private EnemyFactory enemyFactory;

    public EnemySpawner(DifficultyManager difficultyManager) {
        this.random = new Random();
        this.spawnTimer = 0;
        this.difficultyManager = difficultyManager;
        this.enemyFactory = new Level1EnemyFactory();
    }
    
    public void setFactory(EnemyFactory factory) {
        this.enemyFactory = factory;
    }

    public void update(float delta, Array<Enemy> enemies) {
        spawnTimer += delta;

        float spawnInterval = difficultyManager.getSpawnInterval();
        int maxEnemies = difficultyManager.getMaxEnemies();

        if (spawnTimer >= spawnInterval && enemies.size < maxEnemies) {
            spawnEnemy(enemies);
            spawnTimer = 0;
        }
    }

    private void spawnEnemy(Array<Enemy> enemies) {
        float x = random.nextInt(Gdx.graphics.getWidth() - 64);
        float y = Gdx.graphics.getHeight() + 50;

        float rand = random.nextFloat();
        float zigzagChance = difficultyManager.getZigZagChance();
        float tankChance = difficultyManager.getTankChance();

        if (rand < zigzagChance) {
            enemies.add(enemyFactory.createZigZagEnemy(x, y));
        } else if (rand < zigzagChance + tankChance) {
            enemies.add(enemyFactory.createTankEnemy(x, y));
        } else {
            enemies.add(enemyFactory.createFastEnemy(x, y));
        }
        // -----------------------------------------------------
    }
}