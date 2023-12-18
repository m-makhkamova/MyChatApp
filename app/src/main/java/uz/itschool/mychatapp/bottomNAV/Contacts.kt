package uz.itschool.mychatapp.bottomNAV

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import uz.itschool.mychatapp.model.UserData

@Composable
fun Contacts(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        val intent = Intent()
        val uid = intent.getStringExtra("uid")
        val userList = remember {
            mutableStateListOf(UserData())
        }

        val reference = Firebase.database.reference.child("users")
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val u = snapshot.children
                userList.clear()
                u.forEach{
                    val userData = it.getValue(UserData::class.java)
                    userList.clear()
                    if(userData != null && uid != userData.uid){
                        userList.add(userData)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "error: ${error.message}")
            }
        })

        
        LazyColumn{
            item(userList){
                ContactCard(contact = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testContacts(){
    val navController = rememberNavController()
    NavGraph(navController = (navController))
    Contacts(navController = navController)
}

@Composable
fun ContactCard(contact:UserData){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, color = Color(0xFF771F98)),
        elevation = 5.dp
    ){
        val painter = rememberImagePainter(data = contact.photo,
                builder = {
                    transformations(
                        GrayscaleTransformation(),       // Gray Scale Transformation
                        CircleCropTransformation()       // Circle Crop Transformation
                    )
                })
        Row {
            Image(painter = painter, contentDescription = "Photo")
            contact.name?.let { Text(text = it, fontSize = 16.sp) }
        }
    }
}