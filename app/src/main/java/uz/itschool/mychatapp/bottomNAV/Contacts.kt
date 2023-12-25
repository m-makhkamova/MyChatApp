package uz.itschool.mychatapp.bottomNAV

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import uz.itschool.mychatapp.MessageActivity
import uz.itschool.mychatapp.R
import uz.itschool.mychatapp.model.UserData

@Composable
fun Contacts(navController: NavController, uid:String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        val userList = remember {
            mutableStateListOf<UserData>()
        }

        val context = LocalContext.current

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

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Contacts", fontSize = 25.sp, color = Color.Black, modifier = Modifier.padding(0.dp, 70.dp, 0.dp, 0.dp), fontWeight = FontWeight.Bold)
            LazyColumn() {
                items(userList) {
                    val roundedCornerShape = RoundedCornerShape(15.dp)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 15.dp)
                        .border(1.dp, color = Color(0xFF771F98))
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                        .clickable {
                            val i = Intent(context, MessageActivity::class.java)
                            i.putExtra("uid", uid)
                            i.putExtra("useruid", it.uid)
                            context.startActivity(i)
                        }, verticalAlignment = Alignment.CenterVertically){
                        Spacer(modifier = Modifier.padding(start = 10.dp))
                        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(it.photo).crossfade(true).build(),
                            placeholder = painterResource(id = R.drawable.user),
                            contentDescription = ("no image"),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape).size(30.dp))

                        Text(text = it.name?:"", modifier = Modifier.padding(10.dp), fontSize = 20.sp)
                    }
                }
            }
        }

    }
}

