package com.aryastefhani0140.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryastefhani0140.miniproject2.database.TabunganDao
import com.aryastefhani0140.miniproject2.model.Tabungan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: TabunganDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, target: Double, tipe: String, jumlah: Double) {
        val tabungan = Tabungan(
            tanggal = formatter.format(Date()),
            nama = nama,
            target = target,
            tipe = tipe,
            jumlah = jumlah,
            isDeleted = false
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(tabungan)
        }
    }

    suspend fun getTabungan(id: Long): Tabungan? {
        return withContext(Dispatchers.IO) {
            dao.getTabunganById(id)
        }
    }

    fun update(id: Long, nama: String, target: Double, tipe: String, jumlah: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val current = dao.getTabunganById(id)
            if (current != null) {
                val tabungan = Tabungan(
                    id = id,
                    tanggal = formatter.format(Date()),
                    nama = nama,
                    target = target,
                    tipe = tipe,
                    jumlah = jumlah,
                    isDeleted = current.isDeleted
                )
                dao.update(tabungan)
            }
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.softDeleteById(id)
        }
    }


    }
