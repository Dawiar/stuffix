package com.doberman.it.stuffix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doberman.it.stuffix.homescreen.HomeScreenFragment

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
