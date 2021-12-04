package pl.mateuszmigot.brats_information.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        setupRecyclerView(view)
        return view
    }

    fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.full_ranking_recycler_view)
        dataRankingAdapter = DataRankingAdapter((activity as DataActivity).teams)
        recyclerView.adapter = dataRankingAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}