package com.doberman.it.stuffix.ui.add.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepository
import kotlinx.coroutines.launch

class AddLocationViewModel(
    private val repository: LocationsListRepository
) : ViewModel() {

    val title = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private var _navigate = false
        set(value) {
            field = value
            (navigate as MutableLiveData).postValue(value)
        }
    val navigate: LiveData<Boolean> = MutableLiveData(_navigate)

    fun onProcessClick() = viewModelScope.launch {
        repository.addLocation(
            LocationsDao.LocationsModel(
                0,
                title.value!!,
                address.value!!,
                description.value!!
            )
        )
        _navigate = true
    }

}