package com.example.sofanba.model

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Player (
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("first_name"    ) var firstName    : String? = null,
    @SerializedName("height_feet"   ) var heightFeet   : String? = null,
    @SerializedName("height_inches" ) var heightInches : String? = null,
    @SerializedName("last_name"     ) var lastName     : String? = null,
    @SerializedName("position"      ) var position     : String? = null,
    @SerializedName("team"          ) var team         : Team?   = Team(),
    @SerializedName("weight_pounds" ) var weightPounds : String? = null,
    var isFavourite:Boolean=false
        ):Serializable

data class Team(
    @SerializedName("id"           ) var id           : Int?    = null,
    @SerializedName("abbreviation" ) var abbreviation : String? = null,
    @SerializedName("city"         ) var city         : String? = null,
    @SerializedName("conference"   ) var conference   : String? = null,
    @SerializedName("division"     ) var division     : String? = null,
    @SerializedName("full_name"    ) var fullName     : String? = null,
    @SerializedName("name"         ) var name         : String? = null,
    var isFavourite:Boolean=false
):Serializable
data class PlayersData(
    @SerializedName("data" ) var players : ArrayList<Player> = arrayListOf(),
    @SerializedName("meta" ) var meta : Meta?           = Meta()
)
data class TeamsData(
    @SerializedName("data" ) var teams : ArrayList<Team> = arrayListOf()
)
data class GamesData (

    @SerializedName("data" ) var games : ArrayList<Game> = arrayListOf(),
    @SerializedName("meta" ) var meta : Meta?           = Meta()

)
data class Meta (

    @SerializedName("total_pages"  ) var totalPages  : Int?    = null,
    @SerializedName("current_page" ) var currentPage : Int?    = null,
    @SerializedName("next_page"    ) var nextPage    : String? = null,
    @SerializedName("per_page"     ) var perPage     : Int?    = null,
    @SerializedName("total_count"  ) var totalCount  : Int?    = null

)
@Entity
data class FavouritePlayer(
    @PrimaryKey
    var id: Int?
)
@Entity
data class FavouriteTeam(
    @PrimaryKey
    var id: Int?
)

data class Game (
    @SerializedName("id"                 ) var id               : Int?         = null,
    @SerializedName("date"               ) var date             : String?      = null,
    @SerializedName("home_team"          ) var homeTeam         : Team?    = Team(),
    @SerializedName("home_team_score"    ) var homeTeamScore    : Int?         = null,
    @SerializedName("period"             ) var period           : Int?         = null,
    @SerializedName("postseason"         ) var postseason       : Boolean?     = null,
    @SerializedName("season"             ) var season           : Int?         = null,
    @SerializedName("status"             ) var status           : String?      = null,
    @SerializedName("time"               ) var time             : String?      = null,
    @SerializedName("visitor_team"       ) var visitorTeam      : Team? = Team(),
    @SerializedName("visitor_team_score" ) var visitorTeamScore : Int?         = null
):Serializable
