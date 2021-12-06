package kz.suyun.redditexample.view.posts

import androidx.recyclerview.widget.DiffUtil
import kz.suyun.redditexample.data.GithubUser

class UsersDiffUtil(oldList: MutableList<GithubUser>, newList: MutableList<GithubUser>): DiffUtil.Callback() {

    var oldList: List<GithubUser>
    var newList: List<GithubUser>

    init {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}