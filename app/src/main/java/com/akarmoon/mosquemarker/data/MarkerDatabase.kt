package com.akarmoon.mosquemarker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akarmoon.mosquemarker.model.Marker

@Database(entities = [Marker::class], version = 1, exportSchema = false)
abstract class MarkerDatabase : RoomDatabase() {
    abstract fun markerDao(): MarkerDao

    companion object {
        @Volatile
        private var INSTANCE: MarkerDatabase? = null
        fun getDatabase(context: Context): MarkerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarkerDatabase::class.java,
                    "marker_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this code lab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}