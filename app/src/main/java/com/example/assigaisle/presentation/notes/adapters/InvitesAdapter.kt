package com.example.assigaisle.presentation.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.assigaisle.data.models.Profile
import com.example.assigaisle.databinding.ItemInviteLayoutBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class InvitesAdapter() :
    ListAdapter<Profile, InvitesAdapter.InvitesViewHolder>(
        object : DiffUtil.ItemCallback<Profile>() {
            override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    ) {
    inner class InvitesViewHolder(private val binding: ItemInviteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            Glide.with(binding.root.context)
                .load(getProfileImage(profile))
                .into(binding.ivProfile)

            binding.tvAgeName.text=profile.generalInformation.firstName + "," + profile.generalInformation.age
        }
    }

    private fun getProfileImage(profile: Profile): String {
        val profile= profile.photos.filter { photo -> photo.selected }
        return profile[0].photo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitesViewHolder {
        val binding = ItemInviteLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return InvitesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvitesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}