package com.doberman.it.stuffix.ui.auth.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doberman.it.stuffix.RootNavigationGraphDirections
import com.doberman.it.stuffix.common.util.vmNavigation.ExposesNavCommands
import com.doberman.it.stuffix.common.util.vmNavigation.NavigationCommand
import com.doberman.it.stuffix.common.util.SingleHandledEvent


class SignUpViewModel : ViewModel(), ExposesNavCommands {
    override val navigationCommands: SingleHandledEvent<NavigationCommand> =
        SingleHandledEvent()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)


    // TODO add backend interaction here
    fun onSignUpSubmit() {
        navigate(RootNavigationGraphDirections.actionGlobalNavigationFragmentHomeScreen())
    }

    fun handleNavigateToSignIn() {
        navigate(SignUpFragmentDirections.actionNavigationFragmentSignUpToNavigationFragmentSignIn())
    }
}
