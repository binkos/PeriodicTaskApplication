package com.uladzislau.pravalenak.periodictaskapplication.core.compose

import androidx.compose.runtime.Stable

@Stable
data class StableList<T>(private val delegate: List<T>) : List<T> by delegate