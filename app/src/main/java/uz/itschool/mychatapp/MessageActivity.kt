package uz.itschool.mychatapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.itschool.mychatapp.ui.theme.MyChatAppTheme


class MessageActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                        TextField(
                            value = "",
                            onValueChange = {}
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier
                                .size(25.dp)
                                .background(
                                    colorResource(id = R.color.main)
                                ))
                        }
                    }
                }
            }
        }
    }
}
