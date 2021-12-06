package kz.suyun.redditexample.view.posts

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.suyun.redditexample.R
import kz.suyun.redditexample.data.GithubUser
import kz.suyun.redditexample.databinding.ViewholderPostsBinding

class PostsViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    var binding: ViewholderPostsBinding? = null

    init { binding = DataBindingUtil.bind(view) }

    companion object {
        @JvmStatic
        @BindingAdapter("bind:loadAvatar")
        fun initiateLoadAvatar(userAvatar: AppCompatImageView, avatarUrl: String){
            Picasso.get().load(avatarUrl).into(userAvatar)
        }
    }

    fun initiateBindData(user: GithubUser){
        binding?.user = user
    }
}