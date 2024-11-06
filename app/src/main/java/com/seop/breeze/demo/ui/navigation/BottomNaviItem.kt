package com.seop.breeze.demo.ui.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seop.breeze.demo.ui.common.HorizontalSpacer

@Composable
fun BottomNaviItem(
    screen: Screen,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val roundedCornerShape = RoundedCornerShape(20.dp)
    val animatedHeight by animateDpAsState(
        targetValue = if (isSelected) 36.dp else 26.dp,
        label = "animated height"
    )
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 15.dp else 0.dp,
        label = "animated elevation"
    )
    val animatedIconSize by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 20.dp,
        label = "animated icon size",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val animationRotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        label = "animation rotation",
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val icon = if (animationRotation > 90f) screen.filledIcon else screen.outlinedIcon

    Row(
        modifier = modifier
            .height(animatedHeight)
            .background(
                color = Color.White,
                shape = roundedCornerShape
            )
            .shadow(
                elevation = animatedElevation,
                shape = roundedCornerShape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(animatedIconSize)
                .graphicsLayer {
                    rotationY = animationRotation
                }
        )
        if (isSelected) {
            HorizontalSpacer(size = 5.dp)
            Text(text = screen.title)
        }
    }
}

@Preview
@Composable
private fun PreviewBottomNaviItem() {
    MaterialTheme {
        Column(
            modifier = Modifier.background(Color.White),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BottomNaviItem(
                screen = Screen.HOME,
                onClick = {}
            )
            BottomNaviItem(
                screen = Screen.HOME,
                isSelected = true,
                onClick = {}
            )
        }
    }
}