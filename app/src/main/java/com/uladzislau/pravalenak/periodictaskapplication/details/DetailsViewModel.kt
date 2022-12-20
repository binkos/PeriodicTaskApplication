package com.uladzislau.pravalenak.periodictaskapplication.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepository
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: DataRepository = createRepository(NoteDB.getDao()!!)
) : ViewModel(),
    StateDelegate<DetailsNoteState> by DefaultStateDelegate(DetailsNoteState()) {

    fun onInit(noteId: Int) {
        viewModelScope.launch {
            val entity = repository.getFieldById(noteId)
            val note =
                NoteModel(entity.id ?: -1, entity.title, entity.text, entity.date.time.toString())
            updateState { copy(note = note) }
        }
    }
}