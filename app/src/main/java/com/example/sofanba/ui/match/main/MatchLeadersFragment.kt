package com.example.sofanba.ui.match.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentMatchBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Stats
import com.example.sofanba.recview.*


/**
 * A placeholder fragment containing a simple view.
 */
class MatchLeadersFragment : Fragment() {

    private val matchLeadersViewModel: MatchLeadersViewModel by activityViewModels()
    private var _binding: FragmentMatchBinding? = null
    private lateinit var adapterStats: PlayerStatsRecyclerAdapter
    private lateinit var adapter3PMade: Player3PMRecyclerAdapter
    private lateinit var adapterAssists: PlayerAssistsRecyclerAdapter
    private lateinit var adapterRebounds: PlayerReboundsRecyclerAdapter
    private lateinit var adapterTurnovers: PlayerTurnoverRecyclerAdapter
    private var game: Game = Game()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        val root = binding.root
        game=activity?.intent?.getSerializableExtra("game") as Game
        matchLeadersViewModel.stats.observe(viewLifecycleOwner) {
            binding.rvFgbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterStats = PlayerStatsRecyclerAdapter()
            adapterStats.setStats(matchLeadersViewModel.findTopKFGPct(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvFgbest.adapter = adapterStats

            binding.rvFg3ptbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter3PMade= Player3PMRecyclerAdapter()
            adapter3PMade.setStats(matchLeadersViewModel.findTopKThreePct(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvFg3ptbest.adapter = adapter3PMade

            binding.rvReboundbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterRebounds= PlayerReboundsRecyclerAdapter()
            adapterRebounds.setStats(matchLeadersViewModel.findTopKReb(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvReboundbest.adapter = adapterRebounds
            binding.rvAssistbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterAssists= PlayerAssistsRecyclerAdapter()
            adapterAssists.setStats(matchLeadersViewModel.findTopKAst(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvAssistbest.adapter = adapterAssists
            binding.rvTurnoverbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterTurnovers=PlayerTurnoverRecyclerAdapter()
            adapterTurnovers.setStats(matchLeadersViewModel.findTopKTov(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvTurnoverbest.adapter = adapterTurnovers
            binding.rvOffrebbest.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapterRebounds.setStats(matchLeadersViewModel.findTopKOffReb(it.toCollection(arrayListOf()),3) as ArrayList<Stats>)

            binding.rvOffrebbest.adapter = adapterRebounds



        }

        matchLeadersViewModel.getStatsforGame(game)


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}