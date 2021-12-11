package br.unicamp.projetopratica;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.concurrent.TimeUnit;

public class TelaPrincipal extends Game {
    MenuPrincipal telaMenu;
    TelaComoJogar telaComoJogar;
    TelaDeMorte telaDeMorte;
    TelaDePause telaDePause;
    MyGdxGame jogo;
    String estado;
    float largura, altura;
    Context context;
    Music musica;
    Music musica2;
    Music explosao;

    public TelaPrincipal (Context contexto)
    {
        this.context = contexto;
    }

    public void trocarParaMenu ()
    {
        musica.play();
        musica2.stop();
        estado = "menu";
        setScreen(telaMenu);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException err) {
            ;
        }
    }

    public void trocarComoJogar ()
    {
        estado = "comoJogar";
        telaComoJogar = new TelaComoJogar();
        telaComoJogar.setTamanho(largura, altura);
        setScreen(telaComoJogar);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException err) {
            ;
        }
    }

    public void trocarParaMorte ()
    {
        estado = "morte";
        telaDeMorte = new TelaDeMorte();
        telaDeMorte.setTamanho(largura, altura, context);
        telaDeMorte.setPontos(jogo.getPontos());
        setScreen(telaDeMorte);
    }

    public void trocarParaPause ()
    {
        musica2.setVolume(0.01F);
        estado = "pausado";
        telaDePause = new TelaDePause();
        telaDePause.setTamanho(largura, altura);
        setScreen(telaDePause);
    }

    public void continuarJogo()
    {
        musica2.setVolume(0.05F);
        setScreen(jogo);
        estado = "jogar";
        jogo.resume();
    }

    public void reiniciarJogo ()
    {
        musica.stop();
        musica2.play();
        musica2.setVolume(0.05F);
        jogo = new MyGdxGame();
        jogo.setTamanhoDaTela(largura, altura);
        setScreen(jogo);
        jogo.iniciar();
        estado = "jogar";
    }

    @Override
    public void create() {
        largura = Gdx.app.getGraphics().getWidth();
        altura = Gdx.graphics.getHeight();
        telaMenu = new MenuPrincipal();
        telaMenu.setTamanho(largura, altura);
        musica = Gdx.audio.newMusic(Gdx.files.internal("musicaMenu.mp3"));
        musica.setLooping(true);
        //Song by Context Sensitive: https://www.youtube.com/watch?v=M94eN-YLbOs
        musica2 = Gdx.audio.newMusic(Gdx.files.internal("Vibing in the 20s.mp3"));
        musica2.setLooping(true);
        explosao = Gdx.audio.newMusic(Gdx.files.internal("explosao.mp3"));
        explosao.setLooping(false);
        trocarParaMenu();
    }

    @Override
    public void render()
    {
        if(estado == "menu") {
            telaMenu.render(60);
            if (telaMenu.qualOEstado() == "jogar") {
                reiniciarJogo();
            }
            else if (telaMenu.qualOEstado() == "comoJogar")
            {
                trocarComoJogar();
            }
        }
        else if(estado == "comoJogar")
        {
            telaComoJogar.render(60);
            if(telaComoJogar.getEstado() == "lido")
            {
                trocarParaMenu();
            }
        }
        else if(estado == "jogar")
        {
            switch (jogo.getEstado())
            {
                case "vivo": jogo.render(60); break;
                case "morrendo": explosao.play(); jogo.render(60); break;
                case "morto": trocarParaMorte(); break;
                case "pausado": trocarParaPause(); break;
            }
        }
        else if(estado == "morte")
        {
            telaDeMorte.render(60);
            if(telaDeMorte.getEstado() == "salvo")
            {
                trocarParaMenu();
            }
        }
        else if (estado == "pausado")
        {
            telaDePause.render(60);
            switch (telaDePause.getEstado())
            {
                case "continuar": continuarJogo(); break;
                case "reiniciar": reiniciarJogo(); break;
                default: break;
            }
        }
    }
}
