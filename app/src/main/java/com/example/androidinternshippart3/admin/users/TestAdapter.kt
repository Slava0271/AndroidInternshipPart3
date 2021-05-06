package com.example.androidinternshippart3.admin.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidinternshippart3.R
import kotlinx.android.synthetic.main.test_item.view.*
import kotlin.math.log

class TestAdapter(private val users: ArrayList<String>) :
        RecyclerView.Adapter<TestAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagTV: TextView = view.tagTV as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.test_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = users[position]
        holder.tagTV.text = user

    }

    override fun getItemCount(): Int {
        return users.size
    }
}