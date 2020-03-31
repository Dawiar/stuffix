package com.doberman.it.stuffix.ui.auth.signIn

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doberman.it.stuffix.RootNavigationGraphDirections
import com.doberman.it.stuffix.common.util.vmNavigation.ExposesNavCommands
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.common.util.SingleHandledEvent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch


class SignInViewModel : ViewModel(), ExposesNavCommands {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    override val navigationCommands: SingleHandledEvent<NavigationCommand> =
        SingleHandledEvent()

    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)

    // TODO add backend interaction here
    fun onSignInSubmit() = viewModelScope.launch {
        navigate(RootNavigationGraphDirections.actionGlobalNavigationFragmentHomeScreen())
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)

            handleGoogleAccountExistence(account)
        } catch (e: ApiException) {

            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            handleGoogleAccountExistence(null)
        }
    }

    fun handleGoogleAccountExistence(account: GoogleSignInAccount?) {
        if (account != null) {
            navigate(RootNavigationGraphDirections.actionGlobalNavigationFragmentHomeScreen())
        }
    }

    fun handleNavigateToSignIn() {
        navigate(SignInFragmentDirections.actionNavigationFragmentSignInToNavigationFragmentSignUp())
    }

}
