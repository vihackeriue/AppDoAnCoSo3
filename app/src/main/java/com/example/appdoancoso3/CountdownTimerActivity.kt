package com.example.appdoancoso3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdoancoso3.databinding.ActivityCountdownTimerBinding

class CountdownTimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountdownTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountdownTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }
}