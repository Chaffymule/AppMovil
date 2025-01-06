package com.example.proyectoofertastrabajo.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoofertastrabajo.R
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.proyectoofertastrabajo.components.AreaDropdownMenu
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    var nombres by remember { mutableStateOf(TextFieldValue("")) }
    var apellido1 by remember { mutableStateOf(TextFieldValue("")) }
    var apellido2 by remember { mutableStateOf(TextFieldValue("")) }
    var telefono by remember { mutableStateOf(TextFieldValue("")) }
    var direccion by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var selectedArea by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val mAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF9B71D7), Color(0xFF3F51B5)),
        startY = 0f,
        endY = 1000f
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Tu código existente para el botón de regresar y la imagen
        }
        item {
            Text(text = "Bienvenido", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
        }
        item {
            OutlinedTextField(
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text(text = "Nombres", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = apellido1,
                onValueChange = { apellido1 = it },
                label = { Text(text = "Primer Apellido", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = apellido2,
                onValueChange = { apellido2 = it },
                label = { Text(text = "Segundo Apellido", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text(text = "Teléfono", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text(text = "Dirección", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Correo electrónico", color = Color.White)  },
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(color = Color.White), // Establece el color blanco para el texto
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        // Aquí agregamos el área de selección
//        item {
//            AreaDropdownMenu(
//                selectedArea = selectedArea,
//                onAreaSelected = { area -> selectedArea = area },
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
        item {
            Button(
                onClick = {
                    isLoading = true
                    val emailText = email.text.trim()
                    val passwordText = password.text

                    // Validaciones
                    if (nombres.text.isEmpty() || apellido1.text.isEmpty() || apellido2.text.isEmpty() ||
                        telefono.text.isEmpty() || direccion.text.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()
                    ) {
                        Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                        isLoading = false
                        return@Button
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                        Toast.makeText(context, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                        isLoading = false
                        return@Button
                    }

                    // Crear usuario en Firebase
                    mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                                // Al registrar al usuario, enviar el área seleccionada
                                navController.navigate("view_convocatorias/${selectedArea}")
                            } else {
                                Log.w("RegisterScreen", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                } else {
                    Text("Registrar")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Iniciar sesión con:")
        }
        // Resto de tu código para las opciones de login soci
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fb),
                    contentDescription = "Iniciar sesión con Facebook",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { /* Lógica para Facebook */ }
                )
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Iniciar sesión con Google",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { }
                )
            }
        }
        item {
            TextButton(onClick = { navController.navigate("login") }) {
                Text("¿Ya tienes una cuenta? Inicia sesión aquí")
            }
        }
    }
}