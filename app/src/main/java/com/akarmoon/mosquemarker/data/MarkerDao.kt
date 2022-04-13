package com.akarmoon.mosquemarker.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import com.akarmoon.mosquemarker.model.Marker

@Dao
interface MarkerDao {
    @Query("SELECT * FROM marker_databases ORDER BY name")
    fun getMarkers(): Flow<List<Marker>>

    @Query("SELECT * from marker_databases WHERE id = :id")
    fun getMarker(id: Long): Flow<Marker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(marker: Marker)

    @Update
    fun update(marker: Marker)

    @Delete
    fun delete(marker: Marker)
}