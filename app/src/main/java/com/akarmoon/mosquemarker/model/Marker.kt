package com.akarmoon.mosquemarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "marker_databases")
data class Marker(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val address: String,
    @ColumnInfo(name = "is_marker") val isMarker: Boolean,
    val notes: String?
)
