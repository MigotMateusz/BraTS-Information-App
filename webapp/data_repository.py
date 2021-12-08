import pandas as pd
import classes

tables = pd.read_html("https://www.cbica.upenn.edu/BraTS20/lboardValidation.html")
rankings = tables[-1]

def getAllTeamsModelRanking():
    teams = list()
    for table in rankings[1:].iterrows():
        team_row = table[1]
        name = team_row[0]
        dice = classes.Dice(team_row[3], team_row[4], team_row[5])
        sensitivity = classes.Sensitivity(team_row[6], team_row[7], team_row[8])
        specificity = classes.Specificity(team_row[9], team_row[10], team_row[11])
        hausdorff95 = classes.Hausdorff95(team_row[12], team_row[13], team_row[14])
        if abs(float(dice.et) - 1.0) > 0.00000001:
            teams.append(classes.Team(name, dice, sensitivity, specificity, hausdorff95))

    return teams

def getAllTeamsEndpoint():
    teams = getAllTeamsModelRanking()
    print(teams)
    return [x.serialize() for x in teams]

def get10BestTeams():
    teams = getAllTeamsModelRanking()
    teams.sort(key=lambda x: x.dice.et, reverse=True)
    return [x.serialize() for x in teams[0:10]] 

def myModelRankingNumber():
    teams = getAllTeamsModelRanking()
    counter = 0
    for team in teams:
        if float(team.dice.et) > 0.76838:
            counter = counter + 1

    return serializeMyModelNumbers(counter + 1, len(teams))

def serializeMyModelNumbers(my, all):
    return {
        "myRanking": my,
        "numberOfTeams": all
    }

def myModels():
    return 