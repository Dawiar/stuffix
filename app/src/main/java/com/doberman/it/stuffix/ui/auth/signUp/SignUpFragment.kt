package com.doberman.it.stuffix.ui.auth.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private val viewModel: SignUpViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SignUpViewModel() as T
        }
    }
    private lateinit var dataBinding: FragmentSignUpBinding
    private val rootNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner


        viewModel.navigationCommands.observe(viewLifecycleOwner, Observer { command ->
            when (command) {
                is NavigationCommand.To ->
                    rootNavController?.navigate(command.directions)
            }
        })
    }
}