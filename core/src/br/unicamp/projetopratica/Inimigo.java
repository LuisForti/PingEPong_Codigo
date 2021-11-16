package br.unicamp.projetopratica;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

public class Inimigo {
    private byte vida;
    private byte tipoDeMovimento;
    private Texture foto;
    private Sprite spriteInimigo;

    public void setVida(byte vida)
    {
        this.vida = vida;
    }

    public void setCoordenadaX(float posicao)
    {
        this.spriteInimigo.setX(posicao);
    }

    public void setCoordenadaY(float posicao)
    {
        this.spriteInimigo.setY(posicao);
    }

    public byte getVida()
    {
        return vida;
    }

    public byte getVidaInicial() { return tipoDeMovimento; }

    public float getCoordenadaX() {
        return this.spriteInimigo.getX();
    }

    public float getCoordenadaY() {
        return this.spriteInimigo.getY();
    }

    public Sprite getSpriteInimigo() {
        return spriteInimigo;
    }

    public Inimigo(byte vida, String enderecoDaFoto, float X, float Y)
    {
        this.vida = vida;
        this.tipoDeMovimento = vida;
        this.foto = new Texture(enderecoDaFoto);
        this.spriteInimigo = new Sprite(foto);
        this.spriteInimigo.setX(X);
        this.spriteInimigo.setY(Y);
        int qualDirecao = 0;
        if(X > 600)
        {
            if(Y > 400)
            {
                qualDirecao = 90;
            }
            else
            {
                qualDirecao = 0;
            }
        }
        else
        {
            if(Y > 400)
            {
                qualDirecao = 180;
            }
            else
            {
                qualDirecao = 270;
            }
        }
        this.spriteInimigo.setRotation((float)(Math.random() * 90) + qualDirecao);
    }

    public Inimigo movimentar(Sprite nave, LinkedList<Inimigo> inimigos)
    {
        switch (tipoDeMovimento)
        {
            case 1: movimento1(); break;
            case 2: movimento2(nave, inimigos); break;
        }

        for (Inimigo atual : inimigos) {
            if(atual.getCoordenadaX() > 1800 || atual.getCoordenadaX() < -400)
            {
                return atual;
            }
            else
            {
                if(atual.getCoordenadaY() > 1000 || atual.getCoordenadaY() < -400)
                {
                    return atual;
                }
            }
        }
        return null;
    }

    private void movimento1()
    {
        float novoX = (float)(-5 * Math.sin(Math.toRadians(spriteInimigo.getRotation())));
        float novoY = (float)(5 * Math.cos(Math.toRadians(spriteInimigo.getRotation())));

        spriteInimigo.setX(spriteInimigo.getX() + novoX);
        spriteInimigo.setY(spriteInimigo.getY() + novoY);
    }

    private void movimento2(Sprite nave, LinkedList<Inimigo> inimigos) {
        int rotacao = 0;

        if (this.getCoordenadaX() - 25 > nave.getX()) {
            this.setCoordenadaX(this.getCoordenadaX() - 3);
            rotacao = 1;
        } else {
            if (this.getCoordenadaX() + 25 < nave.getX()) {
                this.setCoordenadaX(this.getCoordenadaX() + 3);
                rotacao = 2;
            }
        }
        if (this.getCoordenadaY() - 25 > nave.getY()) {
            this.setCoordenadaY(this.getCoordenadaY() - 3);
            if (rotacao == 0) {
                rotacao = 3;
            } else if (rotacao == 1) {
                rotacao = 4;
                this.setCoordenadaX(this.getCoordenadaX() + 1);
            } else {
                rotacao = 5;
                this.setCoordenadaX(this.getCoordenadaX() - 1);
            }
        } else {
            if (this.getCoordenadaY() + 25 < nave.getY()) {
                this.setCoordenadaY(this.getCoordenadaY() + 3);
                if (rotacao == 0) {
                    rotacao = 6;
                } else if (rotacao == 1) {
                    rotacao = 7;
                    this.setCoordenadaX(this.getCoordenadaX() + 1);
                } else {
                    rotacao = 8;
                    this.setCoordenadaX(this.getCoordenadaX() - 1);
                }
            }
        }

        switch (rotacao) {
            case 1:
                rotacao = 90;
                break;
            case 2:
                rotacao = 270;
                break;
            case 3:
                rotacao = 180;
                break;
            case 4:
                rotacao = 135;
                break;
            case 5:
                rotacao = 225;
                break;
            case 6:
                rotacao = 0;
                break;
            case 7:
                rotacao = 45;
                break;
            case 8:
                rotacao = 315;
                break;
        }

        this.getSpriteInimigo().setRotation(rotacao);

        verificarColisaoDaNave(inimigos);
    }

    private void verificarColisaoDaNave(LinkedList<Inimigo> inimigos)
    {
        for (Inimigo atual2 : inimigos) {
            if(this != atual2)
            {
                if(atual2.getVida() > 1) {
                    if (this.getSpriteInimigo().getBoundingRectangle().overlaps(atual2.getSpriteInimigo().getBoundingRectangle())) {
                        while (this.getSpriteInimigo().getBoundingRectangle().overlaps(atual2.getSpriteInimigo().getBoundingRectangle())) {
                            if (this.getCoordenadaX() > atual2.getCoordenadaX() - 25) {
                                this.setCoordenadaX(this.getCoordenadaX() + 1);
                            } else {
                                if (this.getCoordenadaX() < atual2.getCoordenadaX() + 25) {
                                    this.setCoordenadaX(this.getCoordenadaX() - 1);
                                }
                            }
                            if (this.getCoordenadaY() > atual2.getCoordenadaY() - 25) {
                                this.setCoordenadaY(this.getCoordenadaY() + 1);
                            } else {
                                if (this.getCoordenadaY() < atual2.getCoordenadaY() + 25) {
                                    this.setCoordenadaY(this.getCoordenadaY() - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
