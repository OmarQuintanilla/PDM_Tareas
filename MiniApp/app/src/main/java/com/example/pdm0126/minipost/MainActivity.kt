package com.example.pdm0126.minipost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.pdm0126.minipost.navigation.PostRoutes
import com.example.pdm0126.minipost.ui.screens.CreatePostScreen
import com.example.pdm0126.minipost.ui.screens.PostListScreen
import com.example.pdm0126.minipost.ui.theme.MiniPostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniPostTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val backStack = rememberNavBackStack(PostRoutes.List)

                    NavDisplay(
                        backStack = backStack,
                        entryProvider = entryProvider {
                            entry<PostRoutes.List> {
                                PostListScreen(
                                    navigateToCreate = {
                                        backStack.add(PostRoutes.Create)
                                    }
                                )
                            }
                            entry<PostRoutes.Create> {
                                CreatePostScreen(
                                    onBack = {
                                        backStack.removeLastOrNull()
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}