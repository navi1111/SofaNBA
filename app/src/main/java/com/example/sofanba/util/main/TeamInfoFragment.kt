package com.example.sofanba.util.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.databinding.FragmentSettingsBinding
import com.example.sofanba.databinding.FragmentTeamInfoBinding
import com.example.sofanba.model.Team
import com.example.sofanba.recview.OnTeamEventListener
import com.example.sofanba.recview.SimpleTeamAdapter
import com.example.sofanba.recview.TeamRecyclerAdapter
import com.example.sofanba.util.TeamActivity
import com.example.sofanba.util.TeamIconHelper


class TeamInfoFragment : Fragment() {
    private lateinit var team: Team
    private var _binding: FragmentTeamInfoBinding? = null
    private val teamIconHelper=TeamIconHelper()
    private val teamInfoViewModel:TeamInfoViewModel by activityViewModels()
    private lateinit var teamAdapter: SimpleTeamAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        team=activity?.intent?.getSerializableExtra("team") as Team
        _binding = FragmentTeamInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.teamInfoPhoto.setImageResource(teamIconHelper.getWeatherIcon(team.abbreviation ?: "LAL"))
        binding.teamInfoName.text=team.name
        binding.tvTeamInfoAbb.text=team.abbreviation
        binding.teamLocation.text=team.city
        binding.tvConference.text=team.conference + "\n Conference"
        binding.tvDivision.text=team.division + "\n Division"
        activity?.actionBar?.title=team.name
        teamInfoViewModel.teamList.observe(viewLifecycleOwner){
            binding.rvConferenceTeams.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            teamAdapter = SimpleTeamAdapter(adapterListener)
            teamAdapter.setTeams(it.toCollection(arrayListOf()))

            binding.rvConferenceTeams.adapter = teamAdapter

        }
        teamInfoViewModel.getTeams(team)
        return root
        // Inflate the layout for this fragment

    }
    private val adapterListener=object : OnTeamEventListener {
        override fun onTeamSelected(team: Team) {
            val intent= Intent(activity, TeamActivity::class.java).apply {
                putExtra("team",team)
            }
            startActivity(intent)
            Toast.makeText(activity,"Item is clicked", Toast.LENGTH_LONG).show()


        }

        override fun onTeamImageButtonSelected(team: Team) {

        }
    }


}