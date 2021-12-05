package pl.mateuszmigot.brats_information.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.activities.DataActivity
import pl.mateuszmigot.brats_information.adapters.DataRankingAdapter
import pl.mateuszmigot.brats_information.adapters.Top10RankingAdapter

class Top10Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var top10RankingAdapter: Top10RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_top10, container, false)
        setupRecyclerView(view)
        return view
    }

    fun setupRecyclerView(view: View) {
        Log.i("Recycler-view", "Setup in top10")
        recyclerView = view.findViewById(R.id.top10_ranking_recycler_view)
        top10RankingAdapter = Top10RankingAdapter((activity as DataActivity).getTop10Teams())
        recyclerView.adapter = top10RankingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}