package com.example.proyectoofertastrabajo.api

data class ConvocatoriaResponse(
    val datosTablas: DatosTablas
)

data class DatosTablas(
    val comedatos_mapachesdesarrolladores: List<Convocatoria>
)