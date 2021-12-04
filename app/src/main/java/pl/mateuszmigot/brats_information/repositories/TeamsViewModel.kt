package pl.mateuszmigot.brats_information.repositories


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.mateuszmigot.brats_information.models.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class TeamsViewModel {
    val allTeams: MutableLiveData<MutableList<Team>> by lazy {
        MutableLiveData<MutableList<Team>>().also {
            loadAllTeams()
        }
    }

    lateinit var teams: MutableList<Team>

    fun loadAllTeams(): MutableList<Team> {
        teams = mutableListOf()
        var teamList = mutableListOf<Team>()
        val call = TeamsApi.retrofitService.getAllTeams()
        call.enqueue(object : Callback<MutableList<Team>> {
            override fun onResponse(
                call: Call<MutableList<Team>>?,
                response: Response<MutableList<Team>>
            ) {
                if (!response.isSuccessful()) {
                    return
                }
                Log.i("Hi123", response.body().toString())
                teamList = response.body()!!
                if (teamList.isNotEmpty()) {
                    teams.clear()
                    teams.addAll(teamList)
                }
                Log.i("Hi12356789", teams.toString())
            }

            override fun onFailure(call: Call<MutableList<Team>>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
            }
        })
        Log.i("Hi123567", teamList.toString())
        Log.i("Hi12356789", teams.toString())
        return teamList
    }

    fun getAllTeamsMock(): MutableList<Team> {
        val t1 = Team(
            "test1", .0, 0.0, .0, .0, .0, .0, .0, .0, .0, .0,
            .0, .0
        )
        val t2 = Team(
            "test2", .0, 0.0, .0, .0, .0, .0, .0, .0, .0, .0,
            .0, .0
        )
        val t3 = Team(
            "test3", .0, 0.0, .0, .0, .0, .0, .0, .0, .0, .0,
            .0, .0
        )
        return mutableListOf(t1, t2, t3)
    }


}