package com.meow.wishlist.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalSoftwareKeyboardController



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.meow.wishlist.Navigation.Screen
import com.meow.wishlist.R
import com.meow.wishlist.UiState
import com.meow.wishlist.ViewModels.AuthViewModel
import com.meow.wishlist.repositories.AuthRepository

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onNavigationToSignUp: () -> Unit,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    // Observe the UI state from the ViewModel
    val uiState by authViewModel.uiState.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.top_bar)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.wishlistlogo),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.padding(8.dp),
            value = name,
            onValueChange = { name = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(R.color.white)
            ),
            placeholder = { Text("Name") },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.padding(8.dp),
            value = email,
            onValueChange = { email = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(R.color.white)
            ),
            placeholder = { Text("Email") },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.padding(8.dp),
            value = password,
            onValueChange = { password = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(R.color.white)
            ),
            placeholder = { Text("Password") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.signUp(name = name, email = email, password = password)
                keyboardController?.hide()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.black),
                contentColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Create Account", color = colorResource(R.color.white))
        }

        Text(
            "You already have an account! Please log in or reset your password if needed.",
            color = androidx.compose.ui.graphics.Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                .clickable { onNavigationToSignUp() }
        )

        when (uiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Success -> { LaunchedEffect(Unit) {
                navController.navigate(Screen.HomeScreen.route)
            } }
            is UiState.Error -> Text(
                text = "Error: ${(uiState as UiState.Error).message}",
                color = MaterialTheme.colorScheme.error
            )
            null -> {} // No state to handle initially
        }
    }
}