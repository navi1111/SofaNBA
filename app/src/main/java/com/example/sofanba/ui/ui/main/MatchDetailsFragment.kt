package com.example.sofanba.ui.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sofanba.R
import com.example.sofanba.databinding.FragmentMatchDetailsBinding
import com.example.sofanba.databinding.FragmentSeasonsBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper
import java.text.DecimalFormat


class MatchDetailsFragment : Fragment() {
    private val matchDetailsViewModel:MatchDetailsViewModel by activityViewModels()
    private var _binding: FragmentMatchDetailsBinding? = null
    private val binding get() = _binding!!
    private var game:Game=Game()
    private val teamIconHelper:TeamIconHelper= TeamIconHelper()
    val dec = DecimalFormat("##")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        game=activity?.intent?.getSerializableExtra("game") as Game
        binding.threeptpct.statsCategory.text="FG3%"
        binding.rebounds.statsCategory.text="REB"
        binding.assists.statsCategory.text="AST"
        binding.turnovers.statsCategory.text="TOV"
        binding.offreb.statsCategory.text="OREB"
        binding.gamelayout.tvSecondTeamAbb.text=game?.visitorTeam?.abbreviation
        binding.gamelayout.tvFirstTeamAbb.text=game?.homeTeam?.abbreviation
        binding.gamelayout.tvFirstScore.text=game?.homeTeamScore.toString()
        binding.gamelayout.tvSecondScore.text=game?.visitorTeamScore.toString()
        binding.gamelayout.tvGametime.text=game?.status
        binding.gamelayout.tvDate.text=game?.date
        binding.gamelayout.firstTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.homeTeam?.abbreviation ?: "LAL"))
        binding.gamelayout.secondTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.fgpct.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.fgpct.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.threeptpct.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.threeptpct.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.rebounds.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.rebounds.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.assists.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.assists.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.turnovers.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.turnovers.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        binding.offreb.firstBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.homeTeam?.abbreviation ?: "LAL"))
        binding.offreb.secondBattery.background=resources.getDrawable(teamIconHelper.getTeamColor(game.visitorTeam?.abbreviation ?: "LAL"))
        matchDetailsViewModel.stats.observe(viewLifecycleOwner){
            binding.fgpct.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalFgPct(it, game.homeTeam!!)).toString()
            binding.fgpct.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalFgPct(it,game.visitorTeam!!)).toString()
            binding.assists.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalAssists(game.homeTeam!!)).toString()
            binding.assists.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalAssists(game.visitorTeam!!)).toString()
            binding.threeptpct.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalThreePtPct(it,game.homeTeam!!)).toString()
            binding.threeptpct.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalThreePtPct(it,game.visitorTeam!!)).toString()
            binding.rebounds.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalRebounds(it,game.homeTeam!!)).toString()
            binding.rebounds.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalRebounds(it,game.visitorTeam!!)).toString()
            binding.turnovers.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalTurnovers(it,game.homeTeam!!)).toString()
            binding.turnovers.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalTurnovers(it,game.visitorTeam!!)).toString()
            binding.offreb.firstNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalOffensiveRebounds(it,game.homeTeam!!)).toString()
            binding.offreb.secondNumberValue.text=dec.format(matchDetailsViewModel.calculateTotalOffensiveRebounds(it,game.visitorTeam!!)).toString()
            binding.fgpct.firstBattery.layoutParams.height=(matchDetailsViewModel.calculateTotalFgPct(it, game.homeTeam!!)/100 * binding.fgpct.firstBattery.height).toInt()
            binding.fgpct.secondBattery.layoutParams.height=(matchDetailsViewModel.calculateTotalFgPct(it, game.homeTeam!!)/100 * binding.fgpct.firstBattery.height).toInt()
            binding.threeptpct.firstBattery.layoutParams.height=(matchDetailsViewModel.calculateTotalThreePtPct(it, game.homeTeam!!)/100 * binding.fgpct.firstBattery.height).toInt()
            binding.threeptpct.secondBattery.layoutParams.height=(matchDetailsViewModel.calculateTotalThreePtPct(it, game.homeTeam!!)/100 * binding.fgpct.firstBattery.height).toInt()


        }

        matchDetailsViewModel.getStatsforGame(game)
        return root
    }


}