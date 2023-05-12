package com.example.quizforstudent

import com.example.quizforstudent.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID:String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User


const val NODE_USERS="users"
const val CHILD_ID = "id"
const val CHILD_EMAIL = "email"
const val CHILD_ECONOMY = "economy"
const val CHILD_electronicEconomy = "electronic_economy"
const val CHILD_ACCOUNTING = "accounting"
const val CHILD_ISIT = "isit"



fun initUSER(){
    USER = User()
}

fun initFirebase(){
    AUTH=FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    CURRENT_UID=AUTH.currentUser?.uid.toString()
}