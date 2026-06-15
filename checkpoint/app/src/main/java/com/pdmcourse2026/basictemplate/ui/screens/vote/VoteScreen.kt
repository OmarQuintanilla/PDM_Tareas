package com.pdmcourse2026.basictemplate.ui.screens.vote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl
import com.pdmcourse2026.basictemplate.ui.components.PlaceCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoteScreen(
  navigateToResults: () -> Unit,
  viewModel: VoteViewModel = viewModel(factory = VoteViewModel.Factory)

) {
  // Consumimos el StateFlow de forma segura
  val uiState by viewModel.uiState.collectAsState()

  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text("RankeUCA - Vota") },
      )
    },
    bottomBar = {
      // El botón solo se habilita si isVoteSuccessful es true
      Button(
        onClick = navigateToResults,
        enabled = uiState.isVoteSuccessful,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Text("Ir a resultados ->")
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ) {

      // 1. Manejo de Loading
      if (uiState.isLoading) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      }

      // 2. Manejo de la lista con LazyColumn
      if (uiState.places.isNotEmpty()) {
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
          contentPadding = PaddingValues(vertical = 16.dp)
        ) {
          items(uiState.places) { place ->
            PlaceCard(
              place = place,
              isSelected = uiState.votedPlaceId == place.id,
              isEnabled = !uiState.isVoteSuccessful,
              onVoteClick = { viewModel.voteForPlace(place.id) }
            )
          }
        }
      }

      // 3. Manejo de Errores
      uiState.errorMessage?.let { error ->
        Text(
          text = error,
          color = MaterialTheme.colorScheme.error,
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp)
        )
      }
    }
  }
}