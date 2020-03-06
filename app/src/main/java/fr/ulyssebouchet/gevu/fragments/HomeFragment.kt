package fr.ulyssebouchet.gevu.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import fr.ulyssebouchet.gevu.R
import fr.ulyssebouchet.gevu.activities.AddMatchActivity
import fr.ulyssebouchet.gevu.activities.MainActivity
import fr.ulyssebouchet.gevu.data.Match

class HomeFragment(private var leagues: Map<String, List<Match>>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment: View = inflater.inflate(R.layout.fragment_home, container, false)

        val insertPoint = fragment.findViewById(R.id.home_container) as ViewGroup

        for (league in leagues) {
            val leagueName: TextView = inflater.inflate(R.layout.league_name, container, false) as TextView
            leagueName.text = league.key
            insertPoint.addView(leagueName, insertPoint.childCount)

            for (match in league.value) {
                val layout: ConstraintLayout = inflater.inflate(R.layout.match, container, false) as ConstraintLayout

                val score : String = match.getHomeTeamGoals().toString() + " - " + match.getAwayTeamGoals().toString()

                layout.findViewById<TextView>(R.id.home_team).text = match.getHomeTeam()
                layout.findViewById<TextView>(R.id.score).text = score
                layout.findViewById<TextView>(R.id.away_team).text = match.getAwayTeam()
                layout.findViewById<TextView>(R.id.date).text = match.getDate()

                layout.setOnClickListener(View.OnClickListener {
                    val intent = Intent(context, AddMatchActivity::class.java)
                    intent.putExtra("match", match.toString())
                    startActivity(intent)
                })

                insertPoint.addView(layout, insertPoint.childCount)
            }
        }

        return fragment
    }
}