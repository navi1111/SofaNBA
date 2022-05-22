package com.example.sofanba.recview

import com.example.sofanba.model.Player

interface OnPlayerEventListener {
    fun onPlayerSelected(player: Player)

    fun onImageButtonSelected(player: Player)
}