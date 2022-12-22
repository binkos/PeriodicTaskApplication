package com.uladzislau.pravalenak.periodictaskapplication.screens.main

sealed class AllNotesEvent {
    object OnAddButtonClicked : AllNotesEvent()
    class OnNoteClicked(val id: Int) : AllNotesEvent()
}