package com.example.sofanba.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentNotificationsBinding
import com.example.sofanba.network.paging.GameDiff
import com.example.sofanba.network.paging.PlayerDiff
import com.example.sofanba.recview.GamePagingAdapter
import com.example.sofanba.recview.GameRecyclerAdapter
import com.example.sofanba.recview.PlayerPagingAdapter
import com.example.sofanba.recview.TeamRecyclerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotificationsFragment : Fragment() {
    private val seasonsViewModel:NotificationsViewModel by activityViewModels()
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var gamesAdapter:GameRecyclerAdapter
    private lateinit var pagingAdapter: GamePagingAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*seasonsViewModel.gameList.observe(viewLifecycleOwner){
            binding.rvGames.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            gamesAdapter = GameRecyclerAdapter()
            gamesAdapter.setTeams(it.toCollection(arrayListOf()))

            binding.rvGames.adapter = gamesAdapter

        }
        seasonsViewModel.getGames()*/
        pagingAdapter= GamePagingAdapter(requireContext(), GameDiff)
        binding.rvGames.adapter=pagingAdapter
        lifecycleScope.launch {
            seasonsViewModel.flow.collectLatest {

                pagingAdapter.submitData(it)

            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}