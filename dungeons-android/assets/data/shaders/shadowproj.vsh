// Vertex shader for rendering the scene with shadows.

// Attributes.
attribute vec3 a_position;

// Uniform variables.
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

uniform mat4 LightSourceProjectionViewMatrix[4];
uniform vec4 color;

// The scale matrix is used to push the projected vertex into the 0.0 - 1.0 region.
// Similar in role to a * 0.5 + 0.5, where -1.0 < a < 1.0.
const mat4 ScaleMatrix = mat4(0.5, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.5, 0.5, 0.5, 1.0);


// Varying variables.
varying vec4 vWorldVertex;
varying vec3 vWorldNormal;
varying vec3 vViewVec;
varying vec4 vPosition[4];
varying vec4 vColor;


// Vertex shader entry.
void main ()
{
    vColor = color;
    
    // Standard basic lighting preperation
    vWorldVertex = vec4(a_position, 1.0);
    gl_Position = ProjectionMatrix * ViewMatrix * vWorldVertex;
    
    // Project the vertex from the light's point of view
    vPosition[0] = ScaleMatrix * LightSourceProjectionViewMatrix[0] * vWorldVertex;
    vPosition[1] = ScaleMatrix * LightSourceProjectionViewMatrix[1] * vWorldVertex;
    vPosition[2] = ScaleMatrix * LightSourceProjectionViewMatrix[2] * vWorldVertex;
    vPosition[3] = ScaleMatrix * LightSourceProjectionViewMatrix[3] * vWorldVertex;
}
