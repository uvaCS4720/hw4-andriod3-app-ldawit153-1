package edu.nd.pmcburne.hello.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import edu.nd.pmcburne.hello.LocationEntity

@Composable
fun CampusMap(
    locations: List<LocationEntity>,
    modifier: Modifier = Modifier
) {
    val center = LatLng(38.0356, -78.5034)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 15f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        locations.forEach {
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                title = it.name,
                snippet = it.description
            )
        }
    }
}