package kz.suyun.redditexample.view.base.fragments

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>: Fragment() {

    var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = initializeBinding<T>(inflater, container)
        return rootView
    }

    abstract fun initializeLayout(): Int

    abstract fun <T> initializeBinding(inflater: LayoutInflater, container: ViewGroup?): View
}