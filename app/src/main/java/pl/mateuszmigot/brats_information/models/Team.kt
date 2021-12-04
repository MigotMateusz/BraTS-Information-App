package pl.mateuszmigot.brats_information.models

data class Team(val name: String, val dice_et: Double, val dice_wt: Double, val dice_tc: Double,
                val specificity_et: Double, val specificity_wt: Double, val specificity_tc: Double,
                val sensitivity_et: Double, val sensitivity_wt: Double, val sensitivity_tc: Double,
                val hausdorff95_et: Double, val hausdorff95_wt: Double, val hausdorff95_tc: Double)