package com.example.appdoancoso3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.ListItemHistoryBinding
import com.example.appdoancoso3.model.WorkModel
import kotlin.random.Random

class HistoryListWorkAdapter(private var list: ArrayList<WorkModel>): RecyclerView.Adapter<HistoryListWorkAdapter.viewHolder>() {

    inner class viewHolder(val binding: ListItemHistoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ListItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.titleHistory.text = this.Title
                binding.dateHistory.text = this.day_created
            }
        }
        holder.binding.cardLayoutHistory.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

    }

    fun randomColor(): Int {
        val  list = ArrayList<Int>()
        list.add(R.color.white)

        val seed = System.currentTimeMillis().toInt()
        val randomIndext = Random(seed).nextInt(list.size)
        return list[randomIndext]
    }
}