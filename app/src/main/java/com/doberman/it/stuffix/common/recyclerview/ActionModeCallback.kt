package com.doberman.it.stuffix.common.recyclerview

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.ui.home.locationsList.LocationsListViewModel

class ActionModeCallback(
    val viewModel: LocationsListViewModel,
    val cells: List<BindableCell<ViewDataBinding>>
) : ActionMode.Callback {


    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_delete) {
            viewModel.deleteSelectedLocations(
                cells
                    .filter { it.viewModel.isSelected.value == true }
                    .map { it.viewModel.location.id })
            mode?.finish()
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.multi_select_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        viewModel.disableMultiselection()
    }
}