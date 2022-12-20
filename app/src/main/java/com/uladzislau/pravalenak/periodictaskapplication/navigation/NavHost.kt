package com.uladzislau.pravalenak.periodictaskapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.uladzislau.pravalenak.periodictaskapplication.add.AddNote
import com.uladzislau.pravalenak.periodictaskapplication.details.DetailsNote
import com.uladzislau.pravalenak.periodictaskapplication.details.NOTE_ID
import com.uladzislau.pravalenak.periodictaskapplication.main.AllNotes

@Composable
fun OwnHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    CompositionLocalProvider(
        LocalNavigator provides rememberNavigator(navController = navController)
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.ALL_NOTES.name,
            modifier = modifier
        ) {
            composable(route = Screen.ALL_NOTES.name) {
                AllNotes()
            }
            composable(route = Screen.ADD_NOTE.name) {
                AddNote()
            }
            composable(
                route = "${Screen.DETAILS_NOTE.name}/{$NOTE_ID}",
                arguments = listOf(navArgument(NOTE_ID) { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt(NOTE_ID, 0) ?: 0
                DetailsNote(noteId)
            }
        }
    }
}

enum class Screen {
    ALL_NOTES,
    ADD_NOTE,
    DETAILS_NOTE
}

@Composable
private fun rememberNavigator(navController: NavController): Navigator {
    val parent = LocalNavigator.current

    return remember(navController, parent) {
        OwnNavigator(navController, parent)
    }
}