package com.uladzislau.pravalenak.periodictaskapplication.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCase
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCaseImpl
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val useCase: DataUseCase = DataUseCaseImpl(createRepository(NoteDB.getDao()!!))
) : ViewModel(),
    StateDelegate<DetailsNoteState> by DefaultStateDelegate(DetailsNoteState()) {

    fun onInit(noteId: Int) {
        viewModelScope.launch {
            val model = useCase.getNoteById(noteId)
            val note =
                NoteModel(
                    model.id,
                    model.title,
                    model.description,
                    model.date.time.toString()
                )
            updateState { copy(note = note) }
        }
    }
}