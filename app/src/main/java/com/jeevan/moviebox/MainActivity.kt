package com.jeevan.moviebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.jeevan.moviebox.Fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.custom_action_bar)
        toolbar.title =""
        setSupportActionBar(toolbar)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView,MainFragment())
            .commit()
    }
}