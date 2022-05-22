package com.example.sofanba.ui.home

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
import com.example.sofanba.databinding.FragmentHomeBinding
import com.example.sofanba.model.Team
import com.example.sofanba.network.paging.PlayerDiff
import com.example.sofanba.recview.OnTeamEventListener
import com.example.sofanba.recview.PlayerPagingAdapter
import com.example.sofanba.recview.TeamRecyclerAdapter
import com.example.sofanba.util.TeamActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                        pagingAdapter= PlayerPagingAdapter(requireContext(),PlayerDiff)
                        binding.rvHome.adapter=pagingAdapter
                        lifecycleScope.launch {
                            homeViewModel.flow.collectLatest {

                                pagingAdapter.submitData(it)

                            }
                        }
                    }
                    if(text=="Teams"){
                        homeViewModel.teamList.observe(viewLifecycleOwner){
                            binding.rvHome.layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                            adapterTeam = TeamRecyclerAdapter(adapterListener)
                            adapterTeam.setTeams(it)

                            binding.rvHome.adapter = adapterTeam

                        }
                        homeViewModel.getTeams()
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
                homeViewModel.insertFavouriteTeam(requireContext(),team)

            }else{
                homeViewModel.deleteFavouriteTeam(requireContext(),team)

            }

        }
    }
}