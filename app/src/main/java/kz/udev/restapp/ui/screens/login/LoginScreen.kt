package kz.udev.restapp.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import kz.udev.restapp.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNext: () -> Unit
) {

    val loginState = loginViewModel.loginState.collectAsState().value

    LaunchedEffect(key1 = loginState) {
        if (loginState.user != null) {
//            onNext()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        TextField(
            value = loginViewModel.username.value,
            onValueChange = loginViewModel::onTypeUsername,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = loginViewModel.password.value,
            onValueChange = loginViewModel::onTypePassword,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Button(onClick = {
            if (loginViewModel.username.value.isNotEmpty()) {
                loginViewModel.login()
            }
        }) {
            Text(text = "Click")
        }

        if (loginState.loading) {
            CircularProgressIndicator()
        }

        if (loginState.error != null) {
            Text(text = loginState.error)
        }

        loginState.user?.let {
            Text(text = it.firstName)

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = it.token)

            Spacer(modifier = Modifier.height(5.dp))

            AsyncImage(
                model = it.image,
                contentDescription = null
            )
        }

    }
}