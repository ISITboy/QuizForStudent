package com.example.quizforstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizforstudent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Разметка activity_main относится к данному kotlin классу


    // это класс binding. Он упрощает написание кода, благодаря ему есть возмлжность упрощенно обращаться
    // ко всем объектам View. Объекты View - объекты, находящиеся в разметке страницы xml. Использование
    // данной конструкции нужно прописывать в папке build.gradle
    lateinit var binding:ActivityMainBinding


    // onCreate - эта функция относится к жизненному циклу Activity. Она запускается сразу же,
    // когда открывается данное окно в приложении
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // надуваем наш класс binding
        setContentView(binding.root)
    }

    // это обработчик события при нажатии на кнопку
    fun onClickStart(view: View) {
        val i = Intent(this,RegisterActivity::class.java) // класс Intent - в данном случае
        // используется для перехода на другую активность (другое окно приложения). This - контекст,
        // он показвает, что с именно с данной активности нужно перейти в TestActivity активность
        startActivity(i) // функция, которая принимает intent и запускает наше второе Activity
        finish()//закрывает данную Activity
    }


}