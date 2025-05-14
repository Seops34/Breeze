package com.seop.breeze.demo.ui.counter

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun IncreasedDigitCounter(
    count: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier =  modifier.animateContentSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        count.toString()
            .mapIndexed { index, char -> Digit(char, index, count) }
            .forEach { digit ->
                AnimatedContent(
                    targetState = digit,
                    transitionSpec = {
                        Log.d("seop", "targetState: ${targetState} / initialState: ${initialState}")
                        if (targetState > initialState) {
                            slideInVertically { -it } togetherWith slideOutVertically { it }
                        } else {
                            slideInVertically { it } togetherWith slideOutVertically { -it }
                        }
                    }
                ) { digit ->
                    Text(text = "${digit.digitChar}")
                }
            }
    }
}

data class Digit(
    val digitChar: Char,
    val index: Int,
    val fullNumber: Int
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Digit -> digitChar == other.digitChar
            else -> super.equals(other)
        }
    }
}

operator fun Digit.compareTo(other: Digit): Int {
    return fullNumber.compareTo(other.fullNumber)
}
