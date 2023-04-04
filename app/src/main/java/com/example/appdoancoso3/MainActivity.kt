package com.example.appdoancoso3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdoancoso3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardListWork.setOnClickListener{
            intent = Intent(this, ListWorkActivity::class.java)
            startActivity(intent)
        }
        binding.cardRing.setOnClickListener{
            intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }
        binding.cardTimer.setOnClickListener {
            intent = Intent(this, CountdownTimerActivity::class.java)
            startActivity(intent)
        }
        binding.cardLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}