package com.doberman.it.stuffix.common.recyclerview

import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListViewModel
import java.lang.ref.WeakReference

class SelectableSubViewModel(val locations: LocationModel, val parent: WeakReference<LocationsListViewModel>) {
    var isSelected: Boolean = false

    fun onLongClick(): Boolean{
        parent
        return true
    }
}
