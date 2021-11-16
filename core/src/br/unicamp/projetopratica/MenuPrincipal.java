package br.unicamp.projetopratica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Menu;

public class MenuPrincipal implements Screen {
    private Texture foto;
    private Sprite spriteInimigo;
    private SpriteBatch batch;

    @Override
    public void show() {
        foto = new Texture(Gdx.files.internal("tiro.png"));
        spriteInimigo = new Sprite(foto);
        spriteInimigo.setPosition(600, 600);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        spriteInimigo.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    @Override
    public void dispose() {

    }
}
