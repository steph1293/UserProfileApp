package com.example.UserProfileApp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.UserProfileApp.presentation.viewmodel.UserViewModel
import com.example.UserProfileApp.R


@Composable
fun UserProfileApp() {
    val userViewModel: UserViewModel = hiltViewModel()
    UserProfileScreen(userViewModel)
}

@Composable
fun UserProfileScreen(userViewModel: UserViewModel) {
    val context = LocalContext.current
    val user by userViewModel.user.observeAsState()
    val isLoading by userViewModel.isLoading.observeAsState(false)
    var userIdInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.background_user_profile), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Input Field for User ID
            OutlinedTextField(
                value = userIdInput,
                onValueChange = { userIdInput = it },
                label = { Text("Enter User ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Load User Button
            Button(
                onClick = {
                    if (userIdInput.isNotEmpty()) {
                        val userId = userIdInput.toInt()
                        userViewModel.loadUser(userId)
                    } else {
                        Toast.makeText(context, "Please enter a User ID", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Load User")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress Indicator
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display User Information
            user?.let {
                Text(
                    text = "Name: ${it.name}",
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Email: ${it.email}",
                    style = MaterialTheme.typography.labelSmall
                )
            } ?: run {
                if (!isLoading && userViewModel.user.value == null) {
                    Text(
                        text = "User not found",
                        color = Color.Red,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
