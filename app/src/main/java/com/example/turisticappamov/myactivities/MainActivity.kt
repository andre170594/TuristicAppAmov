
package com.example.turisticappamov.myactivities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.turisticappamov.R
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.ui.theme.TuristicAppAmovTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseDatabase
    private lateinit var activeUser: User
    companion object {
        private const val REQUEST_INTERNET_PERMISSION = 123
    }
    private var createAccount = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goFullScreen()
        // Check if the app has the INTERNET permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), REQUEST_INTERNET_PERMISSION)
        }
        setContent {
            TuristicAppAmovTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if(createAccount.value)
                        CreateAccountTela()
                    else
                        PrimeiraTela()
                }
            }
        }
    }

    // CREATE ACCOUNT
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateAccountTela() {
        val transparency = Color(0x83BFD3BF)
        val startColor = Color(0xFF738073)
        val endColor = Color(0xFF30353A)
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            startColor,
                            endColor,
                            transparency
                        )
                    )
                )
                .padding(24.dp)
            ,verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = painterResource(id= R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .scale(3.0f)
                    .padding(top = 2.dp, bottom = 6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Devs on Tour",
                modifier = Modifier
                    .padding(bottom = 80.dp, top = 20.dp), fontFamily = FontFamily.Monospace
            )
            Text(
                text = "Lets create an account!",
                modifier = Modifier
                    .padding(bottom = 80.dp, top = 20.dp),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
            )
            TextField(
                value = userName,
                onValueChange = { userName = it },
                textStyle = TextStyle(color = Color.LightGray),
                label = { Text(text = "Define nickname", color = Color.DarkGray, fontFamily = FontFamily.Monospace) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = transparency
                ),leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,contentDescription = "create image"
                    )
                }
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = transparency
                ),leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,contentDescription = "password image"
                    )
                },
                textStyle = TextStyle(color = Color.LightGray),
                label = { Text(text = "Define Password", color = Color.DarkGray, fontFamily = FontFamily.Monospace) },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = { createNewUser(userName, password) },
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .clipToBounds(),
                colors = ButtonDefaults.buttonColors(startColor),
                shape = RoundedCornerShape(100.dp),
            ) {
                Text(
                    text = "Create Account",
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp, fontFamily = FontFamily.Monospace
                )
            }
            Button(onClick = {
                createAccount.value = false
            },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(100.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account?",
                    color = startColor,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }

    // CREATE NEW USER
    private fun createNewUser(username: String, password: String) {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseRef = firebaseDatabase.getReference("users")

        // Check if username and password are not empty
        if (username.isNotEmpty() && password.isNotEmpty()) {
            // Query the database to check if the username already exists
            databaseRef.orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Username already exists, show error message
                            Toast.makeText(this@MainActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                        } else {
                            // Username is unique, proceed with user creation

                            // Generate a unique key for the new user
                            val userId = databaseRef.push().key

                            // Check if the userId is not null
                            if (userId != null) {
                                // Create a new user object
                                val newUser = User(userId,username, password)
                                // Insert the new user into the database under the generated userId
                                databaseRef.child(userId).setValue(newUser)
                                    .addOnSuccessListener {
                                        // User creation successful
                                        Toast.makeText(this@MainActivity, "User created successfully", Toast.LENGTH_SHORT).show()
                                        createAccount.value = false
                                    }
                                    .addOnFailureListener {
                                        // User creation failed
                                        Toast.makeText(this@MainActivity, "Failed to create user", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Error occurred while querying the database
                        Toast.makeText(this@MainActivity, "Database error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            // Username or password is empty, show error message
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
        }
    }


    // LOGIN
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PrimeiraTela() {
        val transparency = Color(0x5198959E)
        val startColor = Color(0xFF585069)
        val endColor = Color(0xFF1F1A2B)

        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

      Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(startColor, endColor),
                        start = Offset.Zero,
                        tileMode = TileMode.Repeated
                    )
                )
                .padding(24.dp)
            ,verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.CenterHorizontally

      ) {

          Image(
              painter = painterResource(id= R.drawable.logo),
              contentDescription = "Logo",
              modifier = Modifier
                  .scale(3.0f)
                  .padding(top = 2.dp, bottom = 6.dp)
                  .fillMaxWidth()
          )
          Text(
              text = "Devs on Tour",
              fontFamily = FontFamily.Monospace,
              modifier = Modifier
                  .padding(bottom = 80.dp, top = 20.dp)
          )

          Text(
              text = "LOGIN",
              modifier = Modifier
                  .padding(bottom = 10.dp, top = 20.dp),
              fontSize = 24.sp,
              fontFamily = FontFamily.Monospace
          )
          Text(
              text = "Hi lets jump in!",
              modifier = Modifier
                  .padding(bottom = 80.dp, top = 20.dp),
              fontSize = 20.sp,
              fontFamily = FontFamily.Monospace
          )

          TextField(
              value = userName,
              onValueChange = { userName = it },
              textStyle = TextStyle(color = Color.White),
              label = { Text(text = "Username", color = Color.White, fontFamily = FontFamily.Monospace) },
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(12.dp, 8.dp),
              colors = TextFieldDefaults.textFieldColors(
                  containerColor = transparency
              ), leadingIcon = {
                  // Add your icon here
                  Icon(
                      imageVector = Icons.Default.Person,contentDescription = "login"
                  )
              }
          )
          TextField(
              value = password,
              onValueChange = { password = it },
              modifier = Modifier
                  .fillMaxWidth()
                  .background(Color.Transparent)
                  .padding(12.dp, 8.dp),
              textStyle = TextStyle(color = Color.White),
              label = { Text(text = "Password", color = Color.White, fontFamily = FontFamily.Monospace) },
              visualTransformation = PasswordVisualTransformation(),
              colors = TextFieldDefaults.textFieldColors(
                  containerColor = transparency
              ),
              leadingIcon = {
                  Icon(
                      imageVector = Icons.Default.Lock,contentDescription = "password image"
                  )
              }
          )
          Button(onClick = { loginDo(userName,password) },
              modifier = Modifier
                  .padding(vertical = 10.dp, horizontal = 20.dp)
                  .clipToBounds(),
              colors = ButtonDefaults.buttonColors(transparency),
              shape = RoundedCornerShape(100.dp),
          ) {
              Text(
                  text = "Login",
                  color = Color.LightGray,
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 20.dp),
                  textAlign = TextAlign.Center,
                  fontSize = 14.sp, fontFamily = FontFamily.Monospace
              )
          }
          Button(onClick = {
                    createAccount.value = true
                           },
              colors = ButtonDefaults.buttonColors(Color.Transparent),
              shape = RoundedCornerShape(100.dp),
              modifier = Modifier.fillMaxWidth()
              ) {
              Text(
                  text = "Create new account?",
                  color = transparency,
                  textAlign = TextAlign.Center,
                  fontSize = 14.sp, fontFamily = FontFamily.Monospace,
                  textDecoration = TextDecoration.Underline
              )
          }
      }
    }
    private fun loginDo(userName: String, password: String) {

        if(userName.isNotEmpty() && password.isNotEmpty()){
            auth = FirebaseDatabase.getInstance()
            val databaseRef: DatabaseReference = auth.getReference("/")
            userExists(
                userName,
                password,
                databaseRef
            ) { exists ->
                if (exists) {
                    println("AKBARINO Login successfully")
                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    intent.putExtra("USER",activeUser)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"user not found!",Toast.LENGTH_SHORT).show()
                }
            }
        }else {
            Toast.makeText(this,"Please fill with credentials", Toast.LENGTH_SHORT).show()
        }


    }
    private fun userExists(username: String, password: String, databaseRef: DatabaseReference, callback: (Boolean) -> Unit) {
        databaseRef.child("users").orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var userExists = false
                    for (snapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(User::class.java)

                        if (user != null && user.password == password && user.lastScore != null && user.getAvgScoresList() != null) {
                            userExists = true
                            activeUser = User(user.userID,user.username,user.password,user.lastScore,user.getAvgScoresList())
                            break
                        }else if(user != null && user.password == password){
                            userExists = true
                            activeUser = User(user.username,user.password,null,null)
                            break
                        }
                    }
                    callback(userExists)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    callback(false)
                }
            })
    }

    private fun goFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    @Preview
    @Composable
    fun PrimeiraTelaPreview() {

        val tela = 0

        if(tela == 0)
          PrimeiraTela()
        else
          CreateAccountTela()
    }
}
