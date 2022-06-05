package com.example.sofanba.ui.playeractivity.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sofanba.R
import com.example.sofanba.databinding.FragmentPlayerStatisticsBinding
import com.example.sofanba.model.Player
import com.example.sofanba.model.SeasonAverages

import com.example.sofanba.util.TeamIconHelper

class PlayerStatisticsFragment : Fragment() {
    private val playerStatisticsViewModel:PlayerStatisticsViewModel by activityViewModels()
    private var _binding: FragmentPlayerStatisticsBinding?=null
    private var player:Player= Player()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentPlayerStatisticsBinding.inflate(inflater,container,false)
        val root=binding.root
        player=activity?.intent?.getSerializableExtra("player") as Player
        playerStatisticsViewModel.seasonAverages2021.observe(viewLifecycleOwner){
            if(it== emptyList<SeasonAverages>()){
                binding.layout2021.min.text="-"
                binding.layout2021.fgm.text="-"
                binding.layout2021.fga.text="-"
                binding.layout2021.fg3m.text="-"
                binding.layout2021.fg3a.text="-"
                binding.layout2021.ftm.text="-"
                binding.layout2021.fta.text="-"
                binding.layout2021.oreb.text="-"
                binding.layout2021.dreb.text="-"
                binding.layout2021.reb.text="-"
                binding.layout2021.ast.text="-"
                binding.layout2021.stl.text="-"
                binding.layout2021.blk.text="-"
                binding.layout2021.tov.text="-"
                binding.layout2021.pf.text="-"
                binding.layout2021.pts.text="-"
                binding.layout2021.fgpct.text="-"
                binding.layout2021.fg3pct.text="-"
                binding.layout2021.ftpct.text="-"
            }else{
                binding.layout2021.min.text=it[0].min?:"-"
                binding.layout2021.fgm.text=it[0].fgm.toString()?:"-"
                binding.layout2021.fga.text=it[0].fga.toString()?:"-"
                binding.layout2021.fg3m.text=it[0].fg3m.toString()?:"-"
                binding.layout2021.fg3a.text=it[0].fg3a.toString()?:"-"
                binding.layout2021.ftm.text=it[0].ftm.toString()?:"-"
                binding.layout2021.fta.text=it[0].fta.toString()?:"-"
                binding.layout2021.oreb.text=it[0].oreb.toString()?:"-"
                binding.layout2021.dreb.text=it[0].dreb.toString()?:"-"
                binding.layout2021.reb.text=it[0].reb.toString()?:"-"
                binding.layout2021.ast.text=it[0].ast.toString()?:"-"
                binding.layout2021.stl.text=it[0].stl.toString()?:"-"
                binding.layout2021.blk.text=it[0].blk.toString()?:"-"
                binding.layout2021.tov.text=it[0].turnover.toString()?:"-"
                binding.layout2021.pf.text=it[0].pf.toString()?:"-"
                binding.layout2021.pts.text=it[0].pts.toString()?:"-"
                binding.layout2021.fgpct.text=it[0].fgPct.toString()?:"-"
                binding.layout2021.fg3pct.text=it[0].fg3Pct.toString()?:"-"
                binding.layout2021.ftpct.text=it[0].ftPct.toString()?:"-"
            }

        }
        playerStatisticsViewModel.seasonAverages2020.observe(viewLifecycleOwner){
            if (it== emptyList<SeasonAverages>()){
                binding.layout2020.min.text="-"
                binding.layout2020.fgm.text="-"
                binding.layout2020.fga.text="-"
                binding.layout2020.fg3m.text="-"
                binding.layout2020.fg3a.text="-"
                binding.layout2020.ftm.text="-"
                binding.layout2020.fta.text="-"
                binding.layout2020.oreb.text="-"
                binding.layout2020.dreb.text="-"
                binding.layout2020.reb.text="-"
                binding.layout2020.ast.text="-"
                binding.layout2020.stl.text="-"
                binding.layout2020.blk.text="-"
                binding.layout2020.tov.text="-"
                binding.layout2020.pf.text="-"
                binding.layout2020.pts.text="-"
                binding.layout2020.fgpct.text="-"
                binding.layout2020.fg3pct.text="-"
                binding.layout2020.ftpct.text="-"
            }else {


                binding.layout2020.min.text = it[0].min ?: "-"
                binding.layout2020.fgm.text = it[0].fgm.toString() ?: "-"
                binding.layout2020.fga.text = it[0].fga.toString() ?: "-"
                binding.layout2020.fg3m.text = it[0].fg3m.toString() ?: "-"
                binding.layout2020.fg3a.text = it[0].fg3a.toString() ?: "-"
                binding.layout2020.ftm.text = it[0].ftm.toString() ?: "-"
                binding.layout2020.fta.text = it[0].fta.toString() ?: "-"
                binding.layout2020.oreb.text = it[0].oreb.toString() ?: "-"
                binding.layout2020.dreb.text = it[0].dreb.toString() ?: "-"
                binding.layout2020.reb.text = it[0].reb.toString() ?: "-"
                binding.layout2020.ast.text = it[0].ast.toString() ?: "-"
                binding.layout2020.stl.text = it[0].stl.toString() ?: "-"
                binding.layout2020.blk.text = it[0].blk.toString() ?: "-"
                binding.layout2020.tov.text = it[0].turnover.toString() ?: "-"
                binding.layout2020.pf.text = it[0].pf.toString() ?: "-"
                binding.layout2020.pts.text = it[0].pts.toString() ?: "-"
                binding.layout2020.fgpct.text = it[0].fgPct.toString() ?: "-"
                binding.layout2020.fg3pct.text = it[0].fg3Pct.toString() ?: "-"
                binding.layout2020.ftpct.text = it[0].ftPct.toString() ?: "-"
            }
        }
        playerStatisticsViewModel.seasonAverages2019.observe(viewLifecycleOwner){
            if (it== emptyList<SeasonAverages>()){
                binding.layout2019.min.text="-"
                binding.layout2019.fgm.text="-"
                binding.layout2019.fga.text="-"
                binding.layout2019.fg3m.text="-"
                binding.layout2019.fg3a.text="-"
                binding.layout2019.ftm.text="-"
                binding.layout2019.fta.text="-"
                binding.layout2019.oreb.text="-"
                binding.layout2019.dreb.text="-"
                binding.layout2019.reb.text="-"
                binding.layout2019.ast.text="-"
                binding.layout2019.stl.text="-"
                binding.layout2019.blk.text="-"
                binding.layout2019.tov.text="-"
                binding.layout2019.pf.text="-"
                binding.layout2019.pts.text="-"
                binding.layout2019.fgpct.text="-"
                binding.layout2019.fg3pct.text="-"
                binding.layout2019.ftpct.text="-"
            }else {


                binding.layout2019.min.text = it[0].min ?: "-"
                binding.layout2019.fgm.text = it[0].fgm.toString() ?: "-"
                binding.layout2019.fga.text = it[0].fga.toString() ?: "-"
                binding.layout2019.fg3m.text = it[0].fg3m.toString() ?: "-"
                binding.layout2019.fg3a.text = it[0].fg3a.toString() ?: "-"
                binding.layout2019.ftm.text = it[0].ftm.toString() ?: "-"
                binding.layout2019.fta.text = it[0].fta.toString() ?: "-"
                binding.layout2019.oreb.text = it[0].oreb.toString() ?: "-"
                binding.layout2019.dreb.text = it[0].dreb.toString() ?: "-"
                binding.layout2019.reb.text = it[0].reb.toString() ?: "-"
                binding.layout2019.ast.text = it[0].ast.toString() ?: "-"
                binding.layout2019.stl.text = it[0].stl.toString() ?: "-"
                binding.layout2019.blk.text = it[0].blk.toString() ?: "-"
                binding.layout2019.tov.text = it[0].turnover.toString() ?: "-"
                binding.layout2019.pf.text = it[0].pf.toString() ?: "-"
                binding.layout2019.pts.text = it[0].pts.toString() ?: "-"
                binding.layout2019.fgpct.text = it[0].fgPct.toString() ?: "-"
                binding.layout2019.fg3pct.text = it[0].fg3Pct.toString() ?: "-"
                binding.layout2019.ftpct.text = it[0].ftPct.toString() ?: "-"
            }
        }
        playerStatisticsViewModel.seasonAverages2018.observe(viewLifecycleOwner){
            if (it== emptyList<SeasonAverages>()){
                binding.layout2018.min.text="-"
                binding.layout2018.fgm.text="-"
                binding.layout2018.fga.text="-"
                binding.layout2018.fg3m.text="-"
                binding.layout2018.fg3a.text="-"
                binding.layout2018.ftm.text="-"
                binding.layout2018.fta.text="-"
                binding.layout2018.oreb.text="-"
                binding.layout2018.dreb.text="-"
                binding.layout2018.reb.text="-"
                binding.layout2018.ast.text="-"
                binding.layout2018.stl.text="-"
                binding.layout2018.blk.text="-"
                binding.layout2018.tov.text="-"
                binding.layout2018.pf.text="-"
                binding.layout2018.pts.text="-"
                binding.layout2018.fgpct.text="-"
                binding.layout2018.fg3pct.text="-"
                binding.layout2018.ftpct.text="-"
            }else {


                binding.layout2018.min.text = it[0].min ?: "-"
                binding.layout2018.fgm.text = it[0].fgm.toString() ?: "-"
                binding.layout2018.fga.text = it[0].fga.toString() ?: "-"
                binding.layout2018.fg3m.text = it[0].fg3m.toString() ?: "-"
                binding.layout2018.fg3a.text = it[0].fg3a.toString() ?: "-"
                binding.layout2018.ftm.text = it[0].ftm.toString() ?: "-"
                binding.layout2018.fta.text = it[0].fta.toString() ?: "-"
                binding.layout2018.oreb.text = it[0].oreb.toString() ?: "-"
                binding.layout2018.dreb.text = it[0].dreb.toString() ?: "-"
                binding.layout2018.reb.text = it[0].reb.toString() ?: "-"
                binding.layout2018.ast.text = it[0].ast.toString() ?: "-"
                binding.layout2018.stl.text = it[0].stl.toString() ?: "-"
                binding.layout2018.blk.text = it[0].blk.toString() ?: "-"
                binding.layout2018.tov.text = it[0].turnover.toString() ?: "-"
                binding.layout2018.pf.text = it[0].pf.toString() ?: "-"
                binding.layout2018.pts.text = it[0].pts.toString() ?: "-"
                binding.layout2018.fgpct.text = it[0].fgPct.toString() ?: "-"
                binding.layout2018.fg3pct.text = it[0].fg3Pct.toString() ?: "-"
                binding.layout2018.ftpct.text = it[0].ftPct.toString() ?: "-"
            }
        }
        playerStatisticsViewModel.getSeasonAveragefor2021(player)
        playerStatisticsViewModel.getSeasonAveragefor2020(player)
        playerStatisticsViewModel.getSeasonAveragefor2019(player)
        playerStatisticsViewModel.getSeasonAveragefor2018(player)
        return root
    }



}