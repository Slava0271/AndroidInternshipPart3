package com.example.androidinternshippart3.manager.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.databinding.ItemUserBinding

class UserAdapter(private val listener: ((Users) -> Unit)? = null) :
        ListAdapter<Users, UserViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}