package com.example.testapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.databinding.ActivityEndBinding
import com.example.testapp.databinding.ActivityMainBinding

class EndActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndBinding
    private lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        loadInfoFromQuestionPage()
    }

    fun setupBinding() {
        binding = ActivityEndBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun loadInfoFromQuestionPage() {
        shared = this.getSharedPreferences("com.example.testapp", MODE_PRIVATE)

        var correctAns = shared.getString("correctAnsNum", "null")
        var wrongAns = shared.getString("wrongAnsNum", "null")

        if(correctAns == null) {
            binding.correctAnswerInfoText.text = "Doğru: 0"
        } else {
            binding.correctAnswerInfoText.text = "Doğru: $correctAns"
        }

        if(wrongAns == null) {
            binding.wrongAnswerInfoText.text = "Yanlış: 0"
        } else {
            binding.wrongAnswerInfoText.text = "Yanlış: $wrongAns"
        }

    }

}