package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnDoneButton.setOnClickListener { addNickName(it) }
        binding.tvNickNameText.setOnClickListener { updateNickname(it) }
    }

    private fun clickHandlerFunction(view: View) {
        TODO("Not yet implemented")
    }

    private fun addNickName(view: View) {
        binding.tvNickNameText.text = binding.etNickNameEdit.text
        binding.etNickNameEdit.visibility = View.GONE
        binding.btnDoneButton.visibility = View.GONE
        binding.tvNickNameText.visibility = View.VISIBLE
        //Funcao teclado invisivel
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        binding.etNickNameEdit.visibility = View.VISIBLE
        binding.btnDoneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        binding.etNickNameEdit.requestFocus()
        //Funcao deixa teclado visivel
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etNickNameEdit, 0)
    }

}