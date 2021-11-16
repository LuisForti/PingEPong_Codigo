package br.unicamp.projetopratica;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class TelaPrincipal extends Game {
    MenuPrincipal telaAtual;
    MyGdxGame jogo;
    int tempo = 0;
    String estado;
    float largura, altura;

    public TelaPrincipal ()
    {
    }

    public void trocarDeTela ()
    {
        telaAtual = new MenuPrincipal();
        estado = "menu";
        setScreen(telaAtual);
        telaAtual.show();
    }

    public void continuarJogo()
    {
        telaAtual.hide();
        setScreen(jogo);
        estado = "jogar";
        jogo.resume();
    }

    public void reiniciarJogo ()
    {
        telaAtual.hide();
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
        telaAtual = new MenuPrincipal();
        estado = "menu";
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
            else if (telaAtual.qualOEstado() == "continuar")
            {
                continuarJogo();
            }
        }
        else if(estado == "jogar")
        {
            switch (jogo.getEstado())
            {
                case "vivo": jogo.render(60); break;
                case "morto": trocarDeTela(); break;
                case "pausado": trocarDeTela(); break;
            }
        }
    }
}
