package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import puppy.code.invasion.screens.*;
import puppy.code.invasion.managers.TextureManager;

public class InvasionGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // se inicializa el TextureManager una sola vez
        TextureManager.getInstance();

        // esto nicia el juego y establece la pantalla de Menú
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        // Limpia los recursos al cerrar el juego COMPLETAMENTE
        batch.dispose();
        font.dispose();

        // AHORA SÍ disponemos el TextureManager 
        TextureManager.getInstance().dispose();
    }
}