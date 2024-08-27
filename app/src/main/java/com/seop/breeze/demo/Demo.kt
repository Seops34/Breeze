package com.seop.breeze.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seop.breeze.button.breezeClickable
import com.seop.breeze.demo.ui.theme.BreezeTheme

@Composable
fun Demo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp, 50.dp)
                .background(Color.DarkGray, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .breezeClickable {},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Test",
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTest() {
    BreezeTheme {
        Demo()
    }
}