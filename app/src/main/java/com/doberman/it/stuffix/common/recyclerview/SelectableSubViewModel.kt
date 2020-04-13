package com.doberman.it.stuffix.common.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListViewModel

class SelectableSubViewModel(
    val location: LocationModel,
    val parentViewModel: LocationsListViewModel
) {

    private var _isSelected: Boolean = false
        set(value) {
            field = value
            (isSelected as MutableLiveData).postValue(value)
        }
    val isSelected: LiveData<Boolean> = MutableLiveData(false)

    fun onLongClick(): Boolean {
        if (parentViewModel.isMultipleSelection.value != true) {
            parentViewModel.enableMultiselection()
            invertSelected()
        }
        return true
    }


    fun onClick(): Boolean {
        if (parentViewModel.isMultipleSelection.value == true)
            invertSelected()
        return true
    }

    fun invertSelected() {
        _isSelected = !_isSelected
        parentViewModel.updateHeader()
    }


}
