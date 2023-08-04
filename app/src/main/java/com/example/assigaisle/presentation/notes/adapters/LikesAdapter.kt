package com.example.assigaisle.presentation.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.assigaisle.data.models.LikesProfile
import com.example.assigaisle.databinding.ItemLikeLayoutBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class LikesAdapter() :
    ListAdapter<LikesProfile, LikesAdapter.LikesViewHolder>(
        object : DiffUtil.ItemCallback<LikesProfile>() {
            override fun areItemsTheSame(oldItem: LikesProfile, newItem: LikesProfile): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LikesProfile, newItem: LikesProfile): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    ) {
    inner class LikesViewHolder(private val binding: ItemLikeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: LikesProfile) {
            Glide.with(binding.root.context)
                .load(profile.avatar)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
                .into(binding.ivPhoto)

            binding.tvName.text= profile.firstName
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val binding = ItemLikeLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LikesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}