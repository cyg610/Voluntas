package com.project.voluntas

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.voluntas.databinding.ListviewItemBinding


class MainListAdapter(data1: ArrayList<Data>) :RecyclerView.Adapter<Holder>() {

        var listData = data1
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ListviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val data = listData.get(position)
            holder.setRecycler(data)
        }



    }

    class Holder(val binding: ListviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun setRecycler(data: Data){
            binding.ListViewItemTitle.text = "${data.gettitle()}"
        }
    }








