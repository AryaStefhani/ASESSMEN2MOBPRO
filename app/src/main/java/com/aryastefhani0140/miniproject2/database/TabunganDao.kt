package com.aryastefhani0140.miniproject2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aryastefhani0140.miniproject2.model.Tabungan
import kotlinx.coroutines.flow.Flow

@Dao
interface TabunganDao {
    @Insert
    suspend fun insert(tabungan: Tabungan)

    @Update
    suspend fun update(tabungan: Tabungan)

    @Query("SELECT * FROM tabungan ORDER BY tanggal DESC")
    fun getTabungan(): Flow<List<Tabungan>>

    @Query("SELECT * FROM tabungan WHERE id = :id")
    suspend fun getTabunganById(id: Long): Tabungan?

    @Query("DELETE FROM tabungan WHERE id = :id")
    suspend fun deleteById(id: Long)
}