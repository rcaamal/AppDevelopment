package com.classwork.apiassignment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object TvShowRepository {

    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(Api::class.java)
    }
        fun getPopularTvShows(
            page: Int = 1,
            onSuccess: (tvShow: List<TvShow>) -> Unit,
            onError: () -> Unit
        ){
            api.getPopularTvShows(page = page)
                .enqueue(object : Callback<GetTvShowResponse> {
                    override fun onResponse(
                        call: Call<GetTvShowResponse>,
                        response: Response<GetTvShowResponse>
                    ){
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) { onSuccess.invoke(responseBody.tvShow)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke() }
                    }
                    override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }


        fun getTopRatedTvShows(
            page: Int = 1,
            onSuccess: (tvShow: List<TvShow>) -> Unit,
            onError: () -> Unit
        ){
            api.getTopRatedTvShows(page = page)
                .enqueue(object : Callback<GetTvShowResponse> {
                    override fun onResponse(
                        call: Call<GetTvShowResponse>,
                        response: Response<GetTvShowResponse>
                    ){
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) { onSuccess.invoke(responseBody.tvShow)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke() }
                    }
                    override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }

        fun geOnAirTvShows(
            page: Int = 1,
            onSuccess: (tvShow: List<TvShow>) -> Unit,
            onError: () -> Unit
        ){
            api.geOnAirTvShows(page = page)
                .enqueue(object : Callback<GetTvShowResponse> {
                    override fun onResponse(
                        call: Call<GetTvShowResponse>,
                        response: Response<GetTvShowResponse>
                    ){
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) { onSuccess.invoke(responseBody.tvShow)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke() }
                    }
                    override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }

        fun getAiringTodayTvShows(
            page: Int = 1,
            onSuccess: (tvShow: List<TvShow>) -> Unit,
            onError: () -> Unit ){
            api.getAiringTodayTvShows(page= page)
                .enqueue(object : Callback<GetTvShowResponse>{
                    override fun onResponse(
                        call: Call<GetTvShowResponse>,
                        response: Response<GetTvShowResponse>
                    ) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if(responseBody != null){ onSuccess.invoke(responseBody.tvShow)
                            }else{ onError.invoke()
                            }
                        }else{
                            onError.invoke() }
                    }
                    override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {

                    } })
        }


}