package com.doberman.it.stuffix.ui.auth.signIn

import android.content.Context
import com.doberman.it.stuffix.R

object SignInViewUtil {
    private val emailRegex = Regex("""(?:[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#${'$'}%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+))""")
    private val passwordRegex = Regex("""^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}${'$'}""")

    @JvmStatic
    fun signInAvailable(email: String?, passwordText: String?): Boolean {
        return isEmailValid(email)
                && isPasswordValid(passwordText)
    }

    @JvmStatic
    fun emailError(value: String?, context: Context): String? {
        val isValid = isFieldValueEmpty(value) || isEmailValid(value)
        return if (isValid) {
            null
        } else {
            context.getString(R.string.text_email_invalid_hint)
        }
    }

    @JvmStatic
    fun passwordError(value: String?, context: Context): String? {
        val isValid = isFieldValueEmpty(value) || isPasswordValid(value)
        return if (isValid)
            null
        else
            context.getString(R.string.text_password_invalid_hint)
    }

    @JvmStatic
    private fun isFieldValueEmpty(value: String?): Boolean {
        return value?.isNotBlank() != true
    }

    @JvmStatic
    private fun isEmailValid(value: String?): Boolean {
        return value?.isNotBlank() == true && emailRegex.matches(value)
    }

    @JvmStatic
    private fun isPasswordValid(value: String?): Boolean {
        return value?.isNotBlank() == true && passwordRegex.matches(value)
    }
}