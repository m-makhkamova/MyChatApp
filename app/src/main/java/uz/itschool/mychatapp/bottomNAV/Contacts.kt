package uz.itschool.mychatapp.bottomNAV

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Contacts(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun testContacts(){
    val navController = rememberNavController()
    NavGraph(navController = (navController))
    Contacts(navController = navController)
}