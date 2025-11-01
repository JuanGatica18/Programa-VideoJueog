package puppy.code.invasion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import  puppy.code.InvasionGame; // Importa tu juego principal

public class MenuScreen implements Screen {

    private final InvasionGame game;

    public MenuScreen(InvasionGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1); 

        game.batch.begin();
        game.font.draw(game.batch, "Proyecto Invasion", 100, 150);
        game.font.draw(game.batch, "Presiona ENTER para jugar", 100, 100);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override public void show() { }
    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }
    @Override public void dispose() { }
}