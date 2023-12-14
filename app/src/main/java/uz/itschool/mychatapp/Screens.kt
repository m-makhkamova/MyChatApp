package uz.itschool.mychatapp


import android.widget.ImageView
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens(val route:String, val label: String, val icon:ImageVector){
    object Contacts:Screens("contacts_activity", "Contacts", Icons.Default.Call)
    object Profile:Screens("profile_activity", "Profile", Icons.Default.Person)
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screens.Contacts.route) {
        composable(Screens.Contacts.route) {
            Contacts(navController = navController)
        }
        composable(Screens.Profile.route) {
            Profile(navController = navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavController){
BottomNavigation(backgroundColor = colorResource(id = R.color.main)){
    BottomNavigationItem(
        selected = navController.currentDestination?.route == Screens.Contacts.route,
        onClick = { navController.navigate(Screens.Contacts.route)},
        icon = Icons.Default.Call

    BottomNavigationItem(
        selected = navController.currentDestination?.route == Screens.Profile.route,
        onClick = { navController.navigate(Screens.Profile.route)},
        icon = Icons.Default.Person)


}

}




