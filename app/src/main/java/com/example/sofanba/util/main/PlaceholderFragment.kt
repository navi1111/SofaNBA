package com.example.sofanba.util.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentTeamBinding
import com.example.sofanba.model.Team
import com.example.sofanba.network.paging.GameDiff
import com.example.sofanba.network.paging.PlayerDiff
import com.example.sofanba.network.paging.TeamGamesPagingSource
import com.example.sofanba.recview.GameRecyclerAdapter
import com.example.sofanba.recview.PlayerPagingAdapter
import com.example.sofanba.recview.TeamRecyclerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private val pageViewModel: PageViewModel by activityViewModels()
    private var _binding: FragmentTeamBinding? = null
    private lateinit var gameAdapter:GameRecyclerAdapter
    private var team:Team= Team()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        val root = binding.root
        team=activity?.intent?.getSerializableExtra("team") as Team
        pageViewModel.getTeamId(team.id!!)
        binding.rvGamesforTeam.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        gameAdapter= GameRecyclerAdapter(team, GameDiff)
        binding.rvGamesforTeam.adapter=gameAdapter
        lifecycleScope.launch {
            pageViewModel.flow.collectLatest {

                gameAdapter.submitData(it)

            }
        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}