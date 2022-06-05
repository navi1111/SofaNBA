package com.example.sofanba.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sofanba.R
import com.example.sofanba.databinding.ActivityAboutBinding
import com.example.sofanba.databinding.ActivityPlayerBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}