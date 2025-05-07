package com.aryastefhani0140.miniproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aryastefhani0140.miniproject2.navigation.SetupNavGraph
import com.aryastefhani0140.miniproject2.ui.theme.Miniproject2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Miniproject2Theme {
                SetupNavGraph()
            }
        }
    }
}
