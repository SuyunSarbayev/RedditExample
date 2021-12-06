package kz.suyun.redditexample.view.posts

import kz.suyun.redditexample.data.Api
import kz.suyun.redditexample.data.GithubUser
import retrofit2.Call

class PostsRepository(private val api: Api) {
    fun initiateRequestPosts(): Call<List<GithubUser>> {
        return api.requestGetPosts()
    }
}