package com.example.testapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.ActivityQuestionPageBinding
import kotlin.random.Random

class QuestionPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionPageBinding
    private lateinit var math : String
    private lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupTimer()
    }

    fun setupBinding() {
        binding = ActivityQuestionPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun setupTimer() {
        object : CountDownTimer(10 * 1000, 1000) {
            override fun onTick(p0: Long) {
                binding.remainingTime.text = "Süre: ${(p0 / 1000)}"
            }

            override fun onFinish() {
                binding.remainingTime.text = "Süre bitti"
                val intent = Intent(applicationContext, EndActivity::class.java)
                startActivity(intent)
            }

        }.start()
    }

    fun makingMath(): String {
        var firstNumber = Random.nextInt(0, 101)
        var secondNumber = Random.nextInt(0, 101)

        var process = Random.nextInt(1, 5)

        return processWhen(process, firstNumber, secondNumber)
    }

    fun processWhen(process: Int, firstNumber: Int, secondNumber: Int): String {
        var result = "0"

        when (process) {
            1 -> {
                result = Question.topla(firstNumber, secondNumber).toString()
                binding.soruText.text = firstNumber.toString() + " + " + secondNumber
            }
            2 -> {
                result = Question.cikar(firstNumber, secondNumber).toString()
                binding.soruText.text = firstNumber.toString() + " - " + secondNumber
            }
            3 -> {
                result = Question.carp(firstNumber, secondNumber).toString()
                binding.soruText.text = firstNumber.toString() + " * " + secondNumber
            }
            4 -> {
                result = Question.bol(firstNumber, secondNumber).toString()
                binding.soruText.text = firstNumber.toString() + " / " + secondNumber
            }
        }

        return result
    }

    fun nextButtonClick(view: View) {
        shared = this.getSharedPreferences("com.example.testapp", MODE_PRIVATE)
        var correctAnsNumber = 0;
        var wrongAnsNumber = 0;
        var isEqual = isMathEqualToInput()

        if (isEqual) {
            Toast.makeText(this@QuestionPageActivity, "Cevabınız doğru!", Toast.LENGTH_LONG).show()
            correctAnsNumber++;
        } else {
            Toast.makeText(this@QuestionPageActivity, "Cevabınız yanlış!", Toast.LENGTH_LONG).show()
            wrongAnsNumber++;
        }

        shared.edit().putString("correctAnsNum", correctAnsNumber.toString()).apply()
        shared.edit().putString("wrongAnsNum", wrongAnsNumber.toString()).apply()
    }

    fun isMathEqualToInput() : Boolean {
        var input = binding.inputAnswer.text.toString()
        math = makingMath()

        if(!input.equals(""))
            return input.equals(math)

        return false
    }
}