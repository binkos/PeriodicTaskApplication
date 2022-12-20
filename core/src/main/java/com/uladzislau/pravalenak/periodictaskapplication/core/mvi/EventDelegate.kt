package com.uladzislau.pravalenak.periodictaskapplication.core.mvi

import kotlinx.coroutines.flow.SharedFlow

interface EventDelegate<Event> {

    val events: SharedFlow<Event>

    suspend fun sendEvent(event: Event)
}