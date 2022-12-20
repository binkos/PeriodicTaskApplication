package com.uladzislau.pravalenak.periodictaskapplication.add

sealed class AddNoteEvent {
    class OnTitleUpdate(val text: String) : AddNoteEvent()
    class OnDescriptionUpdate(val text: String) : AddNoteEvent()
    class OnNoteAdded(val title: String, val description: String) : AddNoteEvent()
}
