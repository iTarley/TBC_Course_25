package com.example.tbc_course_24.ui.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias Inflater<T> = (inflater : LayoutInflater,view:ViewGroup?,attach:Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflater: Inflater<VB>): Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding
    abstract fun start()
    abstract fun initRecycler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflater.invoke(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        start()

    }
}