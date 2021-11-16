package br.unicamp.projetopratica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.Menu;

public class MenuPrincipal implements Screen {
    private SpriteBatch batch;
    private String estado;
    private Drawable fotoJogar;
    private ImageButton btnJogar;
    private ImageButton btnTeste1;
    private Stage palco;
    private Explosao teste;

    @Override
    public void show() {
        batch = new SpriteBatch();
        fotoJogar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pause.png"))));
        btnJogar = new ImageButton(fotoJogar);
        btnJogar.setPosition(600, 300);
        btnJogar.setSize(200,200);
        btnJogar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "jogar";
                return true;
            }
        });
        btnTeste1 = new ImageButton(fotoJogar);
        btnTeste1.setPosition(800, 200);
        btnTeste1.setSize(200,200);
        btnTeste1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "continuar";
                btnJogar.setVisible(!btnJogar.isVisible());
                return true;
            }
        });
        palco = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(palco);
        palco.addActor(btnJogar);
        palco.addActor(btnTeste1);
        estado = "nada";
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        palco.act();
        palco.draw();
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

    public String qualOEstado()
    {
        return estado;
    }
}
