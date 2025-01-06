package com.example.proyectoofertastrabajo.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import com.example.proyectoofertastrabajo.api.AuthRequest
import com.example.proyectoofertastrabajo.api.Convocatoria
import com.example.proyectoofertastrabajo.api.RetrofitInstance
import com.example.proyectoofertastrabajo.components.DropdownMenuFilter
import com.example.proyectoofertastrabajo.components.NotificationIconButton
import com.example.proyectoofertastrabajo.navigation.NavScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewConvocatorias(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {
    var searchText by remember { mutableStateOf("") }
    var convocatorias by remember { mutableStateOf<List<Convocatoria>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF3F51B5), Color(0xFF9B71D7)),
        startY = 0f,
        endY = 1000f
    )

    LaunchedEffect(Unit) {
        fetchConvocatorias(
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiZjk4MTRlNGQ1Y2FhNWZhZTVlMmQ3Y2ZmMWY5ZWVmZjJhZDIzMTNmMjlkZDY0NjYwNDZkMTA2Y2FhZGNiMzMxNDgwNjBmY2EwNTI4MDRlMDAiLCJpYXQiOjE3MzIyMjEyODAuOTM1ODQ3LCJuYmYiOjE3MzIyMjEyODAuOTM1ODU0LCJleHAiOjE3NjM3NTcyODAuNjUwOTE1LCJzdWIiOiI0MCIsInNjb3BlcyI6W119.YEDxL1Mh0h8XSol7ps4_CruCrqZkide9VPmvkauWvY1pgb8IV-25PvXXG8FRtYWlyCqQCBSam2IG9jfsVLMZwk2GLzb2_7o8nkyldRwg490UoEDuRJ5yoxR_SQngZsCriRqhsd10nPKcIr0HRCdKdTtSZyUGE7wTdpZl81GlLPBqmTjwol4SHnZFJT1brntNY6DzgNtZzR4M2DxVx0wlc6SpKGh1TRjVla-WMqqqVFmgoLdqccytgtMp85TXcUd7v6eLbIhvfYuZkL5gQe3BclrPQg-f1-L02gzTxEzgr04AOtDZdP9-EH2YswphhOd0eWxZeT9lSdL_-gy4tMjfkJpbXwIcWSJpM_whnOSPEP7H03bG10za_VhfJPoLYxUNemv4etuv6wFeH1SKiuR--rgU3tYpWTXPEoppyY6J03n0_HENRYdOXgOnj15B8txqH-Vq9VCjFeiVmG5eOlqsVPbKXo3z_2aMoMiimFaH0T-iJ5dF5GfGlbzj997k5uiyM6uQKosP08BsWqqwwVbHZHcy57mCozivFG-7G4GZL2543yHEgCwxfN4-s8MshQK5sVun_VuFpUrdEeyLNUkO7rW9K1PpABQ6TKKGTYeMzrJauK75RGVAoIzj87EW1o2kfITr2A9kRlQ4he4-gDKylE1ayC-zNf4gzMWok1xFXX8",
            onSuccess = { convocatoriasList ->
                Log.d("API_SUCCESS", "Convocatorias obtenidas: ${convocatoriasList.size}")
                convocatorias = convocatoriasList
            },
            onError = { error ->
                Log.e("API_ERROR", error)
                errorMessage = error
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownMenuFilter(convocatorias, onCitySelected = { selectedCity ->
                searchText = selectedCity
            })

            NotificationIconButton {
                navController.navigate(NavScreen.Notification.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        } else {
            val filteredConvocatorias = convocatorias.filter {
                it.ciudad?.contains(searchText, ignoreCase = true) == true
            }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(filteredConvocatorias.size) { index ->
                    val convocatoria = filteredConvocatorias[index]
                    val imageRes = when (convocatoria.nombre_dependencia) {
                        "Dependencias Secretaría de Educación" -> R.drawable.i1
                        "Dependencias de Salud" -> R.drawable.logo
                        "Dependencias de Seguridad Pública" -> R.drawable.i1
                        "Dependencias de Desarrollo Urbano" -> R.drawable.logo
                        else -> R.drawable.logo // Imagen predeterminada
                    }

                    MenuCard(
                        companyName = convocatoria.nombre_dependencia ?: "N/A",
                        salary = convocatoria.salario ?: "N/A",
                        position = convocatoria.nombre_puesto ?: "N/A",
                        city = convocatoria.ciudad ?: "N/A",
                        vacancies = convocatoria.no_vacantes ?: 0,
                        imageRes = imageRes,
                        onClick = {
                            val encodedCompanyName = URLEncoder.encode(convocatoria.nombre_dependencia, StandardCharsets.UTF_8.toString())
                            val encodedSalary = URLEncoder.encode(convocatoria.salario, StandardCharsets.UTF_8.toString())
                            val encodedPosition = URLEncoder.encode(convocatoria.nombre_puesto, StandardCharsets.UTF_8.toString())
                            val encodedEducationRequired = URLEncoder.encode(convocatoria.experiencia, StandardCharsets.UTF_8.toString())
                            val encodedPhone = URLEncoder.encode(convocatoria.telefono_dependencia, StandardCharsets.UTF_8.toString())
                            val encodedConvocatoriaName = URLEncoder.encode(convocatoria.nombre_convocatoria, StandardCharsets.UTF_8.toString())
                            val encodedJobDescription = URLEncoder.encode(convocatoria.descripcion_puesto, StandardCharsets.UTF_8.toString())
                            val encodedCity = URLEncoder.encode(convocatoria.ciudad, StandardCharsets.UTF_8.toString())
                            val encodedAdminUnit = URLEncoder.encode(convocatoria.unidad_admin_vacante, StandardCharsets.UTF_8.toString())
                            val encodedContractType = URLEncoder.encode(convocatoria.tipo_contrato, StandardCharsets.UTF_8.toString())
                            val encodedPublicationDate = URLEncoder.encode(convocatoria.fecha_publicacion, StandardCharsets.UTF_8.toString())
                            val encodedEndDate = URLEncoder.encode(convocatoria.fecha_finalizacion, StandardCharsets.UTF_8.toString())

                            navController.navigate(
                                "page_content/$encodedCompanyName/$encodedSalary/$encodedPosition/$encodedEducationRequired/${convocatoria.no_vacantes}/$encodedPhone/$encodedConvocatoriaName/$encodedJobDescription/$encodedCity/$encodedAdminUnit/$encodedContractType/$encodedPublicationDate/$encodedEndDate"
                            )
                        }
                    )
                }
            }

            // Botón para guardar las convocatorias en un archivo de texto
            Button(onClick = {
                saveConvocatoriasToTxt(context, convocatorias)
            }) {
                Text("Guardar en TXT")
            }
        }
    }
}

@Composable
fun MenuCard(
    companyName: String,
    salary: String,
    position: String,
    city: String,
    vacancies: Int,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(16.dp)),  // Agregar sombra a la tarjeta
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface  // Usar fondo claro
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageRes), // Asigna la imagen correspondiente
                contentDescription = "Ícono de Empresa",
                modifier = Modifier
                    .size(64.dp)
                    .background(MaterialTheme.colorScheme.surface, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)  // Asegura que la columna ocupe el espacio disponible
            ) {
                Text(
                    text = companyName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFF184059),
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = position,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = "Sueldo: $salary",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Ciudad: $city",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Vacantes: $vacancies",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}
fun saveConvocatoriasToTxt(context: Context, convocatorias: List<Convocatoria>) {
    val fileName = "convocatorias_gamer3.txt"
    val dataToSave = convocatorias.joinToString("\n") { convocatoria ->
        "Nombre: ${convocatoria.nombre_dependencia}"
    }
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
        output.write(dataToSave.toByteArray())
    }
    Toast.makeText(context, "Datos guardados en $fileName", Toast.LENGTH_SHORT).show()
}

private fun fetchConvocatorias(authToken: String, onSuccess: (List<Convocatoria>) -> Unit, onError: (String) -> Unit) {
    val retrofit = RetrofitInstance.api
    val authRequest = AuthRequest(email = "l20390283@chetumal.tecnm.mx", password = "l20390283@chetumal.tecnm.mx")

    CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
        try {
            val response = retrofit.getConvocatorias(authToken, authRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("API_RESPONSE", "Respuesta de la API: $it")
                    onSuccess(it.datosTablas.comedatos_mapachesdesarrolladores ?: emptyList())
                } ?: onError("Error: Lista vacía")
            } else {
                Log.e("API_ERROR", "Error en la respuesta: ${response.code()}")
                onError("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Excepción: ${e.message}")
            onError(e.message ?: "Error desconocido")
        }
    }
}