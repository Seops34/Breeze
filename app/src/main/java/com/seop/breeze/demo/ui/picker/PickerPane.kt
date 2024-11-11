package com.seop.breeze.demo.ui.picker

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PickerPane() {
    var theme by remember { mutableStateOf(lightTheme) }
    var animationOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    AnimatedContent(
        targetState = theme,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        transitionSpec = {
            fadeIn(
                initialAlpha = 0f,
                animationSpec = tween(300)
            ).togetherWith(
                fadeOut(
                    targetAlpha = .9f,
                    animationSpec = tween(800)
                )
            )
        }
    ) { currentTheme ->
        val revealSize = remember { Animatable(1f) }
        LaunchedEffect("reveal") {
            if (animationOffset.x > 0f) {
                revealSize.snapTo(0f)
                revealSize.animateTo(1f, animationSpec = tween(800))
            } else {
                revealSize.snapTo(1f)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    CirclePath(
                        progress = revealSize.value,
                        startPos = animationOffset
                    )
                )
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = currentTheme.background
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        Image(
                            painter = painterResource(id = currentTheme.imgRes),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            currentTheme.background.copy(alpha = .2f),
                                            currentTheme.background
                                        )
                                    )
                                )
                        )
                    }

                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ThemeButton(
                            theme = lightTheme,
                            onClick = {
                                animationOffset = it
                                theme = lightTheme
                            }
                        )
                        ThemeButton(
                            theme = pinkTheme,
                            onClick = {
                                animationOffset = it
                                theme = pinkTheme
                            }
                        )
                        ThemeButton(
                            theme = darkTheme,
                            onClick = {
                                animationOffset = it
                                theme = darkTheme
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ThemeButton(
    theme: CustomTheme,
    onClick: (Offset) -> Unit
) {
    var offset = remember { Offset.Zero }

    Image(
        painter = painterResource(theme.imgRes),
        contentDescription = null,
        modifier = Modifier
            .onGloballyPositioned {
                offset = Offset(
                    x = it.positionInWindow().x + it.size.width / 2,
                    y = it.positionInWindow().y + it.size.height / 2
                )
            }
            .size(100.dp)
            .border(1.dp, Color.Black, CircleShape)
            .clip(CircleShape)
            .clickable { onClick(offset) },
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun PreviewPickerPane() {
    MaterialTheme {
        PickerPane()
    }
}