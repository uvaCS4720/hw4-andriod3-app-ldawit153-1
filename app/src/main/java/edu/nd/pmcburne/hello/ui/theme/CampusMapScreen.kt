package edu.nd.pmcburne.hello.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import edu.nd.pmcburne.hello.MainViewModel

@Composable
fun CampusMapScreen(viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column {
        TagDropdown(
            tags = state.allTags,
            selectedTag = state.selectedTag,
            onTagSelected = viewModel::updateSelectedTag
        )

        CampusMap(
            locations = state.filteredLocations,
            modifier = Modifier.weight(1f)
        )
    }
}