package com.example.androidinternshippart3.manager.list

import androidx.recyclerview.widget.DiffUtil
import com.example.androidinternshippart3.database.users.Users

class DiffUtilCallback
    : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(
            oldItem: Users,
            newItem: Users
    ): Boolean {
        return oldItem.usersId == newItem.usersId
    }

    override fun areContentsTheSame(
            oldItem: Users,
            newItem: Users): Boolean {
        return oldItem == newItem
    }
}

