package com.example.androidinternshippart3.manager.list

import androidx.recyclerview.widget.RecyclerView
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.databinding.ItemUserBinding

class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Users, listener: ((Users) -> Unit)?) {
        binding.user = item
        binding.userContainer.setOnClickListener { listener?.invoke(item) }
    }
}