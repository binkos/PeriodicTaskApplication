package com.uladzislau.pravalenak.periodictaskapplication.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal

val <T> ProvidableCompositionLocal<T?>.currentOrThrow: T
    @Composable
    get() = current ?: error("CompositionLocal is null")