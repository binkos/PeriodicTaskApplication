package com.uladzislau.pravalenak.periodictaskapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDao
import com.uladzislau.pravalenak.periodictaskapplication.navigation.OwnHost
import com.uladzislau.pravalenak.periodictaskapplication.ui.theme.PeriodicTaskApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NoteDB.getDatabase(this)

        setContent {
            PeriodicTaskApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    OwnHost(modifier = Modifier, navController = navHostController)
                }
            }
        }
    }
}