package br.unicamp.projetopratica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.Menu;

public class MenuPrincipal implements Screen {
    private String estado = "nada";
    private float largura, altura;
    private Texture titulo;
    private Texture fundo;
    private Drawable fotoJogar;
    private Button btnJogar;
    private Drawable fotoComoJogar;
    private Button btnComoJogar;
    private SpriteBatch batch;
    private Stage stage;

    @Override
    public void show() {
        estado = "nada";
        titulo = new Texture(Gdx.files.internal("titulo.png"));
        fundo = new Texture(Gdx.files.internal("fundo.png"));
        fotoJogar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("jogar.png"))));
        fotoComoJogar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("comojogar.png"))));
        btnJogar = new Button(fotoJogar);
        btnJogar.setPosition(0, 0);
        btnJogar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "jogar";
                return true;
            }
        });

        btnComoJogar = new Button(fotoComoJogar);
        btnComoJogar.setPosition(0, 0);
        btnComoJogar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "continuar";
                return true;
            }
        });

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        btnJogar.setPosition(largura/2 + 200, altura/4 + 300);
        btnJogar.setWidth(largura/4);
        btnJogar.setHeight(largura/16);
        stage.addActor(btnJogar);
        btnComoJogar.setPosition(largura/2 + 200, altura/3);
        btnComoJogar.setWidth(largura/4);
        btnComoJogar.setHeight(largura/16);
        stage.addActor(btnComoJogar);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(fundo, 0, 0, (int)largura, (int)altura);
        batch.draw(titulo, largura/2 - largura/3, altura/6, (int)largura/3, (int)largura/3);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    public void setTamanho(float largura, float altura)
    {
        this.largura = largura;
        this.altura = altura;
    }
}
