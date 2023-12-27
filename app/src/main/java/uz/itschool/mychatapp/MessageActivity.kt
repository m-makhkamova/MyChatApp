package uz.itschool.mychatapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import uz.itschool.mychatapp.model.Message
import uz.itschool.mychatapp.model.UserData
import uz.itschool.mychatapp.ui.theme.MyChatAppTheme


class MessageActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Message()
            }
        }
    @Preview(showBackground = true)
    @Composable
    fun Message(){
        MyChatAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Row(
                    modifier = Modifier
                        .background(colorResource(id = R.color.white))
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    TextField(
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(painterResource(id = R.drawable.baseline_send_24), contentDescription = null, modifier = Modifier.size(25.dp))

                    val messageList = remember {
                        mutableStateListOf<Message>()
                    }
                    val reference = Firebase.database.reference.child("users").child("message")
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val m = snapshot.children
                            messageList.clear()
                            m.forEach{
                                val message = it.getValue(Message::class.java)
                                if(message != null)
                                messageList.add(message)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.d("TAG", "error: ${error.message}")
                        }
                    })
                }
            }
        }
    }
    }

