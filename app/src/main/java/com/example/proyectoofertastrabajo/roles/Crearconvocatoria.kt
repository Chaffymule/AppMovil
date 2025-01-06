package com.example.proyectoofertastrabajo.roles

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearConvocatoria(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {
    var nombreconvocatoria by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var salario by remember { mutableStateOf("") }
    var nombredependencia by remember { mutableStateOf("") }
    var nombrepuesto by remember { mutableStateOf("") }
    var descripcionpuesto by remember { mutableStateOf("") }
    var novacantes by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var tipocontrato by remember { mutableStateOf("") }
    var fechapublicacion by remember { mutableStateOf("") }
    var fecha_finalizacion by remember { mutableStateOf("") }
    var experiencia by remember { mutableStateOf("") }

    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF3F51B5), Color(0xFF9B71D7)),
        startY = 0f,
        endY = 1000f
    )


    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                // Botón para regresar en la parte superior izquierda
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Publicacion de Convocatorias",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombreconvocatoria,
                onValueChange = { nombreconvocatoria = it },
                label = { Text("Nombre", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Número de Teléfono", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = salario,
                onValueChange = { salario = it },
                label = { Text("Salario", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombredependencia,
                onValueChange = { nombredependencia = it },
                label = { Text("Nombre de la Dependencia", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))



            OutlinedTextField(
                value = nombrepuesto,
                onValueChange = { nombrepuesto = it },
                label = { Text("Nombre Puesto", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcionpuesto,
                onValueChange = { descripcionpuesto = it },
                label = { Text("Descripcion Del Puesto", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = novacantes,
                onValueChange = { novacantes = it },
                label = { Text("Numero De Vacantes", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ciudad,
                onValueChange = { ciudad = it },
                label = { Text("Ciudad", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = tipocontrato,
                onValueChange = { tipocontrato = it },
                label = { Text("Tipo De Contrato", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = fechapublicacion,
                onValueChange = { fechapublicacion = it },
                label = { Text("Fecha De Publicacion", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = fecha_finalizacion,
                onValueChange = { fecha_finalizacion = it },
                label = { Text("Fecha De Finalizacion", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = experiencia,
                onValueChange = { experiencia = it },
                label = { Text("Experiencia Laboral", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Aquí puedes agregar la lógica para guardar la configuración del perfil
                    Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {


                Text("Guardar")
            }
        }
    }

}
