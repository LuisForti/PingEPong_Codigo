package br.unicamp.projetopratica;

public class Pontuacao {

    Integer id;
    Integer pontos;

    public Pontuacao() {
        id = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}