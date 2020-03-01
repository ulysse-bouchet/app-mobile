package fr.ulyssebouchet.gevu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.ulyssebouchet.gevu.R
import fr.ulyssebouchet.gevu.data.Match

class HomeFragment(private var matches: List<Match>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val lyt: View = inflater.inflate(R.layout.fragment_home, container, false)

        val t: TextView = lyt.findViewById(R.id.home_text)
        val s = StringBuilder()

        for (m in matches) s.append(m)
            t.text = s.toString()

        return lyt
    }
}