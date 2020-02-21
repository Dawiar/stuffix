package com.doberman.it.stuffix.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doberman.it.stuffix.R
import com.doberman.it.stuffix.ui.home.HomeScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.rootFragmentContainer, HomeScreenFragment.newInstance())
                .commit()
        }
    }
}
