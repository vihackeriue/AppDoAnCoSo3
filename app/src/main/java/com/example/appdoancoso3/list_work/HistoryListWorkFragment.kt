package com.example.appdoancoso3.list_work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdoancoso3.R
import com.example.appdoancoso3.adapter.HistoryListWorkAdapter
import com.example.appdoancoso3.database.MWorkDBHelper
import com.example.appdoancoso3.databinding.FragmentHistoryListWorkBinding
import com.example.appdoancoso3.databinding.FragmentHomelistworkBinding
import com.example.appdoancoso3.model.WorkModel


class HistoryListWorkFragment : Fragment() {
    private lateinit var list: ArrayList<WorkModel>
    private lateinit var helper: MWorkDBHelper
    private lateinit var binding: FragmentHistoryListWorkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryListWorkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        helper = MWorkDBHelper(requireContext())
        list = arrayListOf<WorkModel>()
//        helper.insertDataWorks("2","22", 1)
        list = helper.getDataWorkHistory()
        var mAdapter = HistoryListWorkAdapter(list)
        binding.rcvHistoryWork.adapter = mAdapter
        binding.rcvHistoryWork.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvHistoryWork.setHasFixedSize(true)
    }

}