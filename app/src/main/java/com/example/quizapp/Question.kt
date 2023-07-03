package com.example.quizapp

data class Question(
    val id:Int,
    val questions:String,
    val image:Int,
    val optionA:String,
    val optionB:String,
    val optionC:String,
    val optionD:String,
    val correctAnswer:Int
)
