package com.doberman.it.stuffix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.doberman.it.stuffix.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
