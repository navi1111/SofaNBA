package com.example.sofanba.util

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.sofanba.databinding.ActivityTeamBinding
import com.example.sofanba.model.Team
import com.example.sofanba.util.main.SectionsPagerAdapter


class TeamActivity : AppCompatActivity() {
    private var team= Team()
    private lateinit var binding: ActivityTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        team=intent.getSerializableExtra("team") as Team


    }
}