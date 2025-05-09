package com.aryastefhani0140.miniproject2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aryastefhani0140.miniproject2.ui.screen.DetailScreen
import com.aryastefhani0140.miniproject2.ui.screen.KEY_ID_TABUNGAN
import com.aryastefhani0140.miniproject2.ui.screen.MainScreen
import com.aryastefhani0140.miniproject2.ui.screen.MainViewModel
import com.aryastefhani0140.miniproject2.ui.screen.RecycleBinScreen
import com.aryastefhani0140.miniproject2.ui.screen.ThemeScreen
import com.aryastefhani0140.miniproject2.util.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val mainViewModel: MainViewModel = viewModel(factory = factory)
    val scope = rememberCoroutineScope()

    val deleteWithUndo: (Long, String) -> Unit = { id, name ->
        mainViewModel.deleteTabungan(id)
        scope.launch {
            val result = mainViewModel.showUndoSnackbar("Tabungan '$name' dihapus")
            if (result) {
                mainViewModel.restoreTabungan(id)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController, mainViewModel)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_TABUNGAN) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_TABUNGAN)
            DetailScreen(
                navController = navController,
                id = id,
                onDeleteWithUndo = deleteWithUndo
            )
        }
        composable(route = Screen.RecycleBin.route) {
            RecycleBinScreen(navController)
        }
        composable(route = Screen.ThemeSettings.route) {
            ThemeScreen(navController)
        }
    }
}