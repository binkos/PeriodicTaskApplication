package com.uladzislau.pravalenak.periodictaskapplication.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.compose.StableList
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepository
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel
import kotlinx.coroutines.launch

class AllNotesViewModel(
    private val repository: DataRepository = createRepository(NoteDB.getDao()!!)
) : ViewModel(),
    StateDelegate<AllNotesState> by DefaultStateDelegate(AllNotesState()) {

    init {
        viewModelScope.launch {
            repository.getAllFields().collect {
                val models = it.map { entity ->
                    NoteModel(
                        id = entity.id ?: 0,
                        title = entity.title,
                        text = entity.text,
                        date = entity.date.time.toString()
                    )
                }

                updateState { copy(model = StableList(models)) }
            }
        }
    }
}