#version 410

in vec3 v_colour; // from vertex shader (RGB)

layout(location = 0) out vec4 o_colour; // (r,g,b,a)

void main() {
	o_colour = vec4(v_colour, 1);
}