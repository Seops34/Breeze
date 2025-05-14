package com.seop.breeze.demo.ui.counter

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

// https://www.sinasamaki.com/animated-counter/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Gray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TypeA()

        TypeB()

        TypeC()
    }
}

@Composable
private fun TypeA(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircleButton(
            title = "-",
            onClick = { count }
        )

        // Type A
        Text(text = "${count}")

        CircleButton(
            title = "+",
            onClick = { count++ }
        )
    }
}

@Composable
private fun TypeB(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircleButton(
            title = "-",
            onClick = { count-- }
        )

        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { -it } togetherWith slideOutVertically { it }
                } else {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            }
        ) {
            Text(text = "${it}")
        }

        CircleButton(
            title = "+",
            onClick = { count++ }
        )
    }
}

@Composable
private fun TypeC(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircleButton(
            title = "-",
            onClick = { count-- }
        )

        IncreasedDigitCounter(
            count = count
        )

        CircleButton(
            title = "+",
            onClick = { count++ }
        )
    }
}


@Preview
@Composable
private fun PreviewAnimatedCounterA() {
    AnimatedCounter()
}

