import comp3170.GLBuffers;
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
	
	//Vertices and vertex buffer
	private float[] vertices;
	private int vertexBuffer;
	
	//Colour and colour buffer
	private float[] colours;
	private int colourBuffer;
	
	//Indices and index buffer
	private int [] indices;
	private int indexBuffer;

	public Week2() throws OpenGLException {

		// create a window with a title, size and a listener (this)
		Window window = new Window("Microsoft", width, height, this);
		window.setResizable(true);

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
		
		
		vertices = new float[] {
			-0.8f, 0.4f, //D 0
			-0.2f, 0.6f, //B 1
			-0.6f, 1.f, //C 2
			
			-0.2f, -0.4f, //E 3	
			0.2f, -0.4f, //F 4
			0.2f, 0.6f, // A 5
			0.8f, 0.4f, // G 6
			0.6f, 1.f, //H 7
			
		};
		
		indices = new int[]{
				0, 1, 2, //left ear
				0, 3, 1, //left cheek
				3, 4, 1, //left side of face
				4, 5, 1, //right side of face
				5, 4, 6, //right cheek
				5, 6, 7, //right ear
		};
		
		indexBuffer = GLBuffers.createIndexBuffer(indices);
		
		vertexBuffer = GLBuffers.createBuffer(vertices, GL_FLOAT_VEC2);
		
		// vertex colours
		colours = new float[] {
				1.0f, 1.0f, 1.0f, // D
				0.3f, 0.2f, 1.0f, // B
				1.0f, 0.6f, 1.0f, // C
				
				1.0f, 0.0f, 0.0f, // E
				1.0f, 0.0f, 0.0f, // F
				0.3f, 0.2f, 1.0f, // A
				1.0f, 1.0f, 1.0f, // G
				1.0f, 0.6f, 1.0f, // H

		};
		
		colourBuffer = GLBuffers.createBuffer(colours, GL_FLOAT_VEC3);
		
		
	}

	@Override
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		shader.enable();
		
		shader.setAttribute("a_position", vertexBuffer);
		shader.setAttribute("a_colour", colourBuffer);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);		
		glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);

	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		
		glViewport(0, 0, width, height);
		

	}

	@Override
	public void close() {

	}
}