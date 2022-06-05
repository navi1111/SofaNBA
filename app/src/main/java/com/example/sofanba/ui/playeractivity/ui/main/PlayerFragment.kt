package com.example.sofanba.ui.playeractivity.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sofanba.R
import com.example.sofanba.databinding.FragmentPlayerBinding
import com.example.sofanba.model.Player
import com.example.sofanba.ui.bottomsheetmodal.AddImageBottomSheet
import com.example.sofanba.util.TeamIconHelper
import kotlin.concurrent.fixedRateTimer


/**
 * A placeholder fragment containing a simple view.
 */
class PlayerFragment : Fragment() {
    private lateinit var player:Player
    private val playerViewModel: PlayerViewModel by activityViewModels()
    private var _binding: FragmentPlayerBinding? = null
    private val teamIconHelper=TeamIconHelper()
    val addImageBottomSheet=AddImageBottomSheet()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        val root = binding.root
        player=activity?.intent?.getSerializableExtra("player" ) as Player
        binding.playerTeamphoto.setImageResource(teamIconHelper.getWeatherIcon(player.team?.abbreviation ?: "LAL"))
        binding.playerTeamphoto.background=resources.getDrawable(teamIconHelper.getTeamColor(player.team?.abbreviation ?: "LAL"))
        if(player.heightFeet==null || player.heightInches==null){
            binding.tvWeightvalue.text="6'4''"
        }else{
            binding.tvHeightvalue.text=player.heightFeet + "'" + player.heightInches + "''"
        }
        if(player.weightPounds==null){
            binding.tvWeightvalue.text="220lb"
        }else{
            binding.tvWeightvalue.text=player.weightPounds + "lb"
        }
        if(player.position==null){
            binding.tvPlayerPosition.text="GUARD"
        }else{
            binding.tvPlayerPosition.text=teamIconHelper.getPlayerPosition(player.position as String)
        }

        binding.tvPlayerTeamName.text=player.team?.fullName
        binding.btnAddModal.setOnClickListener {
            fragmentManager?.let { it1 -> addImageBottomSheet.show(it1,"TAG") }
        }
        Glide.with(requireContext()).load(player.imageUrl).placeholder(R.drawable.ic_player_placeholder_graphic_80_dp)
            .error(R.drawable.ic_player_placeholder_graphic_80_dp).into(binding.ivPlayer)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}