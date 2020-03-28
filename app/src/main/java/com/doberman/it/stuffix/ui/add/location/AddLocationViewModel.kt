package com.doberman.it.stuffix.ui.add.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.common.util.SingleHandledEvent
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepository
import kotlinx.coroutines.launch

class AddLocationViewModel(
    private val repository: LocationsListRepository
) : ViewModel() {
    val navigation = SingleHandledEvent<(Navigation) -> Unit>()
    val title = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun onProcessClick() = viewModelScope.launch {
        navigation.value = { it.showBlocker() }
        repository.addLocation(
            LocationsDao.LocationsModel(
                0,
                title.value!!,
                address.value!!,
                description.value!!
            )
        )
        navigation.value = {
            it.hideBlocker()
            it.navigateToLocationList()
        }
    }

    interface Navigation {
        fun showBlocker()
        fun hideBlocker()
        fun navigateToLocationList()
    }
}