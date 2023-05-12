package com.example.quizforstudent

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.quizforstudent.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    // Разметка activity_test относится к данному kotlin классу


    lateinit var binding : ActivityTestBinding


    val progress= arrayOf(R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5)
    // массив, который хранит идентификаторы поинтов. Поинты - обычные CardView

    val choose = Array<Boolean>(4){false}
    // булевый массив. Для определения выбранной картинки(Дальше все описывается)




    //массив, в котором хранится количчество выборов специальностей
    val arrayResult = IntArray(4){0}// 0(Экономика и упр),1(Электр Эконом),2(Бух учет),3(ИСИТ)
    // каждому элементу соотносится специальность.



    var position =0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init(position)
    }


    //обработчик собития кнопки Далее
    fun onClickNext(view: View) {
        // из-за того, что у нас 4 смен картинки, то в условие position >=4
        countResult()
        if(position >=4)
        {
            //меняет надпись картинки на ГОТОВО
            binding.bNext.setText(R.string.bDone)
            giveData()
        }
        else{
            //пока еще у нас есть картинки для опроса, каждый раз поднимаем position на одно значение
            position++
            init(position)
        }

    }
    fun init (position:Int)=with(binding){
        //alpha - атрибут объекта View. Он контролирует степень прозрачности картинки.
        //1.0 - означает картинка НЕпрозрачна на 100%
        imView1.alpha=1.0f
        imView2.alpha=1.0f
        imView3.alpha=1.0f
        imView4.alpha=1.0f

        //переменная position, которая итерируется каждый раз, как мы нажимаем на кнопку Далее
        // определяет, какую по счету катинку (для каждой из 4 специальностей) взять (идентификатор картинок
        // ноходится в классе Constance).
        imView1.setImageResource(Constance.listImageEconomy[position])
        imView2.setImageResource(Constance.listImageElectronicEconomy[position])
        imView3.setImageResource(Constance.listImageAccounting[position])
        imView4.setImageResource(Constance.listImageISIT[position])
    }


    // обработчик события при нажатии на картинку 1(нумерация катинок идет так, как они расположены)
    fun onClickChooseIm1(view: View)=with(binding) {
        // цикл, который перебирает choose и устанавливает значение для всех элементов - false)
        // для чего это дедается: тут картинки меняются на одном экране. Когда ты выбираешь некую картинку,
        // то меняется определенный элемент массива choose с false на true.
        // и для того, чтобы на новый круг понимать, какая картинка была выбрана, нужно чтобы все элементы
        // были false
        choose.forEachIndexed{i,_-> choose[i]=false}


        // похожая ситуация с choose. По механике порграммы, картинка, которую выбрал пользователь
        // становится прозрачной. Но вдруг пользователь перед окончательвм выбором картинки 1 выюирал
        // другой вариант. Из-за этого все картинки кроме 1 становятся не прозрачными
        imView2.alpha=1.0f
        imView3.alpha=1.0f
        imView4.alpha=1.0f
        view.alpha=0.5f

        //так как выбранная картинка 1, то 0(нумерация элементов в массиве начинается с нуля)
        // элемент choose становится - true.
        choose[0]=true
    }

    fun onClickChooseIm2(view: View)=with(binding) {
        choose.forEachIndexed{i,_-> choose[i]=false}
        imView1.alpha=1.0f
        imView3.alpha=1.0f
        imView4.alpha=1.0f
        view.alpha=0.5f
        choose[1]=true

    }
    fun onClickChooseIm3(view: View)=with(binding) {
        choose.forEachIndexed{i,_-> choose[i]=false}
        imView1.alpha=1.0f
        imView2.alpha=1.0f
        imView4.alpha=1.0f
        view.alpha=0.5f
        choose[2]=true
    }
    fun onClickChooseIm4(view: View)=with(binding) {
        choose.forEachIndexed{i,_-> choose[i]=false}
        imView2.alpha=1.0f
        imView3.alpha=1.0f
        imView1.alpha=1.0f
        view.alpha=0.5f
        choose[3]=true
    }



    fun countResult(){
        //описывает поведение шкалы прогресса
    // для этого инициализировался массив progress. Исходя из переменной position, меняется цвет поинтера
    //R.color.colorForProgress_next - путь, по которому находит программа наш цвет, что мы сами создали
        findViewById<CardView>(progress[position]).setCardBackgroundColor(R.color.colorForProgress_next)

        // проходим по choose и если находим элемент который true, то значит этот элемент был выбран пользователем
        choose.forEachIndexed{i,v->
            if(v){ //условие на проверку

                // прибовляем к элементу 1
                arrayResult[i]++
            }
        }
    }

    fun giveData(){

        val intent = Intent(this, ResultActivity::class.java).apply {
            //через Intent передаем наш результативный массив на следуеюее Activity.
            //для того, чтобы поймать информацию на дркгом activity  мы должны устаносить ключ "key"
            putExtra(Constance.key, arrayResult)
        }
        startActivity(intent)
        finish()
    }


}