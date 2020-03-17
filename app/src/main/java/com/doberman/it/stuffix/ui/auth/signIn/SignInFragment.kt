package com.doberman.it.stuffix.ui.auth.signIn

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.RootNavigationGraphDirections
import com.doberman.it.stuffix.common.Constants
import com.doberman.it.stuffix.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task


class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SignInViewModel() as T
        }
    }
    private lateinit var dataBinding: FragmentSignInBinding
    private val rootNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity().applicationContext, gso)
        googleSignInClient.signOut()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.signInSuccessful.observe(viewLifecycleOwner, Observer { signInSuccessful ->
            if (signInSuccessful) {
                val action =
                    RootNavigationGraphDirections.actionGlobalNavigationFragmentHomeScreen()
                rootNavController?.navigate(action)
            }
        })
        dataBinding.signInGoogleAuthButton.setSize(SignInButton.SIZE_STANDARD)
        dataBinding.signInGoogleAuthButton.setOnClickListener {
            val signInIntent: Intent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, Constants.ActivityRequestCodes.RC_GOOGLE_SIGN_IN)
        }
    }

    override fun onStart() {
        super.onStart()
        val account: GoogleSignInAccount? =
            GoogleSignIn.getLastSignedInAccount(requireActivity().applicationContext)
        viewModel.handleGoogleAccountExistence(account)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ActivityRequestCodes.RC_GOOGLE_SIGN_IN) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleSignInResult(task)
        }
    }
}