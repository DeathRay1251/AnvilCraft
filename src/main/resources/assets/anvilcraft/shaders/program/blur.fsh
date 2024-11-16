#version 150

uniform sampler2D DiffuseSampler;

uniform vec2 InSize;
uniform vec2 BlurDir;
uniform float weight[7] = float[] (0.227027, 0.1945946, 0.1516216, 0.124054, 0.016216, 0.0111, 0.0100);

in vec2 texCoord;
in vec2 texelSize;

out vec4 fragColor;

float apply(float f){
    return - (f - 1) * (f - 1) + 1;
}

void main() {
    vec2 texOffset = 1.0 / textureSize(DiffuseSampler, 0);

    vec3 result = texture(DiffuseSampler, texCoord).rgb * weight[0];
    bool horizontal = BlurDir.x == 1.0;
    if (horizontal) {
        for (int i = 1; i < 7; ++i) {
            result += texture(DiffuseSampler, texCoord + vec2(texOffset.x * i, 0.0)).rgb * weight[i];
            result += texture(DiffuseSampler, texCoord - vec2(texOffset.x * i, 0.0)).rgb * weight[i];
        }
    } else {
        for (int i = 1; i < 7; ++i) {
            result += texture(DiffuseSampler, texCoord + vec2(0.0, texOffset.y * i)).rgb * weight[i];
            result += texture(DiffuseSampler, texCoord - vec2(0.0, texOffset.y * i)).rgb * weight[i];
        }
    }
    vec3 color = vec3(apply(result.r), apply(result.g), apply(result.b));
    fragColor = vec4(result.rgb * 1.105, 1.0);
}