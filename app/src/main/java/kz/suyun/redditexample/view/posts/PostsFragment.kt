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
import kz.suyun.redditexample.data.GithubUser
import kz.suyun.redditexample.databinding.FragmentPostsBinding
import kz.suyun.redditexample.view.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment: BaseFragment<FragmentPostsBinding>() {

    val viewModel by viewModel<PostsViewModel>()

    var users: MutableList<GithubUser> = mutableListOf()

    var adapter: PostsAdapter = PostsAdapter(users)

    var binding: FragmentPostsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBindingData()
        initializeObservers()
        initiateRequestPosts()
        initiateRemoveItem()
    }

    fun initializeBindingData(){
        binding?.adapter = adapter
    }

    companion object{
        @JvmStatic
        @BindingAdapter("bind:adapter")
        fun initializeAdapter(recyclerView: RecyclerView, adapter: PostsAdapter){
            recyclerView.adapter = adapter
        }
    }

    fun initiateUpdateAdapter(oldList: List<GithubUser>, newUsers: List<GithubUser>){
        val diffResult = DiffUtil.calculateDiff(UsersDiffUtil(oldList.toMutableList(), newUsers.toMutableList()))
        adapter.users = newUsers
        diffResult.dispatchUpdatesTo(adapter)
    }

    fun initiateRequestPosts(){
        viewModel.initiateRequestPosts()
    }

    fun initializeObservers(){
        viewModel.usersLiveData.observe(viewLifecycleOwner, {
            initiateUpdateAdapter(adapter.users, it)
        })
    }

    fun initiateRemoveItem(){
        binding?.buttonFragmentPostsRemove?.setOnClickListener {
            val newList = mutableListOf<GithubUser>()
            newList.addAll(adapter.users)
            newList.removeAt(1)
            initiateUpdateAdapter(adapter.users, newList)
        }
    }

    override fun initializeLayout(): Int { return R.layout.fragment_posts }

    override fun <T> initializeBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, initializeLayout(), container, false)
        return binding!!.root
    }
}