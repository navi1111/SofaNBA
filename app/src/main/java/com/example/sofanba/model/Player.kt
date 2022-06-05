package com.example.sofanba.model

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Player(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("height_feet") var heightFeet: String? = null,
    @SerializedName("height_inches") var heightInches: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("position") var position: String? = null,
    @SerializedName("team") var team: Team? = Team(),
    @SerializedName("weight_pounds") var weightPounds: String? = null,
    var isFavourite: Boolean = false,
    var imageUrl: String? = null
) : Serializable

data class Team(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("abbreviation") var abbreviation: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("conference") var conference: String? = null,
    @SerializedName("division") var division: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("name") var name: String? = null,
    var isFavourite: Boolean = false
) : Serializable

data class PlayersData(
    @SerializedName("data") var players: ArrayList<Player> = arrayListOf(),
    @SerializedName("meta") var meta: Meta? = Meta()
)

data class TeamsData(
    @SerializedName("data") var teams: ArrayList<Team> = arrayListOf()
)

data class GamesData(

    @SerializedName("data") var games: ArrayList<Game> = arrayListOf(),
    @SerializedName("meta") var meta: Meta? = Meta()

)

data class StatsData(

    @SerializedName("data") var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("meta") var meta: Meta? = Meta()

)

data class SeasonAveragesData(
    @SerializedName("data") var data: ArrayList<SeasonAverages> = arrayListOf()
)

data class Meta(

    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("next_page") var nextPage: String? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("total_count") var totalCount: Int? = null

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

data class Game(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("home_team") var homeTeam: Team? = Team(),
    @SerializedName("home_team_score") var homeTeamScore: Int? = null,
    @SerializedName("period") var period: Int? = null,
    @SerializedName("postseason") var postseason: Boolean? = null,
    @SerializedName("season") var season: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("visitor_team") var visitorTeam: Team? = Team(),
    @SerializedName("visitor_team_score") var visitorTeamScore: Int? = null
) : Serializable

data class Stats(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("ast") var ast: Int? = null,
    @SerializedName("blk") var blk: Int? = null,
    @SerializedName("dreb") var dreb: Int? = null,
    @SerializedName("fg3_pct") var fg3Pct: Double? = null,
    @SerializedName("fg3a") var fg3a: Int? = null,
    @SerializedName("fg3m") var fg3m: Int? = null,
    @SerializedName("fg_pct") var fgPct: Double? = null,
    @SerializedName("fga") var fga: Int? = null,
    @SerializedName("fgm") var fgm: Int? = null,
    @SerializedName("ft_pct") var ftPct: Double? = null,
    @SerializedName("fta") var fta: Int? = null,
    @SerializedName("ftm") var ftm: Int? = null,
    @SerializedName("game") var game: Game? = Game(),
    @SerializedName("min") var min: String? = null,
    @SerializedName("oreb") var oreb: Int? = null,
    @SerializedName("pf") var pf: Int? = null,
    @SerializedName("player") var player: Player? = Player(),
    @SerializedName("pts") var pts: Int? = null,
    @SerializedName("reb") var reb: Int? = null,
    @SerializedName("stl") var stl: Int? = null,
    @SerializedName("team") var team: Team? = Team(),
    @SerializedName("turnover") var turnover: Int? = null,
    var gameInfo:Game?=Game()

)

data class SeasonAverages(
    @SerializedName("games_played") var gamesPlayed: Int? = null,
    @SerializedName("player_id") var playerId: Int? = null,
    @SerializedName("season") var season: Int? = null,
    @SerializedName("min") var min: String? = null,
    @SerializedName("fgm") var fgm: Double? = null,
    @SerializedName("fga") var fga: Double? = null,
    @SerializedName("fg3m") var fg3m: Double? = null,
    @SerializedName("fg3a") var fg3a: Double? = null,
    @SerializedName("ftm") var ftm: Double? = null,
    @SerializedName("fta") var fta: Double? = null,
    @SerializedName("oreb") var oreb: Double? = null,
    @SerializedName("dreb") var dreb: Double? = null,
    @SerializedName("reb") var reb: Double? = null,
    @SerializedName("ast") var ast: Double? = null,
    @SerializedName("stl") var stl: Double? = null,
    @SerializedName("blk") var blk: Double? = null,
    @SerializedName("turnover") var turnover: Double? = null,
    @SerializedName("pf") var pf: Double? = null,
    @SerializedName("pts") var pts: Double? = null,
    @SerializedName("fg_pct") var fgPct: Double? = null,
    @SerializedName("fg3_pct") var fg3Pct: Double? = null,
    @SerializedName("ft_pct") var ftPct: Double? = null
)

data class PlayerImage(
    @SerializedName("playerId") var playerId: Int? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("imageCaption") var imageCaption: String? = null,

)
data class YTVideo(
    @SerializedName("eventId"        ) var eventId        : Int?    = null,
    @SerializedName("startTimestamp" ) var startTimestamp : Int?    = null,
    @SerializedName("name"           ) var name           : String? = null,
    @SerializedName("url"            ) var url            : String? = null,
    @SerializedName("playerIdList"   ) var playerIdList   : String? = null

)

data class PlayerImageData(
    @SerializedName("data" ) var data : ArrayList<PlayerImage> = arrayListOf()
)
data class YTVideoData(
    @SerializedName("data" ) var data : ArrayList<YTVideo> = arrayListOf()
)
