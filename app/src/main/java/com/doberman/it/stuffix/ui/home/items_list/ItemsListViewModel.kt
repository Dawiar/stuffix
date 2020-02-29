package com.doberman.it.stuffix.ui.home.items_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.items.ItemsModel
import kotlinx.coroutines.launch

class ItemsListViewModel(
    private val repository: ItemsListRepository
) : ViewModel() {
    private var _items: List<ItemsModel>?  = null
        set(value) {
            field = value
            (items as MutableLiveData).postValue(value)
        }
    val items: LiveData<List<ItemsModel>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val items: List<ItemsModel>? = try {
                repository.getItemsList()
            } catch (t: Throwable) {
                print(t)
                null
            }
            items?.let { _items = it }
        }
    }


}