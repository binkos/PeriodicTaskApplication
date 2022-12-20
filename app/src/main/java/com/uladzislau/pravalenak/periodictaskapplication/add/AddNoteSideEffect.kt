package com.uladzislau.pravalenak.periodictaskapplication.add

sealed class AddNoteSideEffect {
    object OnNoteCreated : AddNoteSideEffect()
}