package com.example.appdoancoso3.list_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdoancoso3.R
import com.example.appdoancoso3.databinding.ActivityWorkDetailBinding

class WorkDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}