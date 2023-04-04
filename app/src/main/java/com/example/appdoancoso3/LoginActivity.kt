package com.example.appdoancoso3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdoancoso3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.register.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }

}