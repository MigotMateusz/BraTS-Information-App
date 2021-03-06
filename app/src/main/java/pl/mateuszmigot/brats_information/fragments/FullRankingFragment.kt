package pl.mateuszmigot.brats_information.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.activities.DataActivity
import pl.mateuszmigot.brats_information.adapters.DataRankingAdapter

class FullRankingFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var dataRankingAdapter: DataRankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_full_ranking, container, false)
        setupTextView(view)
        setupRecyclerView(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setupTextView(view: View) {
        val textView = view.findViewById<MaterialTextView>(R.id.myRankingText)
        val modelRanking = (activity as DataActivity).getMyModelRanking()
        val text1 = getString(R.string.my_model_text1)
        val text2 = getString(R.string.my_model_text2)
        textView.text = "$text1: ${modelRanking.myRanking} $text2 ${modelRanking.numberOfTeams}"
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.full_ranking_recycler_view)
        dataRankingAdapter = DataRankingAdapter((activity as DataActivity).getTeams())
        recyclerView.adapter = dataRankingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}