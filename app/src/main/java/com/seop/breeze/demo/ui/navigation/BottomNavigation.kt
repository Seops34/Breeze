package com.seop.breeze.demo.ui.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(
    screens: List<Screen>,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEachIndexed { index, screen ->
            val isSelected = screen == screens[selectedIndex]
            val animatedWeight by animateFloatAsState(
                targetValue = if (isSelected) 1.5f else 1.0f,
                label = ""
            )

            Box(
                modifier = Modifier.weight(animatedWeight),
                contentAlignment = Alignment.Center
            ) {
                BottomNaviItem(
                    screen = screen,
                    isSelected = screen == screens[selectedIndex],
                    onClick = { selectedIndex = index }
                )
            }
        }
    }

}

@Preview
@Composable
private fun PreviewBottomNavigation() {
    MaterialTheme {
        BottomNavigation(
            screens = Screen.entries
        )
    }
}