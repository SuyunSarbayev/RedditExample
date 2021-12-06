package kz.suyun.redditexample.view.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.suyun.redditexample.R
import kz.suyun.redditexample.data.Character

class CharacterAdapter(var characters: List<Character>): RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_characters, parent, false)
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        return holder.initiateBindData(characters.get(position))
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}