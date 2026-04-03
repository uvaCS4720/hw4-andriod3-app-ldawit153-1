package edu.nd.pmcburne.hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.nd.pmcburne.hello.ui.CampusMapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = LocationRepository(
            PlacemarkApiService.create(),
            database.locationDao()
        )

        setContent {
            val vm: MainViewModel = viewModel(
                factory = MainViewModelFactory(repository)
            )
            CampusMapScreen(vm)
        }
    }
}