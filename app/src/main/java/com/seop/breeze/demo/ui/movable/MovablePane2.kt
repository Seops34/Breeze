package com.seop.breeze.demo.ui.movable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun MovablePane2() {
//    MovableCase1()
    MovableCase2()
}

@Composable
private fun MovableCase1() {
    val list = remember {
        mutableStateListOf<String>().apply {
            (0..20).forEach { add("Counter A ${it}") }
        }
    }

    Column {
        Button(
            onClick = { list.removeFirstOrNull() }
        ) {
            Text("Remove First")
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .weight(1F)
        ) {
            list.forEach {
                Text(text = it)
            }
        }
    }
}

@Composable
private fun MovableCase2() {
    val list = remember {
        mutableStateListOf<String>().apply {
            (0..20).forEach { add("Counter A ${it}") }
        }
    }
    val listComposables = list.movable {
        Text(text = it)
    }

    Column {
        Button(
            onClick = { list.removeFirstOrNull() }
        ) {
            Text("Remove First")
        }
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .weight(1F)
        ) {
            list.forEach {
                listComposables(it)
            }
        }
    }
}

@Composable
fun <T> List<T>.movable(
    transform: @Composable (item: T) -> Unit
): @Composable (item: T) -> Unit {
    val composedItems = remember(this) {
        mutableMapOf<T, @Composable () -> Unit>()
    }
    return { item: T ->
        composedItems.getOrPut(item) {
            movableContentOf { transform(item) }
        }.invoke()
    }
}