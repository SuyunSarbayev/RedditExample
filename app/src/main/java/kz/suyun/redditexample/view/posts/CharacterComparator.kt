package kz.suyun.redditexample.view.posts

import androidx.recyclerview.widget.DiffUtil
import kz.suyun.redditexample.data.Character

class CharacterComparator(var oldList: MutableList<Character>, var newList: MutableList<Character>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList.get(oldItemPosition).id == newList.get(newItemPosition).id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition)
    }
}