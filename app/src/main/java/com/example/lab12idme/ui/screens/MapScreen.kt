package com.example.lab12idme.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab12idme.R // Asegúrate de que esta importación está presente
import androidx.compose.ui.platform.LocalContext

@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4040102, -71.559611) // Ubicación de Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    // Obtén el Bitmap de la imagen
    val bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.phone)

    // Escala el Bitmap (cambia las dimensiones según lo que necesites)
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false) // Ajusta el tamaño aquí

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Usa el Bitmap escalado en el marcador
            Marker(
                state = rememberMarkerState(position = ArequipaLocation),
                icon = BitmapDescriptorFactory.fromBitmap(scaledBitmap), // Usa la imagen escalada
                title = "Arequipa, Perú"
            )
        }
    }
}

