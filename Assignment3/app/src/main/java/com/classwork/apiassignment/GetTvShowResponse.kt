package com.classwork.apiassignment


import com.google.gson.annotations.SerializedName

data class GetTvShowResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvShow: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)