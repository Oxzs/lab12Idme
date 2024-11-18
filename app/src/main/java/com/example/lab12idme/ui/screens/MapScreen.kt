package com.example.lab12idme.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import androidx.compose.ui.platform.LocalContext
import com.example.lab12idme.R

@Composable
fun MapScreen() {
    // Definir las ubicaciones para los marcadores
    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994) // Zamacola
    )

    // Posición de la cámara inicial
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(locations.first(), 12f)
    }

    // Obtener el contexto para usar la imagen
    val context = LocalContext.current

    // Cargar la imagen y redimensionarla
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.phone) // Cargar imagen desde drawable
    val smallBitmap = Bitmap.createScaledBitmap(bitmap, dpToPx(50f, context), dpToPx(50f, context), false) // Ajustar el tamaño

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Iterar sobre las ubicaciones y añadir un marcador para cada una con imagen personalizada
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    icon = BitmapDescriptorFactory.fromBitmap(smallBitmap), // Usar la imagen redimensionada
                    title = "Ubicación",
                    snippet = "Punto de interés"
                )
            }
        }
    }
}

// Función para convertir dp a px
fun dpToPx(dp: Float, context: android.content.Context): Int {
    val density = context.resources.displayMetrics.density
    return (dp * density).toInt()
}


