package uz.itschool.mychatapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.itschool.mychatapp.LogInActivity
import uz.itschool.mychatapp.R
import uz.itschool.mychatapp.ui.theme.MyChatAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFFFFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val poppinsFamily = FontFamily(
                                Font(R.font.poppins_bold, FontWeight.Bold),
                                Font(R.font.poppins_black, FontWeight.Black),
                                Font(R.font.poppins_light, FontWeight.Normal),
                                Font(R.font.poppins_thin, FontWeight.Thin)
                            )
                            Text(text = "Get Closer to EveryOne", fontSize = 40.sp, modifier = Modifier.padding(40.dp, 70.dp,0.dp,0.dp), fontFamily = poppinsFamily, fontWeight = FontWeight.Bold)
                            Text(text = "Helps you to contact everyone with just easy way", fontSize = 15.sp, modifier = Modifier.padding(40.dp, 0.dp, 30.dp, 0.dp), fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
                            Image(painter = painterResource(id = R.drawable.splash_img), contentDescription = "Splash image", modifier = Modifier
                                .height(450.dp)
                                .width(450.dp)
                                .padding(30.dp, 50.dp, 30.dp, 0.dp))
                            Button(onClick = {
                                var intent = Intent(this@SplashActivity, LogInActivity::class.java)
                                startActivity(intent)
                            }, modifier = Modifier
                                .width(300.dp)
                                .height(45.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF771F98))) {
                                Text(text = "Get Started", fontSize = 18.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
                            }
                        }
                    }
                }
            }
        }
    }
}




