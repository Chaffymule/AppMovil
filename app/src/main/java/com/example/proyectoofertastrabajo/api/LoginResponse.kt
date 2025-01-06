package com.example.proyectoofertastrabajo.api

data class LoginResponse(
    val token: String,          // Token de autenticación (por ejemplo, JWT)
    val userId: Int,            // ID único del usuario
    val email: String,          // Correo del usuario
    val nombreUsuario: String,  // Nombre del usuario
    val rol: String             // Rol del usuario (administrador, usuario normal, etc.)
)
