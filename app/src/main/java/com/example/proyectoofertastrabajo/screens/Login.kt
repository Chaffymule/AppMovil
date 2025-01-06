package com.example.proyectoofertastrabajo.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import com.example.proyectoofertastrabajo.navigation.NavScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val mAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current  // Obtener el contexto actual

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF9B71D7), Color(0xFF3F51B5)),
        startY = 0f,
        endY = 1000f
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón de Menú en la parte superior izquierda
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de registro",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Campos de texto con diseño mejorado
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico", color = Color.White) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color.White
                    )
                },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Contraseña", color = White) },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón de inicio de sesión con gradiente y sombra
            Button(
                onClick = {
                    isLoading = true
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                navController.navigate(NavScreen.ViewConvocatorias.name)
                            } else {
                                Toast.makeText(context, "Autenticación Fallida", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                ),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                } else {
                    Text("Iniciar Sesión")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(NavScreen.Registro.name)
                },
                modifier = Modifier.fillMaxWidth(0.9f).size(40.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                ),
            ) {
                Text(text = "Registrarme")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¿Olvidó su contraseña?",
                modifier = Modifier.clickable { /* Añade aquí la lógica de recuperación de contraseña */ },
                style = MaterialTheme.typography.bodyMedium.copy( color = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Iniciar sesión con:", color = Color.White )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fb),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { /* Lógica de inicio de sesión con Facebook */ }
                )
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { /* Lógica de inicio de sesión con Google */ }
                )
            }
        }
    }
}
