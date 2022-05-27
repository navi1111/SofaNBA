package com.example.sofanba.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.sofanba.databinding.FragmentFavouritesBinding
import com.example.sofanba.model.Player
import com.example.sofanba.model.Team
import com.example.sofanba.recview.*
import com.example.sofanba.util.TeamActivity

class FavouritesFragment : Fragment() {
    private val favoriteviewModel: FavouritesViewModel by activityViewModels()
    private var _binding: FragmentFavouritesBinding? = null
    private lateinit var teamRecyclerAdapter: TeamRecyclerAdapter
    private lateinit var playerRecyclerAdapter: PlayerRecyclerAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        favoriteviewModel.favouritesList.observe(viewLifecycleOwner){
            binding.rvFavouriteTeam.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            teamRecyclerAdapter = TeamRecyclerAdapter(adapterListener)
            teamRecyclerAdapter.setTeams(it.toCollection(arrayListOf()))

            binding.rvFavouriteTeam.adapter = teamRecyclerAdapter

        }
        favoriteviewModel.getFavouriteTeams(requireContext())
        favoriteviewModel.favouritePlayerList.observe(viewLifecycleOwner){
            binding.rvFavouritePlayers.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )

            playerRecyclerAdapter = PlayerRecyclerAdapter(playerListener)
            playerRecyclerAdapter.setPlayers(it.toCollection(arrayListOf()))

            binding.rvFavouritePlayers.adapter = playerRecyclerAdapter

        }
        favoriteviewModel.getFavouritePlayers(requireContext())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private val adapterListener=object : OnTeamEventListener {
        override fun onTeamSelected(team: Team) {
            val intent= Intent(activity, TeamActivity::class.java).apply {
                putExtra("team",team)
            }
            startActivity(intent)
            Toast.makeText(activity,"Item is clicked", Toast.LENGTH_LONG).show()


        }

        override fun onTeamImageButtonSelected(team: Team) {

            if (team.isFavourite){
                favoriteviewModel.insertFavouriteTeam(requireContext(),team)

            }else{
                favoriteviewModel.deleteFavouriteTeam(requireContext(),team)

            }

        }
    }

    private val playerListener=object : OnPlayerEventListener {
        override fun onPlayerSelected(player:Player) {
            /*val intent= Intent(activity, TeamActivity::class.java).apply {
                putExtra("team",team)
            }
            startActivity(intent)
            Toast.makeText(activity,"Item is clicked", Toast.LENGTH_LONG).show()*/


        }

        override fun onImageButtonSelected(player: Player) {

            if (player.isFavourite){
                favoriteviewModel.insertFavouritePlayer(requireContext(),player)

            }else{
                favoriteviewModel.deleteFavouritePlayer(requireContext(),player)

            }

        }
    }
}