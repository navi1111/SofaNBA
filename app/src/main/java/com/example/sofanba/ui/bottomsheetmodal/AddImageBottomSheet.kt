package com.example.sofanba.ui.bottomsheetmodal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sofanba.databinding.BottomSheetAddImageBinding
import com.example.sofanba.databinding.FragmentPlayerBinding
import com.example.sofanba.model.Player
import com.example.sofanba.model.PlayerImage
import com.example.sofanba.ui.playeractivity.ui.main.PlayerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddImageBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddImageBinding?=null
    private val binding get() = _binding!!
    private val playerViewModel: PlayerViewModel by activityViewModels()
    private var player=Player()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddImageBinding.inflate(inflater, container, false)
        val root = binding.root
        player=activity?.intent?.getSerializableExtra("player") as Player
        binding.btnAddPlayerImage.setOnClickListener {
            playerViewModel.setPlayerImage(PlayerImage(player.id ?: null,binding.urltatv.text.toString() ?: "",binding.phototitletatv.text.toString() ?: null))
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}