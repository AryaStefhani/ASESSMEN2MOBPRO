package com.aryastefhani0140.miniproject2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabungan")
data class Tabungan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val target: Double,
    val tipe: String,
    val jumlah: Double,
    val tanggal: String
)