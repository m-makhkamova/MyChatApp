package uz.itschool.mychatapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import uz.itschool.mychatapp.bottomNAV.BottomNav
import uz.itschool.mychatapp.bottomNAV.NavGraph
import uz.itschool.mychatapp.ui.theme.MyChatAppTheme

class HomeActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val selectedIndex = remember { mutableStateOf(0) }
                    Scaffold(
                        bottomBar = {BottomNav(navController = navController, selectedIndex = selectedIndex.value, onItemSelected = {index -> selectedIndex.value == index})},
                topBar = { TopAppBar(title = {Text(text = "Chatting App")})}
                    ) {
                        NavGraph(navController = navController)
                    }

            }
        }
    }

    }
}




