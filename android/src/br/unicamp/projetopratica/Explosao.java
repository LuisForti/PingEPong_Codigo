package br.unicamp.projetopratica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosao {
    private int frame;
    private Texture animacao1;
    private Texture animacao2;
    private Texture animacao3;
    private Texture animacao4;
    private float x, y;

    public Explosao(float x, float y)
    {
        //animacao = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("explosao.gif").read());
        animacao1 = new Texture(Gdx.files.internal("explosao1.png"));
        animacao2 = new Texture(Gdx.files.internal("explosao2.png"));
        animacao3 = new Texture(Gdx.files.internal("explosao3.png"));
        animacao4 = new Texture(Gdx.files.internal("explosao4.png"));
        frame = 0;
        this.x = x;
        this.y = y;
    }

    public boolean animar(SpriteBatch batch)
    {
        if(frame < 60) {
                batch.begin();
                if(frame < 15)
                {
                    batch.draw(animacao1, x, y);
                }
                else if(frame < 30)
                {
                    batch.draw(animacao2, x, y);
                }
                else if(frame < 45)
                {
                    batch.draw(animacao3, x, y);
                }
                else
                {
                    batch.draw(animacao4, x, y);
                }
                batch.end();
            frame++;
            return true;
        }
        else
        {
            return false;
        }
    }
}