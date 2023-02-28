#version 410

in vec2 a_position; // vertex position as a 2D vector

void main () {
	// Pad the vertex to a homogenous 3D point
	gl_Position = vec4(a_position,0,1);
} 