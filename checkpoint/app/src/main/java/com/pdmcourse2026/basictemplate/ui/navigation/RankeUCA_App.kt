package com.pdmcourse2026.basictemplate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl
import com.pdmcourse2026.basictemplate.ui.screens.options.OptionsScreen
import com.pdmcourse2026.basictemplate.ui.screens.results.ResultScreenViewModel
import com.pdmcourse2026.basictemplate.ui.screens.results.ResultsScreen
import com.pdmcourse2026.basictemplate.ui.screens.vote.VoteScreen

@Composable
fun RankeUCA_App() {
  val backStack = rememberNavBackStack(Routes.Options)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {

      entry<Routes.Options> {
        OptionsScreen()
      }

      entry<Routes.Vote> {
        VoteScreen(
          navigateToResults = { backStack.add(Routes.Results) }
        )
      }

      entry<Routes.Results> {
        ResultsScreen(
          onNavigateBack = { backStack.removeLastOrNull() },
          viewModel = viewModel(factory = ResultScreenViewModel.Factory)
        )
      }
    },
  )
}