package com.example.proyectoofertastrabajo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.navigation.NavScreen

@Composable
fun StartScreen(navController: NavHostController) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF9B71D7), Color(0xFF3F51B5)),
        startY = 0f,
        endY = 1000f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a la Aplicación",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(NavScreen.ViewConvocatorias.name)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2334B8)),
            shape = CircleShape,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp) // Asegurar que el botón no ocupe demasiado espacio
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Comenzar", color = Color.White, fontSize = 18.sp) // Cambiando el color del texto a blanco
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
            }
        }
    }
}
