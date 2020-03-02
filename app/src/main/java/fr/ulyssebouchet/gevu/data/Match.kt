package fr.ulyssebouchet.gevu.data

class Match (
        private var homeTeam : String,
        private var awayTeam : String,
        private var homeTeamGoals : Int,
        private var awayTeamGoals : Int,
        utcDate : String
) {

    fun getHomeTeam() : String {
        return homeTeam
    }

    fun getAwayTeam() : String {
        return awayTeam
    }

    fun getHomeTeamGoals() : Int {
        return homeTeamGoals
    }

    fun getAwayTeamGoals() : Int {
        return awayTeamGoals
    }

    fun getDate() : String {
        return date
    }

    private var date : String = utcDate.replace("-", "/").replace("T", " ").removeSuffix("Z")
    override fun toString(): String {
        return "homeTeam=$homeTeam," +
                "awayTeam=$awayTeam," +
                "homeTeamGoals=$homeTeamGoals," +
                "awayTeamGoals=$awayTeamGoals," +
                "date=$date,"
    }

    companion object {
        fun getMatch (match : String) : Match {
            val matchData = match.split(',')
            val homeTeam = matchData[0].removePrefix("homeTeam=")
            val awayTeam = matchData[1].removePrefix("awayTeam=")
            val homeTeamGoals = matchData[2].removePrefix("homeTeamGoals=").toInt()
            val awayTeamGoals = matchData[3].removePrefix("awayTeamGoals=").toInt()
            val date = matchData[4].removePrefix("date=")

            return Match(homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, date)
        }
    }
}