package com.example.proyectoofertastrabajo.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaDropdownMenu(selectedArea: String?, onAreaSelected: (String) -> Unit) {
    val areas = listOf("Servicios", "Informática", "Ventas", "Marketing") // Áreas predefinidas
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopStart)
    ) {
        Button(onClick = { expanded = true }) {
            Row {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown, // O cualquier ícono relacionado
                    contentDescription = "Seleccionar área"
                )
                Text(
                    text = selectedArea ?: "Seleccionar área",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            areas.forEach { area ->
                DropdownMenuItem(
                    text = { Text(text = area) },
                    onClick = {
                        onAreaSelected(area)
                        expanded = false
                    }
                )
            }
        }
    }
}

