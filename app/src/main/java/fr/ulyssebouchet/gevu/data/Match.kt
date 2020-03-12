package fr.ulyssebouchet.gevu.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Match (
        @PrimaryKey val id : Int,
        @ColumnInfo(name = "home_team") val homeTeam : String,
        @ColumnInfo(name = "away_team") val awayTeam : String,
        @ColumnInfo(name = "home_team_goals") val homeTeamGoals : Int,
        @ColumnInfo(name = "away_team_goals") val awayTeamGoals : Int,
        utcDate : String,
        @ColumnInfo(name = "anecdote") var anecdote : String,
        @ColumnInfo(name = "image") var image : String
) {
    constructor(id: Int, homeTeam: String, awayTeam: String, homeTeamGoals: Int, awayTeamGoals: Int, date: String)
        : this(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date, "", "")

    @ColumnInfo(name = "date") val date : String = utcDate.replace("-", "/").replace("T", " ").removeSuffix("Z")

    override fun toString(): String {
        return "id=$id," +
                "homeTeam=$homeTeam," +
                "awayTeam=$awayTeam," +
                "homeTeamGoals=$homeTeamGoals," +
                "awayTeamGoals=$awayTeamGoals," +
                "date=$date," +
                "anecdote=$anecdote," +
                "image=$image,"
    }

    companion object {
        fun getMatch (match : String) : Match {
            val matchData = match.split(',')
            val id = matchData[0].removePrefix("id=").toInt()
            val homeTeam = matchData[1].removePrefix("homeTeam=")
            val awayTeam = matchData[2].removePrefix("awayTeam=")
            val homeTeamGoals = matchData[3].removePrefix("homeTeamGoals=").toInt()
            val awayTeamGoals = matchData[4].removePrefix("awayTeamGoals=").toInt()
            val date = matchData[5].removePrefix("date=")
            val anecdote = matchData[6].removePrefix("anecdote=")
            val image = matchData[7].removePrefix("image=")

            return Match(id, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date, anecdote, image)
        }
    }
}