package com.aryastefhani0140.miniproject2.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aryastefhani0140.miniproject2.database.TabunganDb
import com.aryastefhani0140.miniproject2.ui.screen.DetailViewModel
import com.aryastefhani0140.miniproject2.ui.screen.MainViewModel
import com.aryastefhani0140.miniproject2.ui.screen.RecycleBinViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = TabunganDb.getInstance(context).dao
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(dao) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dao) as T
            }
            modelClass.isAssignableFrom(RecycleBinViewModel::class.java) -> {
                RecycleBinViewModel(dao) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}