package com.aryastefhani0140.miniproject2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aryastefhani0140.miniproject2.model.Tabungan

@Database(entities = [Tabungan::class], version = 1, exportSchema = false)
abstract class TabunganDb : RoomDatabase() {

    abstract val dao: TabunganDao

    companion object {
        @Volatile
        private var INSTANCE: TabunganDb? = null

        fun getInstance(context: Context): TabunganDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TabunganDb::class.java,
                        "tabungan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}