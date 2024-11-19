package com.seop.breeze.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.seop.breeze.demo.ui.movable.MovablePane
import com.seop.breeze.demo.ui.movable.MovablePane2
import com.seop.breeze.demo.ui.picker.PickerPane
import com.seop.breeze.demo.ui.theme.BreezeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreezeTheme {
                MovablePane2()
            }
        }
    }
}