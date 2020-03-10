package com.doberman.it.stuffix.ui.add.location

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doberman.it.stuffix.R

class AddLocationFragment : Fragment() {

    companion object {
        fun newInstance() = AddLocationFragment()
    }

    private lateinit var viewModel: AddLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddLocationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}