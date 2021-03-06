package pl.mateuszmigot.brats_information.repositories

import pl.mateuszmigot.brats_information.models.ModelRanking
import pl.mateuszmigot.brats_information.models.Team
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "http://10.0.2.2:5000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface TeamsApiService {
    @GET("ranking")
    fun getAllTeams(): Call<MutableList<Team>>

    @GET("top10")
    fun getTop10Teams(): Call<MutableList<Team>>

    @GET("myranking")
    fun getMyModelRanking(): Call<ModelRanking>
}

object TeamsApi {
    val retrofitService: TeamsApiService by lazy {
        retrofit.create(TeamsApiService::class.java)
    }
}