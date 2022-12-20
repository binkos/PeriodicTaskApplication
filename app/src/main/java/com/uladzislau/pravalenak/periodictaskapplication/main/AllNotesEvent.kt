package com.uladzislau.pravalenak.periodictaskapplication.main

sealed class AllNotesEvent {
    object OnAddButtonClicked : AllNotesEvent()
    class OnNoteClicked(val id: Int) : AllNotesEvent()
}