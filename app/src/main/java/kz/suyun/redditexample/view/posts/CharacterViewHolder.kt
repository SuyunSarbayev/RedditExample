package kz.suyun.redditexample.view.posts

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.suyun.redditexample.data.Character
import kz.suyun.redditexample.databinding.ViewholderCharactersBinding

class CharacterViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    var binding: ViewholderCharactersBinding? = null

    init { binding = DataBindingUtil.bind(view) }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:loadImage")
        fun initiateLoadAvatar(image: AppCompatImageView, imageUrl: String){
            Picasso.get().load(imageUrl).into(image)
        }
    }

    fun initiateBindData(character: Character){
        binding?.character = character
    }
}