package com.example.sofanba.ui.playeractivity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.sofanba.databinding.ActivityPlayerBinding
import com.example.sofanba.model.Player
import com.example.sofanba.ui.playeractivity.ui.main.SectionsPagerAdapter


class PlayerActivity : AppCompatActivity() {
    private lateinit var player:Player
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        player=intent.getSerializableExtra("player") as Player
        binding.title.text=player.firstName +" " +player.lastName


    }
}