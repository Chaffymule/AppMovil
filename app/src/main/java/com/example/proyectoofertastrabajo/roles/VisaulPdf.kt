package com.example.proyectoofertastrabajo.roles


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualizarSolicitud(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {


    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF3F51B5), Color(0xFF9B71D7)),
        startY = 0f,
        endY = 1000f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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

        // Aquí va el componente para visualizar el PDF
        // Dado que no hay soporte directo para PDF en Jetpack Compose, podemos usar una vista previa simple.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Vista previa del PDF", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Solicitud Aceptada", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Aceptar")
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Solicitud Denegada", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Denegar")
            }
        }
    }
}
