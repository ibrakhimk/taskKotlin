package com.example.taskkotlin.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskkotlin.data.model.Home
import com.example.taskkotlin.databinding.ItemHomeBinding

class HomeAdapter(val onLongClick:(Home)->Unit,val onClick:(Home)->Unit) :
    Adapter<HomeAdapter.HomeViewHolder>() {

    private val homeList = arrayListOf<Home>()
    fun addTasks(home: List<Home>){
        homeList.clear()
        homeList.addAll(home)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeList[position])
    }

    inner class HomeViewHolder(private val binding: ItemHomeBinding) : ViewHolder(binding.root) {
        fun bind(home: Home) {
            binding.tvDesc.text = home.desc
            binding.tvTitle.text = home.title
            itemView.setOnLongClickListener {
                onLongClick(home)
                false
            }
            itemView.setOnClickListener {
                onClick(home)
            }
        }
    }
}