package br.unicamp.projetopratica;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class TelaPrincipal extends Game {
    Screen telaAtual;
    MyGdxGame jogo;
    int tempo = 0;

    public TelaPrincipal ()
    {
        jogo = new MyGdxGame();
        telaAtual = new MenuPrincipal();
    }

    public void trocarDeTela (Screen telaAtual)
    {
        jogo.hide();
        this.telaAtual.hide();
        this.telaAtual = telaAtual;
        setScreen(telaAtual);
    }

    public void reiniciarJogo ()
    {
        this.telaAtual.hide();
        jogo = new MyGdxGame();
        setScreen(jogo);
    }

    @Override
    public void create() {
        setScreen(jogo);
        jogo.show();
    }

    @Override
    public void render()
    {
        if(jogo.estaVivo())
        {
            jogo.render(tempo);
        }
        else
        {
            trocarDeTela(new MenuPrincipal());
            telaAtual.render(tempo);
            reiniciarJogo();
        }
    }
}
