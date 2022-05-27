package com.example.sofanba.ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sofanba.databinding.FragmentMatchBinding


/**
 * A placeholder fragment containing a simple view.
 */
class MatchLeadersFragment : Fragment() {

    private lateinit var matchLeadersViewModel: MatchLeadersViewModel
    private var _binding: FragmentMatchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        matchLeadersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}