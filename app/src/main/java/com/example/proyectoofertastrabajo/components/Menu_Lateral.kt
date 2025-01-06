package com.example.proyectoofertastrabajo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.models.MenuLateral
import com.example.proyectoofertastrabajo.navigation.NavScreen
import com.example.proyectoofertastrabajo.navigation.currentRoute
import com.example.proyectoofertastrabajo.screens.logout
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Menu_Lateral(
    navController: NavHostController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Verificamos si el usuario está logueado o no
    val menuItems = listOf(
            MenuLateral.Home,
            MenuLateral.Help,
            MenuLateral.CrearConvocatorias,
            MenuLateral.Logout,// Mostrar "Cerrar sesión"
            MenuLateral.LoginScreen,
            MenuLateral.Registro,
            MenuLateral.Settings
        )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(200.dp) // Reducir el ancho del menú lateral
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 16.dp)
                ) {
                    // Botón para cerrar el menú lateral
                    IconButton(
                        onClick = { scope.launch { drawerState.close() } },
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Cerrar menú",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Menú Principal",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Iteramos sobre los ítems del menú para crear los botones
                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(item.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            },
                            label = { Text(text = item.title, fontWeight = FontWeight.Bold) },
                            selected = currentRoute(navController) == item.route,
                            onClick = {
                                // Si el ítem es Logout, cerramos sesión
                                if (item == MenuLateral.Logout) {
                                    logout(navController = navController)
                                } else {
                                    // Si el ítem no es Logout, navegamos a la ruta correspondiente
                                    scope.launch {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        drawerState.close()
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = MaterialTheme.colorScheme.surface,
                                selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                                selectedTextColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    ) {
        contenido()
    }
}
