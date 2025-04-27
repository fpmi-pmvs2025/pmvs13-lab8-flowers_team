package bsu.pi_13.flowers_team

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import bsu.pi_13.flowers_team.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation()
        }
    }
}