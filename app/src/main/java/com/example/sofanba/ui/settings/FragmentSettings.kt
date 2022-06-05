package com.example.sofanba.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sofanba.R

import com.example.sofanba.databinding.FragmentSettingsBinding


class FragmentSettings : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val settingsViewModel:SettingsViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnClearFavourites.setOnClickListener {
            settingsViewModel.clearFavouritePlayerList(requireContext())
            settingsViewModel.clearFavouriteTeamList(requireContext())
        }
        binding.btnMoreinfo.setOnClickListener {
            val intent=Intent(activity,AboutActivity::class.java)
            startActivity(intent)


        }
        return root
        //return inflater.inflate(R.layout.fragment_settings, container, false)
    }



}