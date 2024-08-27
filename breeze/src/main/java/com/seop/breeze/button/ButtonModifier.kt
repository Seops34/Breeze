package com.seop.breeze.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.seop.breeze.button.indication.PressedStateIndication

@Composable
fun Modifier.breezeClickable(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        label = "breeze clickable"
    )
    val graphicLayerModifier = Modifier
        .graphicsLayer {
            scaleX = scale; scaleY = scale
        }

    val clickableModifier = Modifier
        .clickable(
            interactionSource = interactionSource,
            indication = PressedStateIndication,
            enabled = enabled,
            onClick = onClick
        )

    return graphicLayerModifier then this then clickableModifier
}