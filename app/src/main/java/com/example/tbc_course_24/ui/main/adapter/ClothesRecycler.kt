package com.example.tbc_course_24.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_24.databinding.GridViewBinding
import com.example.tbc_course_24.domain.model.ClothesModel
import com.example.tbc_course_24.extensions.setImage

typealias onClick = (clothes: ClothesModel.ClothesModelItem) -> Unit

class ClothesRecycler:ListAdapter<ClothesModel.ClothesModelItem,ClothesRecycler.ViewHolder>(DiffUtilCallback()) {

    lateinit var onClick: onClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: GridViewBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var activeClothes: ClothesModel.ClothesModelItem


        fun bind(){
            activeClothes = getItem(adapterPosition)

            binding.apply {
                appCompatImageView.setImage(activeClothes.cover!!)
                titleTextView.text = activeClothes.title
                priceTextView.text = activeClothes.price
                if (activeClothes.liked == true){
                    heartLogo.visibility = View.VISIBLE
                }
                root.setOnClickListener {
                    onClick(
                        activeClothes
                    )
                }

            }
        }

    }


    class DiffUtilCallback:DiffUtil.ItemCallback<ClothesModel.ClothesModelItem>() {
        override fun areItemsTheSame(
            oldItem: ClothesModel.ClothesModelItem,
            newItem: ClothesModel.ClothesModelItem
        ): Boolean {
            return oldItem.price == newItem.price
        }

        override fun areContentsTheSame(
            oldItem: ClothesModel.ClothesModelItem,
            newItem: ClothesModel.ClothesModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }


}