package com.gangaown.firestorecoroutineex

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.gangaown.firestorecoroutineex.databinding.LayoutDialogBinding

class UserInfoDialog(context: Context, private var addDialogListener: AddDialogListener):AppCompatDialog(context) {

    private lateinit var binding: LayoutDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAdd.setOnClickListener{
            val fname = binding.etFirstname.text.toString()
            val lname = binding.etLastname.text.toString()
            val age = binding.etAge.text.toString()

            if(fname.isEmpty()||lname.isEmpty()||age.isEmpty()){
                Toast.makeText(context,"Please enter all the details! ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val person = Person(fname,lname, age)
            addDialogListener.onAddButtonClicked(person)
            dismiss()
        }

        binding.btnCancel.setOnClickListener{
            cancel()
        }

    }
}