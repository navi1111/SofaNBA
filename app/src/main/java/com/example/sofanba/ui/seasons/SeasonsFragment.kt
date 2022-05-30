package com.example.sofanba.ui.seasons

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.databinding.FragmentSeasonsBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.network.paging.GameDiff
import com.example.sofanba.recview.GamePagingAdapter
import com.example.sofanba.recview.GameRecyclerAdapter
import com.example.sofanba.recview.OnGameEventListener
import com.example.sofanba.recview.OnPlayerEventListener
import com.example.sofanba.ui.MatchActivity
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
        pagingAdapter= GamePagingAdapter(gameListener, GameDiff)
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
    private val gameListener=object : OnGameEventListener {
        override fun onGameSelected(game: Game) {
            val intent= Intent(activity, MatchActivity::class.java).apply {
                putExtra("game",game)
            }
            startActivity(intent)



        }

    }
}