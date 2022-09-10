package com.virta.nearbystations.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virta.nearbystations.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NearbyStationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby)
    }
}