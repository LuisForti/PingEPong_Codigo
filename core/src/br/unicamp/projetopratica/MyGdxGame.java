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

	//Demais
	private Sprite tiro;
	private int repulsao = 0;
	private int repulsao2 = 0;
	private float posicaoAntigaX = 0;
	private float posicaoAntigaY = 0;
	private LinkedList<Inimigo> inimigos;
	private int rotacao;
	private boolean estaVivo = true;

	//Tempo e invocação
	double frame = 1500;
	int quantosInvocarTotal = 11;
	int quantosInvocar = 10;
	int quantosInvocar2 = 1;
	int quantosInvocar3 = 0;
	int quantosInvocouTotal = 0;
	int quantosInvocou1 = 0;
	int quantosInvocou2 = 0;
	int quantosInvocou3 = 0;
	int tempoDesdeAUltimaHorda = 0;

	//Pontuação
	private int score;
	private String yourScoreName;
	BitmapFont yourBitmapFontName;

	@Override
	public void show() {
		batch = new SpriteBatch();
		//Create camera
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10f*aspectRatio, 10f);

		criarJoystics();

		//Create block sprite
		blockTexture = new Texture(Gdx.files.internal("nave.png"));
		blockSprite = new Sprite(blockTexture, 0, 0, 50, 50);
		//Set position to centre of the screen
		blockSprite.setPosition(Gdx.graphics.getWidth()/2-blockSprite.getWidth()/2, Gdx.graphics.getHeight()/2-blockSprite.getHeight()/2);

		blockTexture = new Texture(Gdx.files.internal("tiro.png"));
		tiro = new Sprite(blockTexture, 0, 0, 50, 50);
		//Set position to centre of the screen
		tiro.setPosition(800, 300);

		inimigos = new LinkedList<>();

		blockSpeed = 7;

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
		boolean deveContinuar = true;
		if(frame != 1500.5) {
			if (touchpad2.getKnobPercentX() == 0) {
				if (touchpad2.getKnobPercentY() == 0) {
					if (touchpad.getKnobPercentX() == 0) {
						if (touchpad.getKnobPercentY() == 0) {
							deveContinuar = false;
						}
					}
				}
			}
		}
		if(deveContinuar) {
			if (frame % (60 / (int) (frame / 1500)) == 0) {
				if (quantosInvocar - quantosInvocou1 > 0) {
					for (int i = 0; i < quantosInvocar / (5 * (frame / 3000)); i++) {
						quantosInvocou1++;
						quantosInvocarTotal++;
						if (quantosInvocarTotal % 4 == 0) {
							inimigos.add(new Inimigo((byte) 1, "inimigo1.png", (float) (Math.random() * 200) + 1200, quantosInvocarTotal * 6));
						} else if (quantosInvocarTotal % 3 == 0) {
							inimigos.add(new Inimigo((byte) 1, "inimigo1.png", (float) (Math.random() * 100) - 200, quantosInvocarTotal * 6));
						} else if (quantosInvocarTotal % 2 == 0) {
							inimigos.add(new Inimigo((byte) 1, "inimigo1.png", quantosInvocarTotal * 12, (float) (Math.random() * 100) - 200));
						} else {
							inimigos.add(new Inimigo((byte) 1, "inimigo1.png", quantosInvocarTotal * 12, (float) (Math.random() * 200) + 700));
						}
					}
				}
			}
			if (frame == (tempoDesdeAUltimaHorda + ((300 / quantosInvocar2) * (quantosInvocou2 + 1)))) {
				quantosInvocouTotal++;
				quantosInvocou2++;
				if (quantosInvocarTotal % 4 == 0) {
					inimigos.add(new Inimigo((byte) 2, "inimigo2.png", (float) (Math.random() * 200) + 1200, quantosInvocarTotal * 6));
				} else if (quantosInvocarTotal % 3 == 0) {
					inimigos.add(new Inimigo((byte) 2, "inimigo2.png", (float) (Math.random() * 100) - 200, quantosInvocarTotal * 6));
				} else if (quantosInvocarTotal % 2 == 0) {
					inimigos.add(new Inimigo((byte) 2, "inimigo2.png", quantosInvocarTotal * 12, (float) (Math.random() * 100) - 200));
				} else {
					inimigos.add(new Inimigo((byte) 2, "inimigo2.png", quantosInvocarTotal * 12, (float) (Math.random() * 200) + 700));
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
				quantosInvocarTotal = quantosInvocar + quantosInvocar2;
				quantosInvocarTotal = 0;
				quantosInvocou1 = 0;
				quantosInvocou2 = 0;
				quantosInvocou3 = 0;
				tempoDesdeAUltimaHorda = (int) frame;
			}
			if (estaVivo) {
				Gdx.gl.glClearColor(0, 0, 0, 1);
				Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
				camera.update();

				batch.begin();
				yourBitmapFontName.setColor(1, 1, 1, 1);
				yourBitmapFontName.draw(batch, yourScoreName, 50, 700);
				blockSprite.draw(batch);
				tiro.draw(batch);
				batch.end();

				blockSprite.setX(blockSprite.getX() + touchpad.getKnobPercentX() * (blockSpeed - 1));
				blockSprite.setY(blockSprite.getY() + touchpad.getKnobPercentY() * (blockSpeed - 1));

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
					repulsao = 13;
				}
				if (repulsao > 0) {
					repulsao--;

					if (tiro.getX() - 25 > blockSprite.getX()) {
						tiro.setX(tiro.getX() + 3 * repulsao);
						blockSprite.setX(blockSprite.getX() - 1 * repulsao);
					} else {
						if (tiro.getX() + 25 < blockSprite.getX()) {
							tiro.setX(tiro.getX() - 3 * repulsao);
							blockSprite.setX(blockSprite.getX() + 1 * repulsao);
						}
					}
					if (tiro.getY() - 25 > blockSprite.getY()) {
						tiro.setY(tiro.getY() + 3 * repulsao);
						blockSprite.setY(blockSprite.getY() - 1 * repulsao);
					} else {
						if (tiro.getY() + 25 < blockSprite.getY()) {
							tiro.setY(tiro.getY() - 3 * repulsao);
							blockSprite.setY(blockSprite.getY() + 1 * repulsao);
						}
					}
				}
				if (repulsao2 != 0) {
					repulsao2--;

					if (tiro.getX() - 25 > posicaoAntigaX) {
						tiro.setX(tiro.getX() + 2 * repulsao2);
					} else {
						if (tiro.getX() + 25 < posicaoAntigaX) {
							tiro.setX(tiro.getX() - 2 * repulsao2);
						}
					}
					if (tiro.getY() - 25 > posicaoAntigaY) {
						tiro.setY(tiro.getY() + 2 * repulsao2);
					} else {
						if (tiro.getY() + 25 < posicaoAntigaY) {
							tiro.setY(tiro.getY() - 2 * repulsao2);
						}
					}
				}

				if (blockSprite.getX() > 1200) {
					blockSprite.setX(1200);
				}
				if (blockSprite.getX() < 10) {
					blockSprite.setX(10);
				}
				if (blockSprite.getY() > 660) {
					blockSprite.setY(660);
				}
				if (blockSprite.getY() < 25) {
					blockSprite.setY(25);
				}
				if (tiro.getX() > 1200) {
					tiro.setX(1200);
				}
				if (tiro.getX() < 10) {
					tiro.setX(10);
				}
				if (tiro.getY() > 660) {
					tiro.setY(660);
				}
				if (tiro.getY() < 25) {
					tiro.setY(25);
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
							score += 10 * atual.getVidaInicial();
							yourScoreName = "score: " + score;
						}
						posicaoAntigaX = atual.getCoordenadaX();
						posicaoAntigaY = atual.getCoordenadaY();
						repulsao2 = 8;
					}
					if (atual.getVida() > 0) {
						objetoForaDoMapa = atual.movimentar(blockSprite, inimigos);
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
		touchpad.setBounds(15, 15, 200, 200);
		touchpad2.setBounds(1050, 15, 200, 200);

		//Create a Stage and add TouchPad
		stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), batch);
		stage.addActor(touchpad);
		stage.addActor(touchpad2);
		Gdx.input.setInputProcessor(stage);
	}
}
