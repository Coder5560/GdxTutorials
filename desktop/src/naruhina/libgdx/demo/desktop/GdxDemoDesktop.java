package naruhina.libgdx.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dongbat.example.AiDemo;
import com.dongbat.example.util.R;

public class GdxDemoDesktop {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = R.WIDTH_SCREEN;
		config.height = R.HEIGHT_SCREEN;
		config.title = "Libgdx Demo";
		new LwjglApplication(new AiDemo(), config);
//		new LwjglApplication(new DemoMessageHandle(), config);
	}
}
