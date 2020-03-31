package com.doberman.it.stuffix.ui.home.locationsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.recyclerview.SelectableCell
import com.doberman.it.stuffix.common.recyclerview.SelectableSubViewModel
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

    val isMultipleSelection =

    private var _selectableCells: List<SelectableCell>? = null
        set(value) {
            field = value
            (selectableCells as MutableLiveData).postValue(value)
        }

    val selectableCells: LiveData<List<SelectableCell>> = MutableLiveData()

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
            locations.also { _locations = it }
            _selectableCells = locations?.map { SelectableCell(SelectableSubViewModel(it)) }
            _isLoading = false
        }
    }


}