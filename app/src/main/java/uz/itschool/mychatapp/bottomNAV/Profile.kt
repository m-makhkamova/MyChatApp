package uz.itschool.mychatapp.bottomNAV

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import uz.itschool.mychatapp.R




@Composable
fun Profile(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 100.dp, 0.dp, 0.dp)
    ) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(stringResource(id = R.string.client_id))
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)


        val roundedCornerShape = RoundedCornerShape(15.dp)
        Column {
            Row(modifier = Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(currentUser?.photoUrl).crossfade(true).build(),
                    placeholder = painterResource(id = R.drawable.user),
                    contentDescription = ("no image"),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(70.dp))
                Column{
                    Text(text = currentUser?.displayName?:"", modifier = Modifier.padding(7.dp), fontSize = 25.sp)
                    Text(text = currentUser?.email?:"", modifier = Modifier.padding(5.dp, 0.dp), fontSize = 15.sp)
                    Log.d("TAG", "${currentUser?.displayName}")
                }
            }

            Row(modifier = Modifier.padding(20.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "Logout Icon",
                    modifier = Modifier.size(25.dp),
                    colorResource(id = R.color.red)
                )
                Text(text = "Log out", color = Color.Red, fontSize = 20.sp, modifier = Modifier.clickable {
                    mGoogleSignInClient.signOut()
                })
            }
        }

    }
}


