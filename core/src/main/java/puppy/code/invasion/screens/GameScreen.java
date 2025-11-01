package puppy.code.invasion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.Iterator;
import java.util.Random;
import  puppy.code.invasion.entities.Bullet;
import  puppy.code.invasion.entities.PowerUp;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import  puppy.code.InvasionGame;
import  puppy.code.invasion.entities.Enemy;
import  puppy.code.invasion.entities.PlayerShip;
import  puppy.code.invasion.managers.TextureManager;
import  puppy.code.invasion.managers.EnemySpawner;
import  puppy.code.invasion.managers.DifficultyManager;
import  puppy.code.invasion.strategies.shoot.DoubleShoot;
import  puppy.code.invasion.ui.HUD;

public class GameScreen implements Screen {

    private final InvasionGame game;

    private PlayerShip player;
    private Array<Enemy> enemies;
    private Array<Bullet> playerBullets;
    private Array<PowerUp> powerUps;
    private EnemySpawner enemySpawner;
    private DifficultyManager difficultyManager;
    private HUD hud;
    private Random random;

    private float powerUpDuration = 10f; // duración del power up en segundos
    private float powerUpTimer = 0;
    private boolean hasPowerUp = false;

    public GameScreen(InvasionGame game) {
        this.game = game;
        this.random = new Random();

        this.player = new PlayerShip(Gdx.graphics.getWidth() / 2f, 50f);
        this.playerBullets = new Array<>();
        this.enemies = new Array<>();
        this.powerUps = new Array<>();
        this.difficultyManager = new DifficultyManager();
        this.enemySpawner = new EnemySpawner(difficultyManager);
        this.hud = new HUD();
    }

    @Override
    public void render(float delta) {

        player.update(delta, playerBullets);

        // Actualiza el sistema de dificultad
        difficultyManager.update(delta);

        // Actualiza el spawner de enemigos
        enemySpawner.update(delta, enemies);

        // Actualiza enemigos
        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }

        // Actualiza balas
        for (Bullet bullet : playerBullets) {
            bullet.update(delta);
        }

        // Actualiza power ups
        for (PowerUp powerUp : powerUps) {
            powerUp.update(delta);
        }

        // Actualiza el timer del power up
        if (hasPowerUp) {
            powerUpTimer += delta;
            if (powerUpTimer >= powerUpDuration) {
                hasPowerUp = false;
                powerUpTimer = 0;
                player.setWeapon(new  puppy.code.invasion.strategies.shoot.SingleShoot());
            }
        }

        checkCollisions();

        removeDeadEntities();

        if (player.isDead()) {
            game.setScreen(new GameOverScreen(game, hud.getScore()));
            dispose();
            return;
        }

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        game.batch.begin();

        player.render(game.batch);
        for (Enemy enemy : enemies) {
            enemy.render(game.batch);
        }
        for (Bullet bullet : playerBullets) {
            bullet.render(game.batch);
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.render(game.batch);
        }

        // Dibuja el HUD con nivel e información de power-up
        hud.render(game.batch, player.getLives(), difficultyManager.getCurrentLevel(), hasPowerUp, powerUpTimer, powerUpDuration);

        game.batch.end();
    }

    private void checkCollisions() {
        for (Bullet bullet : playerBullets) {
            for (Enemy enemy : enemies) {
                if (bullet.getBounds().overlaps(enemy.getBounds())) {
                    enemy.takeDamage(100);
                    bullet.setDead(true);

                    if (enemy.isDead()) {
                        hud.addScore(enemy.getScoreValue());

                        // 15% de probabilidad de soltar power-up
                        if (random.nextFloat() < 0.15f) {
                            dropPowerUp(enemy.getX(), enemy.getY());
                        }
                    }
                }
            }
        }

        if (!player.isInvincible()) {
            for (Enemy enemy : enemies) {
                if (enemy.getBounds().overlaps(player.getBounds())) {
                    player.takeHit();
                    enemy.setDead(true);
                }
            }
        }

        // Colisión: Jugador vs PowerUps
        for (PowerUp powerUp : powerUps) {
            if (powerUp.getBounds().overlaps(player.getBounds())) {
                collectPowerUp(powerUp);
                powerUp.setDead(true);
            }
        }
    }

    private void dropPowerUp(float x, float y) {
        PowerUp powerUp = new PowerUp(
                x,
                y,
                PowerUp.PowerUpType.DOUBLE_SHOOT,
                TextureManager.getInstance().getTexture("powerup_double")
        );
        powerUps.add(powerUp);
    }

    private void collectPowerUp(PowerUp powerUp) {
        if (powerUp.getType() == PowerUp.PowerUpType.DOUBLE_SHOOT) {
            player.setWeapon(new DoubleShoot());
            hasPowerUp = true;
            powerUpTimer = 0;
        }
    }

    private void removeDeadEntities() {
        // Elimina balas muertas
        for (Iterator<Bullet> iter = playerBullets.iterator(); iter.hasNext(); ) {
            if (iter.next().isDead()) {
                iter.remove();
            }
        }

        // Elimina enemigos muertos
        for (Iterator<Enemy> iter = enemies.iterator(); iter.hasNext(); ) {
            if (iter.next().isDead()) {
                iter.remove();
            }
        }

        // Elimina power-ups usados
        for (Iterator<PowerUp> iter = powerUps.iterator(); iter.hasNext(); ) {
            if (iter.next().isDead()) {
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        hud.dispose();
    }

    @Override public void show() { }
    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }
}