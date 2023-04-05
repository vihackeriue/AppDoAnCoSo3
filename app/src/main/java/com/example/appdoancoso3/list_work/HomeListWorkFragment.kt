package com.example.appdoancoso3.list_work

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.FragmentHomelistworkBinding


class HomeListWorkFragment : Fragment() {

    private lateinit var binding: FragmentHomelistworkBinding

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
       binding.addWork.setOnClickListener {
           val intent = Intent(activity, AddListWorkActivity::class.java)
           startActivity(intent)

       }
   }
}