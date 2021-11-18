package br.unicamp.projetopratica;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

public class Inimigo {
    private byte vida;
    private byte tipoDeMovimento;
    private Texture foto;
    private Sprite spriteInimigo;
    private byte velocidade = 4;
    private boolean estaColado = false;

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

    public Inimigo(byte vida, String enderecoDaFoto, int largura, int altura, float X, float Y)
    {
        this.vida = vida;
        this.tipoDeMovimento = vida;
        this.foto = new Texture(enderecoDaFoto);
        this.spriteInimigo = new Sprite(foto, largura, altura);
        this.spriteInimigo.setX(X);
        this.spriteInimigo.setY(Y);
        this.spriteInimigo.setScale(2);
        int qualDirecao = 0;
        if(vida < 3) {
            if (X > 600) {
                if (Y > 400) {
                    qualDirecao = 90;
                } else {
                    qualDirecao = 0;
                }
            } else {
                if (Y > 400) {
                    qualDirecao = 180;
                } else {
                    qualDirecao = 270;
                }
            }
            this.spriteInimigo.setRotation((float) (Math.random() * 90) + qualDirecao);
        }
        else
        {
            if(X < 0)
            {
                this.spriteInimigo.setRotation(270);
            }
            else
            {
                this.spriteInimigo.setRotation(90);
            }
        }
    }

    public Inimigo movimentar(Sprite nave, LinkedList<Inimigo> inimigos, float largura, float altura) {
        if (!estaColado) {
            switch (tipoDeMovimento) {
                case 1:
                    movimento1();
                    break;
                case 2:
                    movimento2(nave, inimigos);
                    break;
                case 3:
                    movimento3(inimigos);
                    break;
                default:
                    break;
            }
        }

        for (Inimigo atual : inimigos) {
            if (atual.getCoordenadaX() > largura + 400 || atual.getCoordenadaX() < -1 * largura - 400) {
                return atual;
            } else {
                if (atual.getCoordenadaY() > altura + 400 || atual.getCoordenadaY() < -1 * altura - 400) {
                    return atual;
                }
            }
        }
        return null;
    }

    private void movimento1()
    {
        float novoX = (float)(-1 * velocidade * Math.sin(Math.toRadians(spriteInimigo.getRotation())));
        float novoY = (float)(velocidade * Math.cos(Math.toRadians(spriteInimigo.getRotation())));

        spriteInimigo.setX(spriteInimigo.getX() + novoX);
        spriteInimigo.setY(spriteInimigo.getY() + novoY);
    }

    private void movimento2(Sprite nave, LinkedList<Inimigo> inimigos) {
        int rotacao = 0;

        if (this.getCoordenadaX() - 50 > nave.getX()) {
            this.setCoordenadaX(this.getCoordenadaX() - velocidade + 1);
            rotacao = 1;
        } else {
            if (this.getCoordenadaX() + 50 < nave.getX()) {
                this.setCoordenadaX(this.getCoordenadaX() + velocidade - 1);
                rotacao = 2;
            }
        }
        if (this.getCoordenadaY() - 50 > nave.getY()) {
            this.setCoordenadaY(this.getCoordenadaY() - velocidade + 1);
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
            if (this.getCoordenadaY() + 50 < nave.getY()) {
                this.setCoordenadaY(this.getCoordenadaY() + velocidade - 1);
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

    private void movimento3(LinkedList<Inimigo> inimigos)
    {
        for (Inimigo atual : inimigos) {
            if(atual != this) {
                if (this.spriteInimigo.getBoundingRectangle().overlaps(atual.spriteInimigo.getBoundingRectangle())) {
                    atual.estaColado = true;
                    if (this.spriteInimigo.getRotation() == 90) {
                        atual.spriteInimigo.setX(atual.spriteInimigo.getX() - velocidade / 2);
                    } else {
                        atual.spriteInimigo.setX(atual.spriteInimigo.getX() + velocidade / 2);
                    }
                }
            }
        }
        if(this.spriteInimigo.getRotation() == 90)
        {
            this.spriteInimigo.setX(this.spriteInimigo.getX() - velocidade / 2);
        }
        else
        {
            this.spriteInimigo.setX(this.spriteInimigo.getX() + velocidade / 2);
        }
    }

    private void verificarColisaoDaNave(LinkedList<Inimigo> inimigos)
    {
        for (Inimigo atual2 : inimigos) {
            if(this != atual2)
            {
                if(atual2.getVida() == 2) {
                    if(!atual2.estaColado) {
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
}
