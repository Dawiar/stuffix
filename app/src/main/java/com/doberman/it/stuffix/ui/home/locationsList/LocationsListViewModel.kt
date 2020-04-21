package com.doberman.it.stuffix.ui.home.locationsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.recyclerview.SelectableSubViewModel
import com.doberman.it.stuffix.common.util.SingleHandledEvent
import com.doberman.it.stuffix.common.util.vmNavigation.ExposesNavCommands
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class LocationsListViewModel(
    private val repository: LocationsListRepository
) : ViewModel(), ExposesNavCommands {

    private var _locations: List<LocationModel>? = null
        set(value) {
            field = value
            (locations as MutableLiveData).postValue(value)
        }
    val locations: LiveData<List<LocationModel>> = MutableLiveData()

    override val navigationCommands: SingleHandledEvent<NavigationCommand> =
        SingleHandledEvent()

    val updateHeader: SingleHandledEvent<Boolean> =
        SingleHandledEvent()

    private var _isMultiSelection = false
        set(value) {
            field = value
            (isMultipleSelection as MutableLiveData).postValue(value)
        }
    val isMultipleSelection: LiveData<Boolean> = MutableLiveData(_isMultiSelection)


    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)


    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val locations: List<LocationModel>? = try {
                repository.getLocationsList()
            } catch (t: Throwable) {
                print(t)
                null
            }
            locations.also { _locations = it }
            _isLoading = false
        }
    }

    fun onProcessClick() =
        navigate(LocationsListFragmentDirections.actionNavigationLocationsToAddLocationFragment())

    fun deleteSelectedLocations(IDs: List<Long>) {
        viewModelScope.launch {
            try {
                repository.deleteLocation(IDs)
            } catch (t: Throwable) {
                print(t)
            }
            loadLocations()
        }
    }

    fun enableMultiselection() {
        _isMultiSelection = true
    }

    fun disableMultiselection() {
        _isMultiSelection = false
    }

    fun updateHeader(){
        viewModelScope.launch {
            delay(50)
            updateHeader.value = true
        }
    }


}


