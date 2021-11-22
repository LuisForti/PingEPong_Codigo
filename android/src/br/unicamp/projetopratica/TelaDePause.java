package br.unicamp.projetopratica;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TelaDePause implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private float largura, altura;

    private String estado;

    private Texture fundo;
    private Drawable fotoContinuar;
    private Button btnContinuar;
    private Drawable fotoReiniciar;
    private Button btnReiniciar;

    @Override
    public void show() {
        estado = "nada";
        fundo = new Texture(Gdx.files.internal("fundo.png"));
        fotoContinuar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("continuar.png"))));
        fotoReiniciar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("reiniciar.png"))));
        btnContinuar = new Button(fotoContinuar);
        btnContinuar.setPosition(0, 0);
        btnContinuar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "continuar";
                return true;
            }
        });

        btnReiniciar = new Button(fotoReiniciar);
        btnReiniciar.setPosition(0, 0);
        btnReiniciar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                estado = "reiniciar";
                return true;
            }
        });

        stage = new Stage();
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);
        btnContinuar.setPosition(largura/5, altura/4);
        btnContinuar.setWidth(largura/4);
        btnContinuar.setHeight(largura/4);
        stage.addActor(btnContinuar);
        btnReiniciar.setPosition(largura/2 + 200, altura/4);
        btnReiniciar.setWidth(largura/4);
        btnReiniciar.setHeight(largura/4);
        stage.addActor(btnReiniciar);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(fundo, 0, 0, (int) largura, (int) altura);
        batch.end();
        stage.draw();
        stage.act();
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

    }

    @Override
    public void dispose() {

    }

    public void setTamanho(float largura, float altura)
    {
        this.largura = largura;
        this.altura = altura;
    }

    public String getEstado()
    {
        return estado;
    }
}
