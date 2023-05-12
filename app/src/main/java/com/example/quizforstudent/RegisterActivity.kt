package com.example.quizforstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizforstudent.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        initUSER()
    }

    fun onClick_Register(view: View)=with(binding) {

        AUTH = FirebaseAuth.getInstance()

        if (editTextEmail.text.toString().isEmpty() || editTextPassword.text.toString().isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Введите email и пароль", Toast.LENGTH_LONG)
                .show()
        }
        else {
            AUTH.createUserWithEmailAndPassword(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
            ).addOnCompleteListener {
                val uid = AUTH.currentUser?.uid.toString()
                if (it.isSuccessful) {
                    val dateMap = mutableMapOf<String,Any>()
                    dateMap[CHILD_ID]=uid
                    dateMap[CHILD_EMAIL] = AUTH.currentUser?.email.toString()
                    dateMap[CHILD_ECONOMY] = ""
                    dateMap[CHILD_electronicEconomy]=""
                    dateMap[CHILD_ACCOUNTING]=""
                    dateMap[CHILD_ISIT]=""

                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                        .addOnCompleteListener {task2->
                            if(task2.isSuccessful){
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Аккаунт успешно создан",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(this@RegisterActivity, TestActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this@RegisterActivity, "Ошибка!", Toast.LENGTH_LONG).show()
                            }

                        }

                }
            }
        }
    }
}