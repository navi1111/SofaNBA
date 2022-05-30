package com.example.sofanba.recview

import com.example.sofanba.model.Game
import com.example.sofanba.model.Player

interface OnGameEventListener {
    fun onGameSelected(game: Game)
}