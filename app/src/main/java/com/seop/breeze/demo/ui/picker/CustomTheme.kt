package com.seop.breeze.demo.ui.picker

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.seop.breeze.demo.R

data class CustomTheme(
    val primary: Color,
    val background: Color,
    val text: Color,
    @DrawableRes
    val imgRes: Int
)

val darkTheme = CustomTheme(
    primary = Color(0xFFE9B518),
    background = Color(0xFF111111),
    text = Color(0xffFFFFFF),
    imgRes = R.drawable.dark,
)

val lightTheme = CustomTheme(
    primary = Color(0xFF2CB6DA),
    background = Color(0xFFF1F1F1),
    text = Color(0xff000000),
    imgRes = R.drawable.light,
)

val pinkTheme = CustomTheme(
    primary = Color(0xFFF01EE5),
    background = Color(0xFF110910),
    text = Color(0xFFEE8CE1),
    imgRes = R.drawable.pink,
)