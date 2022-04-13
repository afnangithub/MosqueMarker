package com.akarmoon.mosquemarker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import com.akarmoon.mosquemarker.data.MarkerDao
import com.akarmoon.mosquemarker.model.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarkerViewModel(private val markerDao: MarkerDao) : ViewModel() {
    val allMarkers: LiveData<List<Marker>> = markerDao.getMarkers().asLiveData()

    fun getMarker(id: Long): LiveData<Marker> {
        return markerDao.getMarker(id).asLiveData()
    }

    fun addMarker(name: String, address: String, isMarker: Boolean, notes: String) {
        val marker = Marker(name = name, address = address, isMarker = isMarker, notes = notes)
        viewModelScope.launch(Dispatchers.IO) { markerDao.insert(marker) }
    }

    fun updateMarker(id: Long, name: String, address: String, isMarker: Boolean, notes: String) {
        val marker =
            Marker(id = id, name = name, address = address, isMarker = isMarker, notes = notes)
        viewModelScope.launch(Dispatchers.IO) { markerDao.update(marker) }
    }

    fun deleteMarker(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) { markerDao.delete(marker) }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }

    fun getDrawable(marker: Boolean): String {
        return if (marker) "drawable/ic_bookmark_border_24"
        else "drawable/ic_bookmark_24"
    }
}

class MarkerViewModelFactory(private val markerDao: MarkerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarkerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MarkerViewModel(markerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}