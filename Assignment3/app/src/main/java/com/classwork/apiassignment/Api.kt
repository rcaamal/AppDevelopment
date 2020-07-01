package com.classwork.apiassignment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = "eedd8549de6303376d74ffc3a8e95451",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>

    @GET( "tv/top_rated")
    fun getTopRatedTvShows(
        @Query("api_key") apiKey: String = "eedd8549de6303376d74ffc3a8e95451",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>

    @GET("tv/on_the_air")
    fun geOnAirTvShows(
        @Query("api_key") apiKey: String = "eedd8549de6303376d74ffc3a8e95451",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTvShows(
        @Query("api_key") apiKey: String = "eedd8549de6303376d74ffc3a8e95451",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>

}