package com.example.sofanba.recview

import com.example.sofanba.model.Team

interface OnTeamEventListener {
    fun onTeamSelected(team:Team)

    fun onTeamImageButtonSelected(team: Team)
}