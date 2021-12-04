package pl.mateuszmigot.brats_information.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.models.Team

class Top10RankingAdapter(private val top10Teams: MutableList<Team>) :
    RecyclerView.Adapter<Top10RankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val diceEtTextView: MaterialTextView = itemView.findViewById(R.id.dice_et_text)
        val diceWtTextView: MaterialTextView = itemView.findViewById(R.id.dice_wt_text)
        val diceTcTextView: MaterialTextView = itemView.findViewById(R.id.dice_tc_text)
        val sensitivityEtTextView: MaterialTextView = itemView.findViewById(R.id.sensitivity_et_text)
        val sensitivityTcTextView: MaterialTextView = itemView.findViewById(R.id.sensitivity_tc_text)
        val sensitivityWtTextView: MaterialTextView = itemView.findViewById(R.id.sensitivity_wt_text)
        val specificityEtTextView: MaterialTextView = itemView.findViewById(R.id.specificity_et_text)
        val specificityTcTextView: MaterialTextView = itemView.findViewById(R.id.specificity_tc_text)
        val specificityWtTextView: MaterialTextView = itemView.findViewById(R.id.specificity_wt_text)
        val hausdorff95EtTextView: MaterialTextView = itemView.findViewById(R.id.hausdorff95_et_text)
        val hausdorff95WtTextView: MaterialTextView = itemView.findViewById(R.id.hausdorff95_wt_text)
        val hausdorff95TcTextView: MaterialTextView = itemView.findViewById(R.id.hausdorff95_tc_text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Top10RankingAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val teamView = inflater.inflate(R.layout.top10_item_layout, parent, false)
        return ViewHolder(teamView)
    }

    override fun onBindViewHolder(holder: Top10RankingAdapter.ViewHolder, position: Int) {
        val team = top10Teams[position]
        holder.nameTextView.text = (position + 1).toString() + ". " + team.name

        holder.diceEtTextView.text = team.dice_et.toString()
        holder.diceWtTextView.text = team.dice_wt.toString()
        holder.diceTcTextView.text = team.dice_tc.toString()

        holder.sensitivityEtTextView.text = team.sensitivity_et.toString()
        holder.sensitivityWtTextView.text = team.sensitivity_wt.toString()
        holder.sensitivityTcTextView.text = team.sensitivity_tc.toString()

        holder.specificityEtTextView.text = team.specificity_et.toString()
        holder.specificityWtTextView.text = team.specificity_wt.toString()
        holder.specificityTcTextView.text = team.specificity_tc.toString()

        holder.hausdorff95EtTextView.text = team.hausdorff95_et.toString()
        holder.hausdorff95WtTextView.text = team.hausdorff95_wt.toString()
        holder.hausdorff95TcTextView.text = team.hausdorff95_tc.toString()
    }

    override fun getItemCount(): Int {
        return top10Teams.size
    }
}