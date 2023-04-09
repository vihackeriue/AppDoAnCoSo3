package com.example.appdoancoso3.list_work

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdoancoso3.R
import com.example.appdoancoso3.adapter.HomeListWorkAdapter
import com.example.appdoancoso3.database.MWorkDBHelper
import com.example.appdoancoso3.databinding.FragmentHomelistworkBinding
import com.example.appdoancoso3.model.DetailWorkModel


class HomeListWorkFragment : Fragment() {

    private lateinit var binding: FragmentHomelistworkBinding

    private lateinit var helper: MWorkDBHelper
    private lateinit var adapter: HomeListWorkAdapter
    private lateinit var list: ArrayList<DetailWorkModel>
//    private lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomelistworkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StartActivity()
    }
   fun StartActivity(){
       helper= MWorkDBHelper(requireContext())
       list = arrayListOf<DetailWorkModel>()
       showDataWorkDetail()

       binding.addDetailwork.setOnClickListener {
           val intent = Intent(activity, AddListWorkActivity::class.java)
           startActivity(intent)

       }
   }

    private fun showDataWorkDetail() {


//        helper.createAccount("11","11","11")
//        helper.insertDataWorks("11","11",1)
//        helper.insertDataWorkDetail("111","11","111","11","2",0,1)
        list = helper.getDataWorkDetail()
        if(list.size > 0){
            binding.addWork.visibility = View.GONE
            binding.txtLoadingData.visibility = View.GONE
        }
//       list.add(DetailWorkModel(1,"1","11","1",1,1))
        val mAdapter = HomeListWorkAdapter(list)
        binding.recyclerViewListwork.adapter = mAdapter
        binding.recyclerViewListwork.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewListwork.setHasFixedSize(true)


    }

}