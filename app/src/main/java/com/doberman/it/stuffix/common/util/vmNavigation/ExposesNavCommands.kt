package com.doberman.it.stuffix.common.util.vmNavigation

import androidx.navigation.NavDirections
import com.doberman.it.stuffix.common.util.SingleHandledEvent

interface ExposesNavCommands {
    val navigationCommands: SingleHandledEvent<NavigationCommand>

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }
}
