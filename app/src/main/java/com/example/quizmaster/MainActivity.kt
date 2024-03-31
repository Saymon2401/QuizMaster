package com.example.quizmaster

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var question: TextView
    lateinit var group: RadioGroup
    lateinit var answer1: RadioButton
    lateinit var answer2: RadioButton
    lateinit var answer3: RadioButton
    lateinit var answer4: RadioButton
    lateinit var next: Button
    lateinit var trueAnswer: TextView
    lateinit var falseAnswer: TextView
    lateinit var countQues: TextView
    var questionList = listOf(
        Question(
            "Сколько костей у акулы",
            listOf("14","21","0","8"),
            2
        ),
        Question(
            "Сколько сердец у осьминога",
            listOf("4","3","2","1"),
            1
        ),
        Question(
            "Какое самое твердое вещество на земле",
            listOf("Алмаз","Золото","Железо","Алюминий"),
            0
        ),
        Question(
            "Сколько костей у Человека",
            listOf("156","256","206","103"),
            2
        ),
        Question(
            "Где расположены самые маленькие кости в организме",
            listOf("Пальцы","Нос","Зубы","Ухо"),
            3
        ),
        Question(
            "Сколько зубов у взрослого человека",
            listOf("30","31","32","33"),
            2
        ),
        Question(
            "Где на теле человека больше всего потовых желез",
            listOf("Ладоши","Стопа","Голень","Предплечья"),
            1
        ),
        Question(
            "Сколько стран на земле",
            listOf("193","203","185","221"),
            0
        ),
        Question(
            "В каком году Начался 2 мировая война",
            listOf("1941","1940","1939","1945"),
            2
        ),
        Question(
            "Какая религия была первой в мире?",
            listOf("Ислам","Будизм","Християнство","Иудаизм"),
            1
        ),
    )
    var countId = 0
    var trueA = 0
    var falseA = 0
    var countQ = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question = findViewById(R.id.question)
        group = findViewById(R.id.group)
        answer1 = findViewById(R.id.answer1)
        answer2 = findViewById(R.id.answer2)
        answer3 = findViewById(R.id.answer3)
        answer4 = findViewById(R.id.answer4)
        next = findViewById(R.id.btn)
        trueAnswer = findViewById(R.id.trueA)
        falseAnswer = findViewById(R.id.falseA)
        countQues = findViewById(R.id.countQuestion)

        nextQuestion(countId)

        next.setOnClickListener {
            if (group.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Пожалуйста, выберите ответ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val findOptionIn = group.checkedRadioButtonId
            val findRadioBut = findViewById<RadioButton>(findOptionIn)
            val findOptionText = findRadioBut.text.toString()

            if (correctAnswer(findOptionText)){
                Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
                trueA++
            }else{
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                falseA++
            }
            group.clearCheck()
            trueAnswer.text = trueA.toString()
            falseAnswer.text = falseA.toString()
            countId++
            if (countId < questionList.size){
                nextQuestion(countId)
            }else{
                Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()
                 countId = 0
                 trueA = 0
                 falseA = 0
                 countQ = 0
                 countQ--
            }
            countQ++
            countQues.text = countQ.toString()
        }
    }

    fun nextQuestion(index:Int){
        val ques = questionList[index]
        question.text = ques.questionText
        answer1.text = ques.options[0]
        answer2.text = ques.options[1]
        answer3.text = ques.options[2]
        answer4.text = ques.options[3]
    }

    fun correctAnswer(findOptionText:String):Boolean{
        val ques = questionList[countId]
        val correctAnswerIndex = ques.correctAnswerIndex
        return findOptionText == ques.options[correctAnswerIndex]
    }
}
