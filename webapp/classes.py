class Dice:
  def __init__(self, et, wt, tc):
    self.et = et
    self.wt = wt
    self.tc = tc

class Sensitivity:
  def __init__(self, et, wt, tc):
    self.et = et
    self.wt = wt
    self.tc = tc

class Specificity:
  def __init__(self, et, wt, tc):
    self.et = et
    self.wt = wt
    self.tc = tc

class Hausdorff95:
  def __init__(self, et, wt, tc):
    self.et = et
    self.wt = wt
    self.tc = tc   

class Team:
    def __init__(self, name, dice, sensitivity, specificity, hausdorff95):
        self.name = name
        self.dice = dice
        self.sensitivity = sensitivity
        self.specificity = specificity
        self.hausdorff95 = hausdorff95

    def print(self):
        print("Name:", self.name)

        print("Dice ET:", self.dice.et)
        print("Dice WT:", self.dice.wt)
        print("Dice TC:", self.dice.tc)

        print("Sensitivity ET:", self.sensitivity.et)
        print("Sensitivity WT:", self.sensitivity.wt)
        print("Sensitivity TC:", self.sensitivity.tc)

        print("Specificity ET:", self.specificity.et)
        print("Specificity WT:", self.specificity.wt)
        print("Specificity TC:", self.specificity.tc)
        
        print("Hausdorff95 ET:", self.hausdorff95.et)
        print("Hausdorff95 WT:", self.hausdorff95.wt)
        print("Hausdorff95 TC:", self.hausdorff95.tc)

    def serialize(self):
        return {
            "name": self.name,
            "dice_et": self.dice.et,
            "dice_wt": self.dice.wt,
            "dice_tc": self.dice.tc,
            "sensitivity_et": self.sensitivity.et,
            "sensitivity_wt": self.sensitivity.wt,
            "sensitivity_tc": self.sensitivity.tc,
            "specificity_et": self.specificity.et,
            "specificity_wt": self.specificity.wt,
            "specificity_tc": self.specificity.tc,
            "hausdorff95_et": self.hausdorff95.et,
            "hausdorff95_wt": self.hausdorff95.wt,
            "hausdorff95_tc": self.hausdorff95.tc
        }
