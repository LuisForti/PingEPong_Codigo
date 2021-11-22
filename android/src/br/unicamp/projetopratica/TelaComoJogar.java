package br.unicamp.projetopratica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.TimeUnit;

public class TelaComoJogar implements Screen {
    private SpriteBatch batch;
    private float largura, altura;

    private String estado;

    private Texture fundo;
    private Texture tutorial;

    @Override
    public void show() {
        estado = "nada";
        batch = new SpriteBatch();
        fundo = new Texture(Gdx.files.internal("fundo.png"));
        tutorial = new Texture(Gdx.files.internal("tutorial.png"));
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(fundo, 0, 0, (int) largura, (int) largura);
        batch.draw(tutorial, largura/10, 0, (int) (altura * 1.5), (int) altura);
        batch.end();
        if (Gdx.input.isTouched()) { // is called whenever the finger is on screen touch
            estado = "lido";
        }
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
