package com.example.onlineshoppro.Activity

import android.content.Intent
import android.os.Bundle
import com.example.onlineshoppro.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private var _binding: ActivityIntroBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startButton.setOnClickListener{
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}