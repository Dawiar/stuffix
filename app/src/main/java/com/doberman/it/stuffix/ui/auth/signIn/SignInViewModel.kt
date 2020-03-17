package com.doberman.it.stuffix.ui.auth.signIn

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch


class SignInViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private var _isLoading = true
        set(value) {
            field = value
            (isLoading as MutableLiveData).postValue(value)
        }
    val isLoading: LiveData<Boolean> = MutableLiveData(_isLoading)

    private var _signInSuccessful = false
        set(value) {
            field = value
            (signInSuccessful as MutableLiveData).postValue(value)
        }
    val signInSuccessful: LiveData<Boolean> = MutableLiveData(_signInSuccessful)

    // TODO add backend interaction here
    fun onSignInSubmit() = viewModelScope.launch {
        _signInSuccessful = true
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
            _signInSuccessful = true
        }
    }
}
