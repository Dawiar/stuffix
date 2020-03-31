package com.doberman.it.stuffix.ui.home.itemsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.common.items.Item
import com.doberman.it.stuffix.common.util.SingleHandledEvent
import com.doberman.it.stuffix.common.util.vmNavigation.ExposesNavCommands
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import kotlinx.coroutines.launch

class ItemsListViewModel(
    private val repository: ItemsListRepository
) : ViewModel(), ExposesNavCommands {
    private var _items: List<Item>? = null
        set(value) {
            field = value
            (items as MutableLiveData).postValue(value)
        }
    val items: LiveData<List<Item>> = MutableLiveData()

    override val navigationCommands: SingleHandledEvent<NavigationCommand> =
        SingleHandledEvent()

    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)

    init {
        viewModelScope.launch {
            val items: List<Item>? = try {
                repository.getItemsList()
            } catch (t: Throwable) {
                print(t)
                null
            }
            _isLoading = false
            items?.let { _items = it }
        }
    }

    fun onProcessClick() = navigate(ItemsListFragmentDirections.actionNavigationItemsToAddItemFragment())
}