#version 410

in vec2 a_position; // vertex position as a 2D vector

in vec3 a_colour; // vertex colour (RGB)

out vec3 v_colour; // RGB to fragment shader

void main () {
	// Pad the vertex to a homogenous 3D point
	gl_Position = vec4(a_position,0,1);
	
	// pass the colour to the fragment shader
	v_colour = a_colour;
} 