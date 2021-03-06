package br.unicamp.projetopratica.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.unicamp.projetopratica.MyGdxGame;
import br.unicamp.projetopratica.TelaPrincipal;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TouchPadTest";
		config.width = 1280;
		config.height = 720;

		new LwjglApplication(new TelaPrincipal(), config);
	}
}
