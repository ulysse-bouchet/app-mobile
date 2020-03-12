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
import fr.ulyssebouchet.gevu.activities.SeeMatchActivity
import fr.ulyssebouchet.gevu.data.Match

class MatchesFragment(private var matches: List<Match>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment: View = inflater.inflate(R.layout.fragment_matches, container, false)

        val insertPoint = fragment.findViewById(R.id.matches_container) as ViewGroup

        for (match in matches) {
            val layout: ConstraintLayout = inflater.inflate(R.layout.match, container, false) as ConstraintLayout

            val score : String = match.homeTeamGoals.toString() + " - " + match.awayTeamGoals.toString()

            layout.findViewById<TextView>(R.id.home_team).text = match.homeTeam
            layout.findViewById<TextView>(R.id.score).text = score
            layout.findViewById<TextView>(R.id.away_team).text = match.awayTeam
            layout.findViewById<TextView>(R.id.date).text = match.date

            layout.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, SeeMatchActivity::class.java)
                intent.putExtra("match", match.toString())
                startActivity(intent)
            })

            insertPoint.addView(layout, insertPoint.childCount)
        }

        return fragment
    }
}