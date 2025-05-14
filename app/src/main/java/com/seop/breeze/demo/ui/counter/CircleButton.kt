package com.seop.breeze.demo.ui.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = Color.Black,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun PreviewCounterButton() {
    CircleButton(
        title = "+",
        onClick = {}
    )
}