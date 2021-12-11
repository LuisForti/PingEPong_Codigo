package br.unicamp.projetopratica;

import static android.database.sqlite.SQLiteDatabase.*;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class TelaDeMorte implements Screen {
    private SpriteBatch batch;
    private float largura, altura;

    private int score;
    private String highestScore;
    private BitmapFont scoreboard;
    private BitmapFont scoreboardHighScore;

    private String estado;

    private Texture fundo;
    private Texture titulo;

    private Context context;

    public void setTamanho(float largura, float altura, Context context)
    {
        this.largura = largura;
        this.altura = altura;
        this.context = context;
    }

    public void setPontos(int pontos)
    {
        score = pontos;
    }

    private void getDados()
    {
        Read r = new Read(context);
        Pontuacao p1 = r.getPontuacao();

        highestScore = Integer.toString(p1.getPontos());
    }

    @Override
    public void show() {
        estado = "nada";
        Create c = new Create(context);
        c.createTable();
        getDados();
        fundo = new Texture(Gdx.files.internal("fundo.png"));
        scoreboard = new BitmapFont();
        scoreboardHighScore = new BitmapFont();
        batch = new SpriteBatch();
        if(Integer.parseInt(highestScore) < score) {
            titulo = new Texture(Gdx.files.internal("novo recorde.png"));
        }
        else
        {
            titulo = new Texture(Gdx.files.internal("fim de jogo.png"));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(fundo, 0, 0, (int) largura, (int) altura);
        batch.draw(titulo, largura / 4, altura / 6, largura / 2, largura / 2);
        scoreboard.setColor(1, 1, 1, 1);
        scoreboard.getData().setScale(5);
        scoreboard.draw(batch, "Pontuação: " + score, largura / 3, altura / 3 + 100);
        scoreboardHighScore.setColor(1, 1, 1, 1);
        scoreboardHighScore.getData().setScale(5);
        scoreboardHighScore.draw(batch, "Maior Pontuação: " + highestScore, largura / 3, altura / 4);
        batch.end();
        if (Gdx.input.isTouched()) { // is called whenever the finger is on screen touch
            setRecorde();
            estado = "salvo";
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

    public void setRecorde()
    {
        if(Integer.parseInt(highestScore) < score) {
            Pontuacao p = new Pontuacao();
            p.setPontos(score);
            Update u = new Update(context);
            u.updatePontuacao(p);
        }
    }

    public String getEstado()
    {
        return estado;
    }
}
