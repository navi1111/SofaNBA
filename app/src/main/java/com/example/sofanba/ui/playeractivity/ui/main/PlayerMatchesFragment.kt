package com.example.sofanba.ui.playeractivity.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentPlayerMatchesBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.network.paging.StatsDiff
import com.example.sofanba.recview.OnGameEventListener
import com.example.sofanba.recview.StatsPagingAdapter
import com.example.sofanba.ui.match.main.MatchActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PlayerMatchesFragment : Fragment() {
    private lateinit var statsAdapter: StatsPagingAdapter
    private var player: Player = Player()
    private val playerMatchesViewModel: PlayerMatchesViewModel by activityViewModels()
    private var _binding: FragmentPlayerMatchesBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPlayerMatchesBinding.inflate(inflater, container, false)
        val root = binding.root
        player=activity?.intent?.getSerializableExtra("player") as Player
        playerMatchesViewModel.getPlayerId(player.id!!)
        binding.rvPlayerGames.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        statsAdapter= StatsPagingAdapter(gameListener,StatsDiff)
        binding.rvPlayerGames.adapter=statsAdapter
        lifecycleScope.launch {
            playerMatchesViewModel.flow.collectLatest {

                statsAdapter.submitData(it)

            }
        }
        return root
    }
    private val gameListener=object : OnGameEventListener {
        override fun onGameSelected(game: Game) {
            val intent= Intent(activity, MatchActivity::class.java).apply {
                putExtra("game",game)
            }
            startActivity(intent)



        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}