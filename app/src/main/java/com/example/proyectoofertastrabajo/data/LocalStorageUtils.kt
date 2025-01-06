package com.example.proyectoofertastrabajo.data

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.proyectoofertastrabajo.api.Convocatoria

@Composable
fun SaveConvocatoriasTxt(convocatorias: List<Convocatoria>) {
    val context = LocalContext.current
    val fileName = "convocatorias_gamer3.txt"
    Button(onClick = {
        val dataToSave = convocatorias.joinToString("\n") { convocatoria ->
            "Nombre: ${convocatoria.nombre_dependencia}"
        }
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
            output.write(dataToSave.toByteArray())
        }
    }) {
        Text("Guardar en TXT")
    }
}