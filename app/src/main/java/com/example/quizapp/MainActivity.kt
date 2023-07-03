package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizapp.R.id

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton = findViewById<Button>(id.start_btn)
        val editTextName = findViewById<EditText>(id.edit_text)
        startButton.setOnClickListener {

            if (editTextName.text.isEmpty()) {
                Toast.makeText(this, "Please Type Something", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USERNAME, editTextName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}