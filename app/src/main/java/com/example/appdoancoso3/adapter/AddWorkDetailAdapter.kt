package com.example.appdoancoso3.adapter


import android.app.TimePickerDialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.ItemAddWorkListworkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class AddWorkDetailAdapter(private var list: ArrayList<DetailWorkModel>, var addDetailWorkInterface: AddDetailWorkInterface) : RecyclerView.Adapter<AddWorkDetailAdapter.ViewHolder>() {

    interface  AddDetailWorkInterface{
        fun onChooseItem(position: Int)


    }



    inner class ViewHolder( val binding: ItemAddWorkListworkBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemAddWorkListworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.tvTitleWorkListwork.text = this.title
                binding.tvCountHour.text = this.time_hours
                binding.tvCountMinute.text = this.time_minute
                binding.tvContentWorkListwork.text = this.content

            }
            holder.binding.cardItemAdddetailwork.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))
        }
    }
    fun randomColor(): Int {
        val  list = ArrayList<Int>()
        list.add(R.color.AddWorkColor1)
        list.add(R.color.AddWorkColor2)
        list.add(R.color.AddWorkColor3)

        val seed = System.currentTimeMillis().toInt()
        val randomIndext = Random(seed).nextInt(list.size)
        return list[randomIndext]
    }
}