from flask import Flask
from flask import jsonify
import data_repository

app = Flask(__name__)

@app.route("/")
def index():
    return "Welcome to the index page"

@app.route("/ranking")
def ranking():
    return jsonify({'usersList': data_repository.getAllTeamsEndpoint()})

@app.route("/top10")
def top10():
    return jsonify({'top10List': data_repository.get10BestTeams()})

@app.route("/myranking")
def myRanking():
    return data_repository.myModelRankingNumber()
