package kz.suyun.redditexample.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.suyun.redditexample.R
import kz.suyun.redditexample.data.Character
import kz.suyun.redditexample.databinding.FragmentPostsBinding
import kz.suyun.redditexample.view.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment: BaseFragment<FragmentPostsBinding>() {

    val viewModel by viewModel<CharacterViewModel>()

    var characters: MutableList<Character> = mutableListOf()

    var adapter: CharacterAdapter = CharacterAdapter(characters)

    var binding: FragmentPostsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBindingData()
        initializeObservers()
        initializeListeners()
        initiateRequestPosts()
    }

    fun initializeBindingData(){
        binding?.adapter = adapter
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bind:adapter")
        fun initializeAdapter(recyclerView: RecyclerView, adapter: CharacterAdapter){
            recyclerView.adapter = adapter
        }
    }

    fun initiateUpdateAdapter(oldList: List<Character>, newUsers: List<Character>){
        val diffResult = DiffUtil.calculateDiff(CharacterComparator(oldList.toMutableList(), newUsers.toMutableList()))
        adapter.characters = newUsers
        diffResult.dispatchUpdatesTo(adapter)
    }

    fun initializeListeners(){
        binding?.swiperefreshFragmentPostsRefresh?.setOnRefreshListener {
            initiateClearData()
            initiateRequestPosts()
        }
    }

    fun initiateClearData(){
        var newList = mutableListOf<Character>()
        newList.addAll(adapter.characters)
        newList.clear()
        initiateUpdateAdapter(adapter.characters, newList)
    }

    fun initiateRequestPosts(){
        viewModel.initiateRequestCharacters(1)
    }

    fun initializeObservers(){
        viewModel.usersLiveData.observe(viewLifecycleOwner, {
            initiateUpdateAdapter(adapter.characters, it)
        })
        viewModel.loaderLiveData.observe(viewLifecycleOwner, {
            binding?.swiperefreshFragmentPostsRefresh?.isRefreshing = it
        })
    }

    override fun initializeLayout(): Int { return R.layout.fragment_posts }

    override fun <T> initializeBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, initializeLayout(), container, false)
        return binding!!.root
    }
}