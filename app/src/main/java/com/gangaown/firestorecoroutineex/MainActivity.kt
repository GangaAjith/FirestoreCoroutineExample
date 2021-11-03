package com.gangaown.firestorecoroutineex


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gangaown.firestorecoroutineex.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.DocumentReference


data class Person(var first_name:String="", var last_name:String="", var age:String=""){

}
class MainActivity : AppCompatActivity() {
    private lateinit var dbCollection: DocumentReference

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val db = Firebase.firestore
        //Add a new document with a generated ID
        dbCollection = db.collection("users").document("tutorial")


        binding.fab.setOnClickListener{
            UserInfoDialog(this, object :AddDialogListener{
                override fun onAddButtonClicked(person: Person){
                    GlobalScope.launch(Dispatchers.IO) {
                        //delay(3000L)
                        dbCollection.set(person).await()
                        val personInfo = dbCollection.get().await().toObject(Person::class.java)
                        withContext(Dispatchers.Main){
                            ("FirstName :"+personInfo?.first_name.toString()).also { binding.tvFirstName.text = it }
                            ("LastName :"+personInfo?.last_name.toString()).also { binding.tvLastName.text = it }
                            ("Age :"+personInfo?.age.toString()).also { binding.tvAge.text = it }
                        }
                    }
                }
            }).show()

        }
    }

}