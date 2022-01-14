package com.example.shop.presentation.viewmodel

import android.content.Context
import com.example.shop.R
import com.example.shop.network.NetworkService
import com.example.shop.presentation.fragments.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class CatalogViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    private var job: Job? = null

    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(ScreenState.Loading)
                val products = NetworkService.loadCatalog()
                _screenState.emit(ScreenState.DataLoaded(products))
            } catch (e: Exception) {
                _screenState.emit(ScreenState.Error(context.getString(R.string.error)))
            }
        }
    }
}