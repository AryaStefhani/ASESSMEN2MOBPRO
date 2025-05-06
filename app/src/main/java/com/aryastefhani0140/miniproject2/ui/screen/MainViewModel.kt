package com.aryastefhani0140.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryastefhani0140.miniproject2.database.TabunganDao
import com.aryastefhani0140.miniproject2.model.Tabungan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: TabunganDao) : ViewModel() {
    val data: StateFlow<List<Tabungan>> = dao.getTabungan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}