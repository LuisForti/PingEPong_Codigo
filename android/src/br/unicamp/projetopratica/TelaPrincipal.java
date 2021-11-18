package br.unicamp.projetopratica;

import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TelaPrincipal extends Game {
    MenuPrincipal telaMenu;
    TelaDeMorte telaDeMorte;
    MyGdxGame jogo;
    int tempo = 0;
    String estado;
    float largura, altura;
    Context context;

    public TelaPrincipal (Context contexto)
    {
        this.context = contexto;
    }

    public void trocarParaMenu ()
    {
        estado = "menu";
        setScreen(telaMenu);
    }

    public void trocarParaMorte ()
    {
        estado = "morte";
        setScreen(telaDeMorte);
        telaDeMorte.setPontos(jogo.getPontos());
    }

    public void continuarJogo()
    {
        telaMenu.hide();
        setScreen(jogo);
        estado = "jogar";
        jogo.resume();
    }

    public void reiniciarJogo ()
    {
        telaMenu.hide();
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
        jogo = new MyGdxGame();
        jogo.setTamanhoDaTela(largura, altura);
        telaMenu = new MenuPrincipal();
        telaDeMorte = new TelaDeMorte();
        estado = "menu";
        telaMenu.setTamanho(largura, altura);
        telaDeMorte.setTamanho(largura, altura, context);
        setScreen(telaMenu);
        telaMenu.show();
    }

    @Override
    public void render()
    {
        if(estado == "menu") {
            telaMenu.render(60);
            if (telaMenu.qualOEstado() == "jogar") {
                reiniciarJogo();
            }
            else if (telaMenu.qualOEstado() == "continuar")
            {
                trocarParaMorte();
            }
        }
        else if(estado == "jogar")
        {
            switch (jogo.getEstado())
            {
                case "vivo": jogo.render(60); break;
                case "morrendo": jogo.render(60); break;
                case "morto": trocarParaMorte(); break;
                case "pausado": trocarParaMenu(); break;
            }
        }
        else
        {
            telaDeMorte.render(60);
        }
    }
}
