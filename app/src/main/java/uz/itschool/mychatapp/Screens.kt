package uz.itschool.mychatapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens(val route:String, val label: String, val icon:(Int)->Int){
    object Contacts:Screens("contacts_activity", "Contacts", {R.drawable.contact_icon})
    object Profile:Screens("profile_activity", "Profile", {R.drawable.user_icon})
}

@Composable
fun SetupBottomNav(navController: NavHostController) {
    NavHost(navController, startDestination = Screens.Contacts.route) {
        composable(Screens.Contacts.route) {
            Contacts(navController = navController)
        }
        composable(Screens.Profile.route) {
            Profile(navController = navController)
        }
    }
    BottomNavigation(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
    backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        BottomNavScreens.values().forEach { screen ->
            BottomNavigationItem(
                icon = screen.icon,
                label = { androidx.compose.material3.Text(screen.label) },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}


