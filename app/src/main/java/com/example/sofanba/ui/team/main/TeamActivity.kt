package com.example.sofanba.ui.team.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.sofanba.databinding.ActivityTeamBinding
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper


class TeamActivity : AppCompatActivity() {
     lateinit var team:Team
     private lateinit var binding: ActivityTeamBinding
     private val teamIconHelper= TeamIconHelper()


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
        binding.title.text=team.fullName
        binding.tabs.setBackgroundColor(resources.getColor(teamIconHelper.getTeamColor(team.abbreviation ?: "LAL")))
        binding.appBarLayout.setBackgroundColor(resources.getColor(teamIconHelper.getTeamColor(team.abbreviation ?: "LAL")))


    }
}