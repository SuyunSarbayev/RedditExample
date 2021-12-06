package kz.suyun.redditexample.view.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.suyun.redditexample.data.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel(val postsRepository: PostsRepository): ViewModel(), Callback<List<GithubUser>> {

    var ioScope = Dispatchers.IO
    var uiScope = Dispatchers.Main

    var usersLiveData = MutableLiveData<List<GithubUser>>()

    fun initiateRequestPosts(){
        viewModelScope.launch(ioScope){
            postsRepository.initiateRequestPosts().enqueue(this@PostsViewModel)
        }
    }

    override fun onResponse(call: Call<List<GithubUser>>, response: Response<List<GithubUser>>) {
        if(response.isSuccessful){ usersLiveData.postValue(response.body()) }
    }

    override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {}
}