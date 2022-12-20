package com.uladzislau.pravalenak.periodictaskapplication.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultEventDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.EventDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.db.entity.NoteEntity
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepository
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import kotlinx.coroutines.launch
import java.util.*

class AddNoteViewModel(
    private val repository: DataRepository = createRepository(NoteDB.getDao()!!)
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
                    val entity = NoteEntity(null, event.title, event.description, Date())
                    repository.addField(entity)
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