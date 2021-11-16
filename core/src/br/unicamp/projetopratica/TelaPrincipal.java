package br.unicamp.projetopratica;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class TelaPrincipal extends Game {
    MenuPrincipal telaAtual;
    MyGdxGame jogo;
    int tempo = 0;
    String estado;

    public TelaPrincipal ()
    {
        jogo = new MyGdxGame();
        telaAtual = new MenuPrincipal();
        estado = "menu";
    }

    public void trocarDeTela ()
    {
        telaAtual = new MenuPrincipal();
        estado = "menu";
        setScreen(telaAtual);
        telaAtual.show();
    }

    public void reiniciarJogo ()
    {
        telaAtual.hide();
        jogo = new MyGdxGame();
        setScreen(jogo);
        estado = "jogar";
    }

    @Override
    public void create() {
        setScreen(telaAtual);
        telaAtual.show();
    }

    @Override
    public void render()
    {
        if(estado == "menu") {
            telaAtual.render(60);
            if (telaAtual.qualOEstado() == "jogar") {
                reiniciarJogo();
            }
        }
        else if(estado == "jogar")
        {
            jogo.render(60);
            if(!jogo.estaVivo())
            {
                trocarDeTela();
            }
        }
    }
}
