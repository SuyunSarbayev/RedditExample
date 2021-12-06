package kz.suyun.redditexample.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("character")
    fun requestCharacters(@Query("page") page: Int): Call<PagedResponse<Character>>
}