package com.mmartosdev.photofx.compose.modifiers

import androidx.compose.ui.Modifier

private val shader = """
    uniform float2 resolution;
    uniform shader content; 
    uniform float pixelSize;

    vec4 main(vec2 fragCoord) {
        vec2 uv = fragCoord.xy / resolution.xy;
        float factor = (abs(sin( resolution.y * (uv.y - 0.5) / pixelSize)) + abs(sin( resolution.x * (uv.x - 0.5) / pixelSize))) / 2.0;
        half4 color = content.eval(fragCoord);
        return half4(factor * color.rgb, color.a); 
    }
""".trimIndent()

fun Modifier.smoothPixelationShader(
    pixelSize: Float,
): Modifier =
    this then shader(shader) {
        uniform("pixelSize", pixelSize)
    }
