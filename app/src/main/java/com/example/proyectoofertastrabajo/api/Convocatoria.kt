package com.example.proyectoofertastrabajo.api

data class Convocatoria(
    val id: String? = "",
    val nombre_dependencia: String? = "",
    val telefono_dependencia: String? = "",
    val nombre_convocatoria: String? = "",
    val nombre_puesto: String? = "",
    val descripcion_puesto: String? = "",
    val no_vacantes: Int? = 0,
    val salario: String? = "",
    val ciudad: String? = "",
    val unidad_admin_vacante: String? = "",
    val tipo_contrato: String? = "",
    val fecha_publicacion: String? = "",
    val fecha_finalizacion: String? = "",
    val experiencia: String? = ""
)