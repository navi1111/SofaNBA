package com.example.sofanba.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentSeasonsBinding
import com.example.sofanba.network.paging.GameDiff
import com.example.sofanba.recview.GamePagingAdapter
import com.example.sofanba.recview.GameRecyclerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SeasonsFragment : Fragment() {
    private val seasonsViewModel:SeasonsViewModel by activityViewModels()
    private var _binding: FragmentSeasonsBinding? = null
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


        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rvGames.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
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