package uz.itschool.mychatapp.bottomNAV


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itschool.mychatapp.R

sealed class Screens(val route:String, val icon:ImageVector){
    object Contacts: Screens("contacts_activity", Icons.Default.Call)
    object Profile: Screens("profile_activity", Icons.Default.Person)
}

@Composable
fun NavGraph(navController: NavHostController, uid:String) {
    NavHost(navController, startDestination = Screens.Contacts.route) {
        composable(Screens.Contacts.route) {
            Contacts(navController = navController, uid = uid)
        }
        composable(Screens.Profile.route) {
            Profile(navController = navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavController, selectedIndex:Int, onItemSelected:(Int)->Unit) {
    BottomNavigation(backgroundColor = colorResource(id = R.color.bottom_Nav)) {
        val items = listOf(
            Screens.Contacts, Screens.Profile
        )

        items.forEach {
            BottomNavigationItem(
                selected = selectedIndex == 0,
                onClick = {navController.navigate(it.route)
                          onItemSelected(0) },
                icon = {Icon(
                    imageVector = it.icon,
                    contentDescription = "Icon",
                    tint = if(selectedIndex == 0) colorResource(id = R.color.main) else colorResource(
                        id = R.color.black
                    ))},
                selectedContentColor = Color(0xFF771F98),
                unselectedContentColor = Color.Black
            )
        }
    }
}