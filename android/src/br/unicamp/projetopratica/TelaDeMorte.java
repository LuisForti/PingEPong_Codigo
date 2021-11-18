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
    private String pontuacao;
    private BitmapFont scoreboard;
    private BitmapFont scoreboardHighScore;

    private Drawable fotoJogar;
    private Button btnJogar;
    private Stage stage;
    private String estado;

    private SQLiteDatabase bancoDados;
    Context context;

    @Override
    public void show() {
        estado = "nada";
        scoreboard = new BitmapFont();
        scoreboardHighScore = new BitmapFont();
        batch = new SpriteBatch();
        fotoJogar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("jogar.png"))));
        btnJogar = new Button(fotoJogar);
        btnJogar.setPosition(0, 0);
        btnJogar.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                setRecorde();
                estado = "salvo";
                return true;
            }
        });
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        btnJogar.setPosition(largura/2 + 200, altura/4 + 300);
        btnJogar.setWidth(largura/4);
        btnJogar.setHeight(largura/16);
        stage.addActor(btnJogar);

        Create c = new Create(context);
        c.createTable();

        getDados();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        scoreboard.setColor(0, 0, 0, 1);
        scoreboard.getData().setScale(5);
        scoreboard.draw(batch, pontuacao, largura/3, altura/3);
        scoreboardHighScore.setColor(0, 0, 0, 1);
        scoreboardHighScore.getData().setScale(5);
        scoreboardHighScore.draw(batch, "HighScore: " + highestScore, largura/3, altura/3 + 200);
        batch.end();
        stage.act();
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

    }

    @Override
    public void dispose() {

    }

    public void setTamanho(float largura, float altura, Context context)
    {
        this.largura = largura;
        this.altura = altura;
        this.context = context;
    }

    public void setPontos(String pontos)
    {
        pontuacao = pontos;
        score = Integer.parseInt(pontuacao.substring(7));
    }

    private void getDados()
    {
        Read r = new Read(context);
        ArrayList<Pontuacao> pArray = r.getPontuacao();
        ArrayList list = new ArrayList();
        Pontuacao p1 = pArray.get(0);

        System.out.println(p1.getId());
        System.out.println(p1.getPontos());

        highestScore = Integer.toString(p1.getPontos());
    }

    public void setRecorde()
    {
        if(Integer.parseInt(highestScore) < score) {
            Pontuacao p = new Pontuacao();
            p.setPontos(score);
            Update u = new Update(context);
            if (u.updatePessoa(p)) {
                System.out.println("funcionou");
            } else {
                System.out.println("erro");
            }
        }
        else
        {
            System.out.println("é menor seu palerma");
        }
    }

    public String getEstado()
    {
        return estado;
    }
}
