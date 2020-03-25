package com.doberman.it.stuffix.ui.add.location

object AddLocationViewUtil {

    @JvmStatic
    fun locationAvalible(title: String?, address: String?, description: String?): Boolean {
        return title?.isNotEmpty() == true
                && address?.isNotEmpty() == true
                && description?.isNotEmpty() == true
    }

}