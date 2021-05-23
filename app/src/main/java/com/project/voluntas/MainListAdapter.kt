package com.project.voluntas

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.voluntas.databinding.ListviewItemBinding
import java.sql.DataTruncation
import com.project.voluntas.Data as VoluntasData


class MainListAdapter(data1: ArrayList<com.project.voluntas.Data>) :RecyclerView.Adapter<Holder>() {

        val listData = data1
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

    fun additem(title: String, work: String, date: String, key: String) {

        val item = com.project.voluntas.Data(title, work, date, key)
        item.settitle(title)
        item.setdate(date)
        item.setwork(work)
        item.setkey(key)
        listData.add(item)


    }


}

    class Holder(val binding: ListviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun setRecycler(data: VoluntasData){
            binding.ListViewItemTitle.text = "${data.gettitle()}"
            binding.ListViewItemWork.text = "${data.getwork()}"
            binding.ListViewItemDate.text = "게시일 : ${data.getdate()}"

            binding.ListViewItemDetailButton.setOnClickListener {
                val DetailIntent = Intent(itemView?.context, DetailActivity::class.java )
                Log.d("key", "${data.getkey()}")
                DetailIntent.putExtra("key","${data.getkey()}")
                startActivity(itemView.context, DetailIntent, null)
            }

        }
    }








