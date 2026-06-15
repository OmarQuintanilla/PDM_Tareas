package com.example.pdm0126.minipost.navigation

import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

@Serializable
sealed class PostRoutes : NavKey {
    @Serializable
    data object List : PostRoutes()

    @Serializable
    data object Create : PostRoutes()
}