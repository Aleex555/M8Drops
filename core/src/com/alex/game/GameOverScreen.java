package com.alex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen implements Screen {
    Texture background;
    final drop game;
    OrthographicCamera camera;
    GlyphLayout layout; // Usado para calcular dimensiones del texto

    public GameOverScreen(final drop game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("2.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        layout = new GlyphLayout(); // Inicializa el GlyphLayout
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 800, 480);

        String pointsText = "TUS PUNTOS: " + GameScreen.dropsGathered;
        layout.setText(game.font, pointsText);
        float pointsX = (800 - layout.width) / 2;
        float pointsY = 150;
        game.font.draw(game.batch, pointsText, pointsX, pointsY);

        String retryText = "Pulsa para volver a jugar";
        layout.setText(game.font, retryText); // Aplica el texto y fuente al layout para calcular dimensiones
        float retryX = (800 - layout.width) / 2;
        float retryY = 100;
        game.font.draw(game.batch, retryText, retryX, retryY);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            GameScreen.dropsGathered = 0;
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 800; // Anchura fija
        camera.viewportHeight = 480; // Altura fija
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
