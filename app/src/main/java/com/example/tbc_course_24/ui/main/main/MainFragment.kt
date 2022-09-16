package com.example.tbc_course_24.ui.main.main


import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_course_24.R
import com.example.tbc_course_24.common.Resource
import com.example.tbc_course_24.databinding.FragmentMainBinding
import com.example.tbc_course_24.ui.main.adapter.ClothesRecycler
import com.example.tbc_course_24.ui.main.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Cache


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {



    private val viewModel: MainViewModel by viewModels()

    private val adapterActive by lazy {
        ClothesRecycler()
    }


    override fun start() {

        viewModel.getClothes()

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.clothes.collect{
                    adapterActive.submitList(it.data)
                    adapterActive.onClick = {
                        Snackbar.make(requireView(),"${it.title} " + getString(R.string.succ),Snackbar.LENGTH_SHORT).show()
                    }
                    if (it.status == Resource.Status.ERROR){
                        Snackbar.make(requireView(),getString(R.string.internet),Snackbar.LENGTH_SHORT).show()
                    }

                }

            }
        }



    }

    override fun initRecycler() {
        binding?.activeRecycler?.adapter = adapterActive
    }
}