package com.example.proyectoofertastrabajo.screens

import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

fun logout(navController: NavHostController) {
    // Cerrar sesión en Firebase
    FirebaseAuth.getInstance().signOut()

    // Navegar a la pantalla de inicio de sesión
    navController.navigate("login_screen") {
        // Limpiar la pila de navegación para que no se pueda volver atrás
        popUpTo(navController.graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
        restoreState = true
    }
}
