package kz.suyun.redditexample.view.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.suyun.redditexample.data.Character
import kz.suyun.redditexample.data.PagedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel(val charactersRepository: CharactersRepository): ViewModel(), Callback<PagedResponse<Character>> {

    var ioScope = Dispatchers.IO
    var uiScope = Dispatchers.Main

    var usersLiveData = MutableLiveData<List<Character>>()

    var loaderLiveData = MutableLiveData<Boolean>()

    fun initiateRequestCharacters(pageNumber: Int){
        viewModelScope.launch {
            charactersRepository.initiateRequestCharactersNormal(pageNumber).enqueue(this@CharacterViewModel)
        }
    }

    override fun onResponse(
        call: Call<PagedResponse<Character>>,
        response: Response<PagedResponse<Character>>
    ) {
        loaderLiveData.postValue(false)
        if(response.isSuccessful){ usersLiveData.postValue(response.body()?.results) }
    }

    override fun onFailure(call: Call<PagedResponse<Character>>, t: Throwable) {
        loaderLiveData.postValue(false)
    }
}