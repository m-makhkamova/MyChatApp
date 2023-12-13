package uz.itschool.mychatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import uz.itschool.mychatapp.R
import uz.itschool.mychatapp.model.UserData
import uz.itschool.mychatapp.ui.theme.MyChatAppTheme

class LogInActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    auth = FirebaseAuth.getInstance()

                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.client_id))
                        .requestEmail()
                        .build()

                    val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFFFFF)),
                        contentAlignment = Alignment.Center
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(50.dp, 20.dp, 0.dp, 0.dp)
                        ) {
                            val poppinsFamily = FontFamily(
                                Font(R.font.poppins_bold, FontWeight.Bold),
                                Font(R.font.poppins_black, FontWeight.Black),
                                Font(R.font.poppins_light, FontWeight.Normal),
                                Font(R.font.poppins_thin, FontWeight.Thin)
                            )

                            Spacer(modifier = Modifier.height(50.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.back_button),
                                contentDescription = "Back icon",
                                modifier = Modifier
                                    .size(22.dp)
                            )
                            Text(
                                text = "Hello, Welcome Back",
                                fontSize = 22.sp,
                                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Happy to see you again, to use your account please login first, using your Google account.",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                                    .width(300.dp),
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal
                            )
                            Image(
                                painter = painterResource(id = R.drawable.signin),
                                contentDescription = "SignIn image",
                                modifier = Modifier
                                    .height(450.dp)
                                    .width(350.dp)
                                    .padding(30.dp, 30.dp, 0.dp, 10.dp)
                            )

                            Button(onClick = {}, modifier = Modifier
                                .width(300.dp)
                                .height(45.dp)
                                .padding(0.dp, 0.dp, 0.dp, 0.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF771F98))) {
                                Text(text = "Sign in with Google", fontSize = 18.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
                Log.d("TAG", "onActivityResult: ")
            } catch (e: ApiException) {
                Log.d("TAG", "error: $e")

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    val userData = UserData(
                        user?.displayName,
                        user?.uid,
                        user?.email,
                        user?.photoUrl.toString()
                    )
                    setUser(userData)

                } else {
                    Log.d("TAG", "error: Authentication Failed.")
                }
            }
    }

    private fun setUser(userData: UserData) {
        val userIdReference = Firebase.database.reference
            .child("users").child(userData.uid?:"")
        userIdReference.setValue(userData).addOnSuccessListener {
            val i = Intent(this, ContactActivity::class.java)
            i.putExtra("uid", userData.uid)
            startActivity(i)
        }
    }

}




