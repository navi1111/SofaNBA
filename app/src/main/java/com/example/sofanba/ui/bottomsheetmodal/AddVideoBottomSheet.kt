package com.example.sofanba.ui.bottomsheetmodal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sofanba.databinding.BottomSheetAddVideoBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.YTVideo
import com.example.sofanba.ui.match.main.MatchDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddVideoBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddVideoBinding?=null
    private val binding get() = _binding!!
    private val matchDetailsViewModel: MatchDetailsViewModel by activityViewModels()
    private var game= Game()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddVideoBinding.inflate(inflater, container, false)
        val root = binding.root
        game=activity?.intent?.getSerializableExtra("game") as Game
        binding.btnAddYtVideo.setOnClickListener {
            matchDetailsViewModel.addYTVideo(YTVideo(game.id,0,binding.videotitletatv.text.toString(),binding.yturltatv.text.toString(),null))
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}