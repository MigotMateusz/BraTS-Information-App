package pl.mateuszmigot.brats_information.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import pl.mateuszmigot.brats_information.R
import pl.mateuszmigot.brats_information.models.Model

class MyModelsRankingAdapter(private val models: MutableList<Model>) :
    RecyclerView.Adapter<MyModelsRankingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val diceEtTextView: MaterialTextView = itemView.findViewById(R.id.dice_et_text)
        val diceWtTextView: MaterialTextView = itemView.findViewById(R.id.dice_wt_text)
        val diceTcTextView: MaterialTextView = itemView.findViewById(R.id.dice_tc_text)
        val sensitivityEtTextView: MaterialTextView =
            itemView.findViewById(R.id.sensitivity_et_text)
        val sensitivityTcTextView: MaterialTextView =
            itemView.findViewById(R.id.sensitivity_tc_text)
        val sensitivityWtTextView: MaterialTextView =
            itemView.findViewById(R.id.sensitivity_wt_text)
        val specificityEtTextView: MaterialTextView =
            itemView.findViewById(R.id.specificity_et_text)
        val specificityTcTextView: MaterialTextView =
            itemView.findViewById(R.id.specificity_tc_text)
        val specificityWtTextView: MaterialTextView =
            itemView.findViewById(R.id.specificity_wt_text)
        val hausdorff95EtTextView: MaterialTextView =
            itemView.findViewById(R.id.hausdorff95_et_text)
        val hausdorff95WtTextView: MaterialTextView =
            itemView.findViewById(R.id.hausdorff95_wt_text)
        val hausdorff95TcTextView: MaterialTextView =
            itemView.findViewById(R.id.hausdorff95_tc_text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyModelsRankingAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val modelView = inflater.inflate(R.layout.top10_item_layout, parent, false)
        return ViewHolder(modelView)
    }

    override fun onBindViewHolder(holder: MyModelsRankingAdapter.ViewHolder, position: Int) {
        val model = models[position]
        holder.nameTextView.text = model.MEAN

        holder.diceEtTextView.text = model.Dice_ET.toString()
        holder.diceWtTextView.text = model.Dice_WT.toString()
        holder.diceTcTextView.text = model.Dice_TC.toString()

        holder.sensitivityEtTextView.text = model.Sensitivity_ET.toString()
        holder.sensitivityWtTextView.text = model.Sensitivity_WT.toString()
        holder.sensitivityTcTextView.text = model.Sensitivity_TC.toString()

        holder.specificityEtTextView.text = model.Specificity_ET.toString()
        holder.specificityWtTextView.text = model.Specificity_WT.toString()
        holder.specificityTcTextView.text = model.Specificity_TC.toString()

        holder.hausdorff95EtTextView.text = model.Hausdorff95_ET.toString()
        holder.hausdorff95WtTextView.text = model.Hausdorff95_WT.toString()
        holder.hausdorff95TcTextView.text = model.Hausdorff95_TC.toString()
    }

    override fun getItemCount(): Int {
        return models.size
    }
}