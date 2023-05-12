package com.example.quizforstudent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizforstudent.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding : ActivityResultBinding

    var listResult = mutableListOf<String>("","","","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //принимаем наше значение по ключу.
        val arrayResult = intent.getIntArrayExtra(Constance.key)

        outputData(arrayResult)
        addResultInUser()
        uploadData()

    }



// на экране, где результат, мы по формале прописываем проценты
    fun outputData(result: IntArray?)=with(binding){
        resultEconomyText.text = "${formula(result!![0]).toString()} %"
        resultElectronicEconomyText.setText("${formula(result!![1]).toString()} %")
        resultAccountingText.setText("${formula(result!![2]).toString()} %")
        resultISITTExt.setText("${formula(result!![3]).toString()} %")

    }

    fun formula(x:Int) = x*100/5

    //переопределенная функция. Тут мы порписываем, чтобы когда пользователь нажал на КНОПКУ НАЗАД
    // , КОТОРАЯ ЯВЛЯЕТСЯ СТАНДАРТНОЙ В КАЖДОМ ТЕЛЕФОНЕ ТИПО КНОПКА ДОМОЙ, пользователь не закрывал программу,
    // а возвращался на гллавный эеран приложения
    override fun onBackPressed(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
    // обработчк события для кнопки, чтобы аернуться на главный экран
    fun onClickBackMainActivity(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun addResultInUser(){
        USER.economy=binding.resultEconomyText.text.toString()
        USER.electronic_economy=binding.resultElectronicEconomyText.text.toString()
        USER.accounting=binding.resultAccountingText.text.toString()
        USER.isit=binding.resultISITTExt.text.toString()
    }
    fun uploadData(){
        val dateMap = mutableMapOf<String,Any>()
        dateMap[CHILD_ID]= AUTH.currentUser?.uid.toString()
        dateMap[CHILD_EMAIL] = AUTH.currentUser?.email.toString()
        dateMap[CHILD_ECONOMY] = USER.economy
        dateMap[CHILD_electronicEconomy]=USER.electronic_economy
        dateMap[CHILD_ACCOUNTING]=USER.accounting.toString()
        dateMap[CHILD_ISIT]=USER.isit.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(AUTH.currentUser?.uid.toString()).updateChildren(dateMap)
            .addOnCompleteListener{task2->
                if(task2.isSuccessful){
                    Toast.makeText(
                        this@ResultActivity,
                        "Данные внесены в базу данных",
                        Toast.LENGTH_LONG
                    ).show()
                }else{
                    Toast.makeText(this@ResultActivity, "Ошибка!", Toast.LENGTH_LONG).show()
                }

            }

    }

}