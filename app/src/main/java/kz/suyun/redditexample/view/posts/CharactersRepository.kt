package kz.suyun.redditexample.view.posts

import kz.suyun.redditexample.data.Api
import kz.suyun.redditexample.data.Character
import kz.suyun.redditexample.data.PagedResponse
import retrofit2.Call

class CharactersRepository(val api: Api) {

    fun initiateRequestCharactersNormal(page: Int): Call<PagedResponse<Character>> {
        return api.requestCharacters(page)
    }
}