package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var mUserName: String? = null
    private var acurrentPosition: Int = 1
    private var questionList: ArrayList<Question>? = null
    private var selectedOptionPosition: Int = 0
    private var mCurrentAnswer: Int = 0
    private var progressBar: ProgressBar? = null
    private var progressValue: TextView? = null
    private var image: ImageView? = null
    private var optionA: TextView? = null
    private var questions: TextView? = null
    private var optionB: TextView? = null
    private var optionC: TextView? = null
    private var optionD: TextView? = null
    private var submit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USERNAME)

        progressBar = findViewById(R.id.progress_bar)
        progressValue = findViewById(R.id.progress_value)
        image = findViewById(R.id.question_image)
        questions = findViewById(R.id.question_id)
        optionA = findViewById(R.id.tv_option_one)
        optionB = findViewById(R.id.tv_option_two)
        optionC = findViewById(R.id.tv_option_three)
        optionD = findViewById(R.id.tv_option_four)
        submit = findViewById(R.id.answer_submit)
        questionList = Constants.getQuestions()
        setQuestions()
        optionA?.setOnClickListener(this)
        optionB?.setOnClickListener(this)
        optionC?.setOnClickListener(this)
        optionD?.setOnClickListener(this)
        submit?.setOnClickListener(this)
    }

    private fun setQuestions() {
        defaultOptionsView()

        val question: Question = questionList!![acurrentPosition - 1]
        image?.setImageResource(question.image)
        progressBar?.progress = acurrentPosition
        progressValue?.text = "${acurrentPosition - 1}/${progressBar?.max}"
        questions?.text = question.questions
        optionA?.text = question.optionA
        optionB?.text = question.optionB
        optionC?.text = question.optionC
        optionD?.text = question.optionD
        if (acurrentPosition == questionList!!.size) {
            submit?.text = "FINESH"
        } else {
            submit?.text = "SUBMIT"

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_bg
        )
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        optionA?.let {
            options.add(0, it)
        }
        optionB?.let {
            options.add(1, it)
        }
        optionC?.let {
            options.add(2, it)
        }
        optionD?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                optionA?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                optionB?.let {
                    selectedOptionView(it, 2)
                }

            }

            R.id.tv_option_three -> {
                optionC?.let {
                    selectedOptionView(it, 3)
                }

            }

            R.id.tv_option_four -> {
                optionD?.let {
                    selectedOptionView(it, 4)
                }

            }
            R.id.answer_submit -> {
                if (selectedOptionPosition == 0) {

                    acurrentPosition++

                    when {

                        acurrentPosition <= questionList!!.size -> {

                            setQuestions()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USERNAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCurrentAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionList?.size)
                            startActivity(intent)
                            finish()
//                            Toast.makeText(this, "You have successfully completed the quiz.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val question = questionList?.get(acurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCurrentAnswer++

                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (acurrentPosition == questionList!!.size) {
                        submit?.text = "FINISH"
                    } else {
                        submit?.text = "GO TO NEXT QUESTION"
                    }

                    selectedOptionPosition = 0
                }

            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                optionA?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                optionB?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                optionC?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                optionD?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}
