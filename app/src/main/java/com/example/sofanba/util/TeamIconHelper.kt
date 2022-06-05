package com.example.sofanba.util

import com.example.sofanba.R

class TeamIconHelper {
    fun getWeatherIcon(teamAbb:String):Int{
        when(teamAbb){
            "LAL" -> return R.drawable.ic_assets_los_angeles_lakers_transparent
            "BKN" -> return R.drawable.ic_assets_brooklyn_nets_transparent
            "BOS" -> return R.drawable.ic_assets_exportable_logos_club_logos_eastern_conference_boston_celtics_transparent
            "CHA" -> return R.drawable.ic_assets_charlotte_hornets_transparent
            "CHI" -> return R.drawable.ic_assets_chicago_bulls_transparent
            "CLE" -> return R.drawable.ic_assets_cleveland_cavaliers_transparent
            "DAL" -> return R.drawable.ic_assets_dallas_mavericks_transparent
            "DEN" -> return R.drawable.ic_assets_denver_nuggets_transparent
            "DET" -> return R.drawable.ic_assets_detroit_pistons_transparent
            "GSW" -> return R.drawable.ic_assets_golden_state_warriors_transparent
            "HOU" -> return R.drawable.ic_assets_huston_rockets_transparent
            "IND" -> return R.drawable.ic_assets_indiana_pacers_transparent
            "LAC" -> return R.drawable.ic_assets_los_angeles_clippers_transparent
            "ATL" -> return R.drawable.ic_assets_exportable_logos_club_logos_eastern_conference_atlanta_hawks_transparent
            "MEM" -> return R.drawable.ic_assets_memphis_grizzlies_transparent
            "MIA" -> return R.drawable.ic_assets_miami_heat_transparent
            "MIL" -> return R.drawable.ic_assets_milwuakee_bucks_transparent
            "MIN" -> return R.drawable.ic_assets_minnesota_timberwolves_transparent
            "NOP" -> return R.drawable.ic_assets_new_orleans_pelicans_transparent
            "NYK" -> return R.drawable.ic_assets_ny_knicks_transparent
            "OKC" -> return R.drawable.ic_assets_oklahoma_city_thunder_transparent
            "ORL" -> return R.drawable.ic_assets_orlando_magic_transparent
            "PHI" -> return R.drawable.ic_assets_philadelphia_76_ers_transparent
            "PHX" -> return R.drawable.ic_assets_phoenix_suns_transparent
            "POR" -> return R.drawable.ic_assets_portland_trailblazers_transparent
            "SAC" -> return R.drawable.ic_assets_sacramento_kings_transparent
            "SAS" -> return R.drawable.ic_assets_san_antonio_spurs_transparent
            "TOR" -> return R.drawable.ic_assets_toronto_raptors_transparent
            "UTA" -> return R.drawable.ic_assets_utah_jazz_transparent
            "WAS" -> return R.drawable.ic_assets_washington_wizards_transparent
            else ->
                return R.drawable.ic_assets_los_angeles_lakers_transparent

        }
    }
    fun getTeamColor(teamAbb:String):Int{
        when(teamAbb){
            "LAL" -> return R.color.team_lakers_primary
            "BKN" -> return R.color.team_nets_primary
            "BOS" -> return R.color.team_celtics_primary
            "CHA" -> return R.color.team_hornets_primary
            "CHI" -> return R.color.team_bulls_primary
            "CLE" -> return R.color.team_cavaliers_primary
            "DAL" -> return R.color.team_mavericks_primary
            "DEN" -> return R.color.team_nuggets_primary
            "DET" -> return R.color.team_pistons_primary
            "GSW" -> return R.color.team_warriors_primary
            "HOU" -> return R.color.team_rockets_primary
            "IND" -> return R.color.team_pacers_primary
            "LAC" -> return R.color.team_clippers_primary
            "ATL" -> return R.color.team_hawks_primary
            "MEM" -> return R.color.team_grizzlies_primary
            "MIA" -> return R.color.team_heat_primary
            "MIL" -> return R.color.team_bucks_primary
            "MIN" -> return R.color.team_timberwolves_primary
            "NOP" -> return R.color.team_pelicans_primary
            "NYK" -> return R.color.team_knicks_primary
            "OKC" -> return R.color.team_thunder_primary
            "ORL" -> return R.color.team_magic_primary
            "PHI" -> return R.color.on_color_on_color_primary
            "PHX" -> return R.color.team_suns_primary
            "POR" -> return R.color.team_blazers_primary
            "SAC" -> return R.color.team_kings_primary
            "SAS" -> return R.color.team_spurs_primary
            "TOR" -> return R.color.team_raptors_primary
            "UTA" -> return R.color.team_jazz_primary
            "WAS" -> return R.color.team_wizards_primary
            else ->
                return R.color.color_primary

        }
    }
    fun getPlayerPosition(input:String):String{
        when(input){
            "G" -> return "GUARD"
            "F" -> return "FORWARD"
            "C" -> return "CENTER"
            else -> return "POSITIONLESS"
        }
    }
}