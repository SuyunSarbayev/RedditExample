package kz.suyun.redditexample.view.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.suyun.redditexample.data.GithubUser
import kz.suyun.redditexample.databinding.ViewholderPostsBinding

class PostsAdapter(var users: List<GithubUser>): RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var binding: ViewholderPostsBinding = ViewholderPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.initiateBindData(users.get(position))
    }

    override fun getItemCount(): Int { return users.size }
}