package com.uladzislau.pravalenak.periodictaskapplication.screens.add

sealed class AddNoteSideEffect {
    object OnNoteCreated : AddNoteSideEffect()
}