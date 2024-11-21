package com.seop.breeze.demo.ui.movable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.seop.breeze.demo.ui.theme.BreezeTheme

@Composable
fun MovablePane(
    modifier: Modifier = Modifier
) {
    MovableCase1()
//    MovableCase2()
}

@Composable
private fun MovableCase1() {
    var isRow by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Switch",
            modifier = Modifier.clickable { isRow = !isRow }
        )
        if (isRow) {
            Row(
                modifier = Modifier.weight(1F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LetterBox("A")
                LetterBox("B")
            }
        } else {
            Column(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.Center
            ) {
                LetterBox("A")
                LetterBox("B")
            }
        }
    }
}

@Composable
private fun MovableCase2() {
    // Case 2. movableContentOf
    var isRow by remember { mutableStateOf(false) }
    val boxes = remember {
        movableContentOf {
            LetterBox("A")
            LetterBox("B")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Switch",
            modifier = Modifier.clickable { isRow = !isRow }
        )
        if (isRow) {
            Row(
                modifier = Modifier.weight(1F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                boxes()
            }
        } else {
            Column(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.Center
            ) {
                boxes()
            }
        }
    }
}

@Composable
private fun LetterBox(
    letter: String,
    modifier: Modifier = Modifier
) {
    Log.d("seop", "recompose : ${letter}")
    Box(
        modifier = modifier
            .size(50.dp)
            .background(
                color = Color.Green,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter)
    }
}

@PreviewLightDark
@Composable
private fun PreviewMovablePane() {
    BreezeTheme {
        MovablePane()
    }
}