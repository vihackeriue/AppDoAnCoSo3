package com.example.appdoancoso3.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.ListItemListworkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import kotlin.random.Random


class HomeListWorkAdapter(private val list: ArrayList<DetailWorkModel>): RecyclerView.Adapter<HomeListWorkAdapter.ViewHolder>(){

    lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class ViewHolder( val binding: ListItemListworkBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ListItemListworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.titleListwork.text =this.title
                binding.contentListwork.text =this.content
                binding.dateListwork.text =this.date_created.toString()
            }
            holder.binding.cardLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))
        }
    }

    fun randomColor(): Int {
        val  list = ArrayList<Int>()
        list.add(R.color.WorkColor1)
        list.add(R.color.WorkColor2)
        list.add(R.color.WorkColor3)
        list.add(R.color.WorkColor4)
        list.add(R.color.WorkColor5)
        list.add(R.color.WorkColor6)
        val seed = System.currentTimeMillis().toInt()
        val randomIndext = Random(seed).nextInt(list.size)
        return list[randomIndext]
    }


}