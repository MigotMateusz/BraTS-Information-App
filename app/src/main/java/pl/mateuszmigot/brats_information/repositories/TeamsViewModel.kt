package pl.mateuszmigot.brats_information.repositories


import android.util.Log
import pl.mateuszmigot.brats_information.models.Model
import pl.mateuszmigot.brats_information.models.ModelRanking
import pl.mateuszmigot.brats_information.models.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel {

    lateinit var teams: MutableList<Team>
    lateinit var top10teams: MutableList<Team>
    lateinit var myModelRanking: ModelRanking

    fun loadAllTeams() {
        teams = mutableListOf()
        var teamList: MutableList<Team>
        val call = TeamsApi.retrofitService.getAllTeams()
        call.enqueue(object : Callback<MutableList<Team>> {
            override fun onResponse(
                call: Call<MutableList<Team>>?,
                response: Response<MutableList<Team>>
            ) {
                if (!response.isSuccessful) {
                    return
                }
                teamList = response.body()!!
                if (teamList.isNotEmpty()) {
                    teams.clear()
                    teams.addAll(teamList)
                }
            }

            override fun onFailure(call: Call<MutableList<Team>>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    fun loadTop10Teams() {
        top10teams = mutableListOf()
        var teamList: MutableList<Team>
        val call = TeamsApi.retrofitService.getTop10Teams()
        call.enqueue(object : Callback<MutableList<Team>> {
            override fun onResponse(
                call: Call<MutableList<Team>>?,
                response: Response<MutableList<Team>>
            ) {
                if (!response.isSuccessful) {
                    return
                }
                teamList = response.body()!!
                if (teamList.isNotEmpty()) {
                    top10teams.clear()
                    top10teams.addAll(teamList)
                }
            }

            override fun onFailure(call: Call<MutableList<Team>>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    fun loadMyModelRanking() {
        var ranking: ModelRanking
        val call = TeamsApi.retrofitService.getMyModelRanking()
        call.enqueue(object : Callback<ModelRanking> {
            override fun onResponse(
                call: Call<ModelRanking>?,
                response: Response<ModelRanking>
            ) {
                if (!response.isSuccessful) {
                    return
                }
                ranking = response.body()!!
                myModelRanking = ranking
            }

            override fun onFailure(call: Call<ModelRanking>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
            }
        })
    }
}