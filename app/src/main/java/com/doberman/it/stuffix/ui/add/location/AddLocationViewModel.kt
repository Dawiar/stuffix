package com.doberman.it.stuffix.ui.add.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.common.util.SingleHandledEvent
import com.doberman.it.stuffix.common.util.vmNavigation.ExposesNavCommands
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListRepository
import kotlinx.coroutines.launch

class AddLocationViewModel(
    private val repository: LocationsListRepository
) : ViewModel(), ExposesNavCommands {

    val title = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    override val navigationCommands: SingleHandledEvent<NavigationCommand> =
        SingleHandledEvent()

    fun onProcessClick() = viewModelScope.launch {
        repository.addLocation(
            LocationsDao.LocationsModel(
                0,
                title.value!!,
                address.value!!,
                description.value!!
            )
        )
        navigate(AddLocationFragmentDirections.actionAddLocationFragmentToNavigationLocations())
    }



}