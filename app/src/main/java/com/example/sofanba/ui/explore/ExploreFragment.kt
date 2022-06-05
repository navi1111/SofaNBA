package com.example.sofanba.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.databinding.FragmentExploreBinding

import com.example.sofanba.model.Player
import com.example.sofanba.model.Team
import com.example.sofanba.network.paging.PlayerDiff
import com.example.sofanba.recview.OnPlayerEventListener
import com.example.sofanba.recview.OnTeamEventListener
import com.example.sofanba.recview.PlayerPagingAdapter
import com.example.sofanba.recview.TeamRecyclerAdapter
import com.example.sofanba.ui.playeractivity.PlayerActivity
import com.example.sofanba.ui.team.main.TeamActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExploreFragment : Fragment() {
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    private var _binding: FragmentExploreBinding? = null
    private lateinit var adapterTeam: TeamRecyclerAdapter
    private lateinit var pagingAdapter:PlayerPagingAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        spinnerInit()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun spinnerInit(): String {
        var text:String=String()
        var spinnerAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.playerorteam,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    text = adapterView?.getItemAtPosition(position).toString()
                    if (text=="Players"){
                        binding.rvHome.layoutManager = LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        pagingAdapter= PlayerPagingAdapter(requireContext(),PlayerDiff, playerListener)
                        binding.rvHome.adapter=pagingAdapter
                        lifecycleScope.launch {
                            exploreViewModel.flow.collectLatest {

                                pagingAdapter.submitData(it)

                            }
                        }
                    }
                    if(text=="Teams"){
                        exploreViewModel.teamList.observe(viewLifecycleOwner){
                            binding.rvHome.layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                            adapterTeam = TeamRecyclerAdapter(adapterListener)
                            adapterTeam.setTeams(it)

                            binding.rvHome.adapter = adapterTeam

                        }
                        exploreViewModel.getTeams()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    text="Players"
                }

            }
        return text
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

           if (team.isFavourite){
                exploreViewModel.insertFavouriteTeam(requireContext(),team)

            }else{
                exploreViewModel.deleteFavouriteTeam(requireContext(),team)

            }

        }
    }


    private val playerListener=object : OnPlayerEventListener {
        override fun onPlayerSelected(player: Player) {
            val intent= Intent(activity, PlayerActivity::class.java).apply {
                putExtra("player",player)
            }
            startActivity(intent)



        }

        override fun onImageButtonSelected(player: Player) {

            if (player.isFavourite){
                exploreViewModel.insertFavouritePlayer(requireContext(),player)

            }else{
                exploreViewModel.deleteFavouritePlayer(requireContext(),player)

            }

        }
    }
}