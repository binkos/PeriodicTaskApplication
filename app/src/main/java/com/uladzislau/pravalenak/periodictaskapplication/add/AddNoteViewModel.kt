package com.uladzislau.pravalenak.periodictaskapplication.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultEventDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.EventDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import com.uladzislau.pravalenak.periodictaskapplication.domain.model.NewNoteModel
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCase
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCaseImpl
import kotlinx.coroutines.launch
import java.util.*

class AddNoteViewModel(
    private val repository: DataUseCase = DataUseCaseImpl(createRepository(NoteDB.getDao()!!))
) : ViewModel(),
    StateDelegate<AddNoteState> by DefaultStateDelegate(AddNoteState()),
    EventDelegate<AddNoteSideEffect> by DefaultEventDelegate() {

    fun handleEvent(event: AddNoteEvent) {
        when (event) {
            is AddNoteEvent.OnDescriptionUpdate ->
                updateState {
                    copy(
                        title = state.title,
                        description = event.text,
                    )
                }
            is AddNoteEvent.OnNoteAdded -> {
                viewModelScope.launch {
                    val model = NewNoteModel(event.title, event.description, Date())
                    repository.addNote(model)
                    sendEvent(AddNoteSideEffect.OnNoteCreated)
                }
            }
            is AddNoteEvent.OnTitleUpdate ->
                updateState {
                    copy(
                        title = event.text,
                        description = state.description,
                    )
                }
        }
    }
}