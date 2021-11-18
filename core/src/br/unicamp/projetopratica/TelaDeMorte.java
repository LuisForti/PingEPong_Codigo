package br.unicamp.projetopratica;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TelaDeMorte implements Screen {
    private SpriteBatch batch;
    private float largura, altura;

    private int score;
    private String pontuacao;
    private BitmapFont scoreboard;

    @Override
    public void show() {
        scoreboard = new BitmapFont();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        scoreboard.setColor(1, 1, 1, 1);
        scoreboard.getData().setScale(5);
        scoreboard.draw(batch, pontuacao, largura/3, altura/3);
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

    }

    @Override
    public void dispose() {

    }

    public void setTamanho(float largura, float altura)
    {
        this.largura = largura;
        this.altura = altura;
    }

    public void setPontos(String pontos)
    {
        pontuacao = pontos;
        score = Integer.parseInt(pontuacao.substring(7));
        System.out.println(score);
    }
}
