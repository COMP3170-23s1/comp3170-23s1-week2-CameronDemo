import comp3170.IWindowListener;
import comp3170.OpenGLException;
import comp3170.Window;
import comp3170.Shader;

import java.io.File;
import java.io.IOException;

import static org.lwjgl.opengl.GL41.*;

public class Week2 implements IWindowListener {

	private Shader shader;

	final private File DIRECTORY = new File("src/");
	final private String VERTEX_SHADER = "vertex.glsl";
	final private String FRAGMENT_SHADER = "fragment.glsl";

	private int width = 800;
	private int height = 800;

	public Week2() throws OpenGLException {

		// create a window with a title, size and a listener (this)
		Window window = new Window("Microsoft", width, height, this);

		// start running the window
		window.run();
	}

	public static void main(String[] args) throws OpenGLException {
		new Week2();
	}

	@Override
	public void init() {
		glClearColor(0.f,0.7f,1f,1f); //RGBA
		
		//Compile the shader
		try {
			File vertexShader = new File(DIRECTORY, VERTEX_SHADER);
			File fragmentShader = new File(DIRECTORY, FRAGMENT_SHADER);
			shader = new Shader(vertexShader, fragmentShader);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (OpenGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

	@Override
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void close() {

	}
}