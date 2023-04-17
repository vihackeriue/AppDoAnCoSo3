package com.example.appdoancoso3.adapter

import android.provider.ContactsContract
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.ListItemListworkBinding
import com.example.appdoancoso3.model.DetailWorkModel
import com.example.appdoancoso3.model.WorkDetailViewModel
import com.example.appdoancoso3.model.WorkModel
import kotlin.random.Random


class HomeListWorkAdapter(private val list: ArrayList<DetailWorkModel>, val addWorkClickListener:AddWorkClickListener ): RecyclerView.Adapter<HomeListWorkAdapter.ViewHolder>(){


    interface AddWorkClickListener : AdapterView.OnItemLongClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick( position: Int,cardView: CardView)
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
            holder.binding.cardLayout.setOnClickListener {
                    addWorkClickListener.onItemClick(position)
            }
            holder.binding.cardLayout.setOnLongClickListener{
                addWorkClickListener.onItemLongClick(position, binding.cardLayout)
                true
            }
        }

    }

    fun randomColor(): Int {
        val  list = ArrayList<Int>()
        list.add(R.color.WorkColor1)

        val seed = System.currentTimeMillis().toInt()
        val randomIndext = Random(seed).nextInt(list.size)
        return list[randomIndext]
    }
//    fun updateList(newList : List<DetailWorkModel>){
//        list.clear()
//        for (item in newList){
//            list.add(DetailWorkModel(item.ID, item.title, item.date_created, item.time_hours, item.time_minute,item.content,item.status, item.IDWorks))
//            notifyDataSetChanged()
//        }
//
//
////        NotesList.clear()
////        NotesList.addAll(fullList)
//
//
//
//    }


}