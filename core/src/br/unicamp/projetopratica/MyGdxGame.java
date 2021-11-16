package br.unicamp.projetopratica;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.LinkedList;

public class MyGdxGame implements Screen {
	//Componentes para os touchpads e a nave principal
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Touchpad touchpad;
	private Touchpad.TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Texture blockTexture;
	private Touchpad touchpad2;
	private Touchpad.TouchpadStyle touchpadStyle2;
	private Skin touchpadSkin2;
	private Drawable touchBackground2;
	private Drawable touchKnob2;
	private Texture blockTexture2;
	private Sprite blockSprite;
	private float blockSpeed;

	//Jogador
	private Sprite tiro;
	private int repulsao = 0;
	private int repulsao2 = 0;
	private float posicaoAntigaX = 0;
	private float posicaoAntigaY = 0;
	private LinkedList<Inimigo> inimigos;
	private int rotacao;
	private boolean estaVivo = true;

	//Tempo e invocação
	private double frame = 1499.5;
	private int quantosInvocarTotal = 12;
	private int quantosInvocar = 10;
	private int quantosInvocar2 = 1;
	private int quantosInvocar3 = 1;
	private int quantosInvocouTotal = 0;
	private int quantosInvocou = 0;
	private int quantosInvocou2 = 0;
	private int quantosInvocou3 = 0;
	private double tempoDesdeAUltimaHorda = 1500.5;
	private int qualHorda = 1;

	//Pontuação
	private int score;
	private String yourScoreName;
	private BitmapFont yourBitmapFontName;

	//Tamanho da tela
	private float largura;
	private float altura;

	public void setTamanhoDaTela(float largura, float altura)
	{
		this.largura = largura;
		this.altura = altura;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		//Create camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10f*largura / altura, 10f);

		criarJoystics();

		//Create block sprite
		blockTexture = new Texture(Gdx.files.internal("nave.png"));
		blockSprite = new Sprite(blockTexture, 0, 0, 50, 50);
		//Set position to centre of the screen
		blockSprite.setPosition(largura/2-blockSprite.getWidth()/2, altura/2-blockSprite.getHeight()/2);
		blockSprite.setScale(2);

		blockTexture = new Texture(Gdx.files.internal("tiro.png"));
		tiro = new Sprite(blockTexture, 0, 0, 50, 50);
		//Set position to centre of the screen
		tiro.setPosition(blockSprite.getX() + 300, blockSprite.getY());
		tiro.setScale(2);

		inimigos = new LinkedList<>();

		blockSpeed = 10;

		score = 0;
		yourScoreName = "score: 0";
		yourBitmapFontName = new BitmapFont();
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
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render(float delta) {
		frame += 0.5;
		if(quantosInvocou < quantosInvocar) {
			if (frame == (tempoDesdeAUltimaHorda + ((150 / quantosInvocar) * (quantosInvocou)))) {
				quantosInvocouTotal++;
				quantosInvocou++;
				if (quantosInvocou % 4 == 0) {
					inimigos.add(new Inimigo((byte) 1, "inimigo1.png", 33, 50, (float) (Math.random() * 200) + largura, quantosInvocouTotal * 6));
				} else if (quantosInvocou % 3 == 0) {
					inimigos.add(new Inimigo((byte) 1, "inimigo1.png", 33, 50, (float) (Math.random() * 100) - 200, quantosInvocouTotal * 6));
				} else if (quantosInvocou % 2 == 0) {
					inimigos.add(new Inimigo((byte) 1, "inimigo1.png", 33, 50, quantosInvocouTotal * 12, (float) (Math.random() * 100) - 200));
				} else {
					inimigos.add(new Inimigo((byte) 1, "inimigo1.png", 33, 50, quantosInvocouTotal * 12, (float) (Math.random() * 200) + altura));
				}
			}
		}
		if (frame == (tempoDesdeAUltimaHorda + ((300 / quantosInvocar2) * (quantosInvocou2)))) {
			quantosInvocouTotal++;
			quantosInvocou2++;
			if (quantosInvocou2 % 4 == 0) {
				inimigos.add(new Inimigo((byte) 2, "inimigo2.png", 50, 50,(float) (Math.random() * 200) + largura, quantosInvocouTotal * 6));
			} else if (quantosInvocou2 % 3 == 0) {
				inimigos.add(new Inimigo((byte) 2, "inimigo2.png", 50, 50,(float) (Math.random() * 100) - 200, quantosInvocouTotal * 6));
			} else if (quantosInvocou2 % 2 == 0) {
				inimigos.add(new Inimigo((byte) 2, "inimigo2.png", 50, 50,quantosInvocouTotal * 12, (float) (Math.random() * 100) - 200));
			} else {
				inimigos.add(new Inimigo((byte) 2, "inimigo2.png", 50, 50,quantosInvocouTotal * 12, (float) (Math.random() * 200) + altura));
			}
		}
		if (frame == (tempoDesdeAUltimaHorda + ((300 / quantosInvocar3) * (quantosInvocou3)))) {
			quantosInvocouTotal++;
			quantosInvocou3++;
			if (quantosInvocou3 % 2 == 0) {
				inimigos.add(new Inimigo((byte) 3, "inimigo3.png", 75, 75,-1, altura / 2));
			}
			else {
				inimigos.add(new Inimigo((byte) 3, "inimigo3.png", 75, 75, largura, altura / 2));
			}
		}
		if (frame % 300 == 0) {
			quantosInvocar += 5;
			while (quantosInvocar > (30 * (frame / 1500))) {
				quantosInvocar2++;
				quantosInvocar -= 20;
			}
			while (quantosInvocar2 > 10 * (frame / 1500)) {
				quantosInvocar2 -= 5;
				quantosInvocar3++;
			}
			quantosInvocarTotal = quantosInvocar + quantosInvocar2 + quantosInvocar3;
			quantosInvocarTotal = 0;
			quantosInvocou = 0;
			quantosInvocou2 = 0;
			quantosInvocou3 = 0;
			tempoDesdeAUltimaHorda = (int) frame + 0.5;
			qualHorda++;
		}
		if (estaVivo) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
			camera.update();

			batch.begin();
			yourBitmapFontName.setColor(1, 1, 1, 1);
			yourBitmapFontName.getData().setScale(5);
			yourBitmapFontName.draw(batch, yourScoreName, 50, altura - 10);
			blockSprite.draw(batch);
			tiro.draw(batch);
			batch.end();

			blockSprite.setX(blockSprite.getX() + touchpad.getKnobPercentX() * (blockSpeed - 2));
			blockSprite.setY(blockSprite.getY() + touchpad.getKnobPercentY() * (blockSpeed - 2));

			if (touchpad.getKnobPercentX() != 0) {
				if (touchpad.getKnobPercentY() < 0) {
					blockSprite.setRotation((touchpad.getKnobPercentX() * 90) + 180);
				} else {
					blockSprite.setRotation(touchpad.getKnobPercentX() * -90);
				}
			}

			tiro.setX(tiro.getX() + touchpad2.getKnobPercentX() * blockSpeed);
			tiro.setY(tiro.getY() + touchpad2.getKnobPercentY() * blockSpeed);

			if (touchpad2.getKnobPercentX() != 0) {
				if (touchpad2.getKnobPercentY() < 0) {
					tiro.setRotation((touchpad2.getKnobPercentX() * 90) + 180);
				} else {
					tiro.setRotation(touchpad2.getKnobPercentX() * -90);
				}
			}

			if (tiro.getBoundingRectangle().overlaps(blockSprite.getBoundingRectangle())) {
				repulsao = 15;
			}
			if (repulsao > 0) {
				repulsao--;

				if (tiro.getX() - 50 > blockSprite.getX()) {
					tiro.setX(tiro.getX() + 3 * repulsao);
					blockSprite.setX(blockSprite.getX() - 1 * repulsao);
				} else {
					if (tiro.getX() + 50 < blockSprite.getX()) {
						tiro.setX(tiro.getX() - 3 * repulsao);
						blockSprite.setX(blockSprite.getX() + 1 * repulsao);
					}
				}
				if (tiro.getY() - 50 > blockSprite.getY()) {
					tiro.setY(tiro.getY() + 3 * repulsao);
					blockSprite.setY(blockSprite.getY() - 1 * repulsao);
				} else {
					if (tiro.getY() + 50 < blockSprite.getY()) {
						tiro.setY(tiro.getY() - 3 * repulsao);
						blockSprite.setY(blockSprite.getY() + 1 * repulsao);
					}
				}
			}
			if (repulsao2 != 0) {
				repulsao2--;

				if (tiro.getX() - 50 > posicaoAntigaX) {
					tiro.setX(tiro.getX() + 2 * repulsao2);
				} else {
					if (tiro.getX() + 50 < posicaoAntigaX) {
						tiro.setX(tiro.getX() - 2 * repulsao2);
					}
				}
				if (tiro.getY() - 50 > posicaoAntigaY) {
					tiro.setY(tiro.getY() + 2 * repulsao2);
				} else {
					if (tiro.getY() + 50 < posicaoAntigaY) {
						tiro.setY(tiro.getY() - 2 * repulsao2);
					}
				}
			}

			if (blockSprite.getX() > largura - 100) {
				blockSprite.setX(largura - 100);
			}
			if (blockSprite.getX() < 10) {
				blockSprite.setX(10);
			}
			if (blockSprite.getY() > altura - 100) {
				blockSprite.setY(altura - 100);
			}
			if (blockSprite.getY() < 50) {
				blockSprite.setY(50);
			}
			if (tiro.getX() > largura - 100) {
				tiro.setX(largura - 100);
			}
			if (tiro.getX() < 10) {
				tiro.setX(10);
			}
			if (tiro.getY() > altura - 100) {
				tiro.setY(altura - 100);
			}
			if (tiro.getY() < 50) {
				tiro.setY(50);
			}

			int posicaoAtual = 0;
			int posicaoARemover = -1;
			Inimigo objetoForaDoMapa = null;

			for (Inimigo atual : inimigos) {
				if (blockSprite.getBoundingRectangle().overlaps(atual.getSpriteInimigo().getBoundingRectangle())) {
					estaVivo = false;
					break;
				}
				if (tiro.getBoundingRectangle().overlaps(atual.getSpriteInimigo().getBoundingRectangle())) {
					atual.setVida((byte) (atual.getVida() - 1));
					if (atual.getVida() <= 0) {
						posicaoARemover = posicaoAtual;
						score += qualHorda * 10 * atual.getVidaInicial();
						yourScoreName = "score: " + score;
					}
					posicaoAntigaX = atual.getCoordenadaX();
					posicaoAntigaY = atual.getCoordenadaY();
					repulsao2 = 14;
				}
				if (atual.getVida() > 0) {
					objetoForaDoMapa = atual.movimentar(blockSprite, inimigos, largura, altura);
					posicaoAtual++;

					batch.begin();
					atual.getSpriteInimigo().draw(batch);
					batch.end();
				}
			}

			if (posicaoARemover != -1) {
				inimigos.remove(posicaoARemover);
			}

			if (objetoForaDoMapa != null) {
				inimigos.remove(objetoForaDoMapa);
			}

			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
	}

	public boolean estaVivo()
	{
		return estaVivo;
	}

	private void criarJoystics()
	{
		//Create a touchpad skin
		touchpadSkin = new Skin();
		touchpadSkin2 = new Skin();
		//Set background image
		touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
		touchpadSkin2.add("touchBackground", new Texture("touchBackground.png"));
		//Set knob image
		touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
		touchpadSkin2.add("touchKnob", new Texture("touchKnob.png"));
		//Create TouchPad Style
		touchpadStyle = new Touchpad.TouchpadStyle();
		touchpadStyle2 = new Touchpad.TouchpadStyle();
		//Create Drawable's from TouchPad skin
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		touchBackground2 = touchpadSkin.getDrawable("touchBackground");
		touchKnob2 = touchpadSkin.getDrawable("touchKnob");
		//Apply the Drawables to the TouchPad Style
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpadStyle2.background = touchBackground;
		touchpadStyle2.knob = touchKnob;
		//Create new TouchPad with the created style
		touchpad = new Touchpad(10, touchpadStyle);
		touchpad2 = new Touchpad(10, touchpadStyle2);
		//setBounds(x,y,width,height)
		touchpad.setBounds(50, 50, largura / 5, largura / 5);
		touchpad2.setBounds(largura - (largura / 5) - 100, 50, largura / 5, largura / 5);

		//Create a Stage and add TouchPad
		stage = new Stage(new StretchViewport(largura, altura, camera), batch);
		stage.addActor(touchpad);
		stage.addActor(touchpad2);
		Gdx.input.setInputProcessor(stage);
	}
}
