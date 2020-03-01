package fr.ulyssebouchet.gevu.data


class Match (private var homeTeam : String, private var awayTeam : String) {

    override fun toString(): String {
        return "homeTeam=$homeTeam,awayTeam=$awayTeam"
    }

    companion object {
        fun getMatch (match : String) : Match {
            val matchData = match.split(',')
            val homeTeam = matchData[0].removePrefix("homeTeam=")
            val awayTeam = matchData[1].removePrefix("awayTeam=")
            return Match(homeTeam, awayTeam)
        }
    }
}