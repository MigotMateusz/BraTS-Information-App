package pl.mateuszmigot.brats_information.adapters

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.models.Team


class DataRankingAdapter(private val teams: MutableList<Team>) :
    RecyclerView.Adapter<DataRankingAdapter.ViewHolder>() {

    var isCardExpanded = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamCardView: MaterialCardView = itemView.findViewById(R.id.teamCardView)
        val innerLinearLayout: LinearLayout = itemView.findViewById(R.id.innerCardLinearLayout)
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val diceTextView: TextView = itemView.findViewById(R.id.dice_text_view)
        val sensitivityTextView: TextView = itemView.findViewById(R.id.sensitivity_text_view)
        val specificityTextView: TextView = itemView.findViewById(R.id.specificity_text_view)
        val hausdorffTextView: TextView = itemView.findViewById(R.id.haussdorf_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val teamView = inflater.inflate(R.layout.data_item_layout, parent, false)
        return ViewHolder(teamView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.nameTextView.text = team.name
        holder.diceTextView.text =
            "Dice -> ET: ${team.dice_et} WT: ${team.dice_wt} TC: ${team.dice_tc}"
        holder.sensitivityTextView.text =
            "Sensitivity -> ET: ${team.sensitivity_et} WT: ${team.sensitivity_wt} TC: ${team.sensitivity_tc}"
        holder.specificityTextView.text =
            "Specificity -> ET: ${team.specificity_et} WT: ${team.specificity_wt} TC: ${team.specificity_tc}"
        holder.hausdorffTextView.text =
            "Hausdorff95 -> ET: ${team.hausdorff95_et} WT: ${team.hausdorff95_wt} TC: ${team.hausdorff95_tc}"
        holder.teamCardView.setOnClickListener {
            expandCard(holder)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    private fun expandCard(viewHolder:DataRankingAdapter.ViewHolder) {
        TransitionManager.beginDelayedTransition(viewHolder.teamCardView, AutoTransition())
        if (isCardExpanded) {
            viewHolder.innerLinearLayout.visibility = View.GONE
            isCardExpanded = false
        } else {
            viewHolder.innerLinearLayout.visibility = View.VISIBLE
            isCardExpanded = true
        }
    }
}