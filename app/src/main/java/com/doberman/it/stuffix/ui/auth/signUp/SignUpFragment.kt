package com.doberman.it.stuffix.ui.auth.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doberman.it.stuffix.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var dataBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return dataBinding.root
    }
}