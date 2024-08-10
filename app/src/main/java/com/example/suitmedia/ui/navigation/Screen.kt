package com.example.suitmedia.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Screen {
    @Serializable
    object First : Screen

    @Serializable
    data class Second(
        val name: String?,
        val userName: String?
    ) : Screen

    @Serializable
    data class Third(
        val name: String?
    ) : Screen


}
