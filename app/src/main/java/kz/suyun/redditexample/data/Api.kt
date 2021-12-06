package kz.suyun.redditexample.data

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun requestGetPosts(): Call<List<GithubUser>>
}