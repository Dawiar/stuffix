package com.doberman.it.stuffix.ui.home.locations_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationModel
import kotlinx.coroutines.launch

class LocationsListViewModel(
    private val repository: LocationsListRepository
) : ViewModel() {
    private var _locations: List<LocationModel>? = null
        set(value) {
            field = value
            (locations as MutableLiveData).postValue(value)
        }
    val locations: LiveData<List<LocationModel>> = MutableLiveData()

    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)

    init {
        viewModelScope.launch {
            val locations: List<LocationModel>? = try {
                repository.getLocationsList()
            } catch (t: Throwable) {
                print(t)
                null
            }
            _isLoading = false
            locations?.let { _locations = it }
        }
    }


}