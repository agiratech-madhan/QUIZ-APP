package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


class ResultActivity : AppCompatActivity() {
    private var mUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        mUserName = intent.getStringExtra("User-Name")

        Log.d("UserNamess", mUserName.toString())

        var textValuename: TextView = findViewById(R.id.tv_name)
        val scoreValue: TextView = findViewById(R.id.tv_score)
        val btnFinish: TextView = findViewById(R.id.btn_finish)
        val value = intent.getStringExtra(Constants.USERNAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        scoreValue.text = "Your Score is $correctAnswer out of $totalQuestions"
        textValuename.text = value.toString()
        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}