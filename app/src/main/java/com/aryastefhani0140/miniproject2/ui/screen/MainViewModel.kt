package com.aryastefhani0140.miniproject2.ui.screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryastefhani0140.miniproject2.database.TabunganDao
import com.aryastefhani0140.miniproject2.model.Tabungan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val dao: TabunganDao) : ViewModel() {
    val data: StateFlow<List<Tabungan>> = dao.getTabungan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val _snackbarHostState = MutableStateFlow(SnackbarHostState())
    val snackbarHostState: StateFlow<SnackbarHostState> = _snackbarHostState

    fun deleteTabungan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.softDeleteById(id)
        }
    }

    fun restoreTabungan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.restoreById(id)
        }
    }

    suspend fun showUndoSnackbar(message: String): Boolean {
        val result = withContext(Dispatchers.Main) {
            _snackbarHostState.value.showSnackbar(
                message = message,
                actionLabel = "Undo",
                duration = SnackbarDuration.Short
            )
        }
        return result == SnackbarResult.ActionPerformed
    }
}