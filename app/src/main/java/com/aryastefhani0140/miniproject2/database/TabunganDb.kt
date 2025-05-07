package com.aryastefhani0140.miniproject2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aryastefhani0140.miniproject2.model.Tabungan

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE tabungan ADD COLUMN isDeleted INTEGER NOT NULL DEFAULT 0")
    }
}

@Database(entities = [Tabungan::class], version = 2, exportSchema = false)
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
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}