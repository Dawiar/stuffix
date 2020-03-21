package com.doberman.it.stuffix.ui.add.item

import android.accounts.AuthenticatorDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.items.ItemsDao
import com.doberman.it.stuffix.common.locations.LocationModel
import com.doberman.it.stuffix.common.locations.LocationsDao
import com.doberman.it.stuffix.ui.home.items_list.ItemsListRepository
import com.doberman.it.stuffix.ui.home.locations_list.LocationsListRepository
import kotlinx.coroutines.launch

class AddItemViewModel(
    private val itemsRepository: ItemsListRepository,
    private val locationsRepository: LocationsListRepository
) : ViewModel() {

    private var _locations: List<LocationModel>? = null
        set(value) {
            field = value
            (locations as MutableLiveData).postValue(value)
        }
    val locations: LiveData<List<LocationModel>?> = MutableLiveData()

    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)

    var selectedLocation: LocationModel? = null
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private var _navigate = false
        set(value) {
            field = value
            (navigate as MutableLiveData).postValue(value)
        }
    val navigate: LiveData<Boolean> = MutableLiveData(false)


    init {
        viewModelScope.launch {
            val locations: List<LocationModel>? = try {
                locationsRepository.getLocationsList()
            } catch (t: Throwable) {
                print(t)
                null
            }
            _isLoading = false
            locations?.let { _locations = it }
        }
    }


    fun itemAvailable(title: String?, description: String?): Boolean {
        _locations ?: return false

        return title?.isNotEmpty() == true
                && description?.isNotEmpty() == true
    }


    fun onProcessClick() = viewModelScope.launch {
        itemsRepository.addItem(
            ItemsDao.ItemsModel(
                0,
                title.value!!,
                description.value!!,
                selectedLocation?.id!!,
                selectedLocation?.address!!
            )
        )
        _navigate = true
    }

}