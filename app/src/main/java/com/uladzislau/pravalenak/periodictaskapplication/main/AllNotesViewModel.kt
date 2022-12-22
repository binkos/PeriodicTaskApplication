package com.uladzislau.pravalenak.periodictaskapplication.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.periodictaskapplication.core.compose.StableList
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.DefaultStateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.core.mvi.StateDelegate
import com.uladzislau.pravalenak.periodictaskapplication.data.db.NoteDB
import com.uladzislau.pravalenak.periodictaskapplication.data.repository.DataRepositoryImpl.Companion.createRepository
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCase
import com.uladzislau.pravalenak.periodictaskapplication.domain.usecase.DataUseCaseImpl
import com.uladzislau.pravalenak.periodictaskapplication.models.NoteModel
import kotlinx.coroutines.launch

class AllNotesViewModel(
    private val useCase: DataUseCase = DataUseCaseImpl(createRepository(NoteDB.getDao()!!))
) : ViewModel(),
    StateDelegate<AllNotesState> by DefaultStateDelegate(AllNotesState()) {

    init {
        viewModelScope.launch {
            useCase.getNoteModels().collect {
                val models = it.map { domainModel ->
                    NoteModel(
                        id = domainModel.id,
                        title = domainModel.title,
                        text = domainModel.description,
                        date = domainModel.date.time.toString()
                    )
                }

                updateState { copy(model = StableList(models)) }
            }
        }
    }
}