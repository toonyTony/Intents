package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private var rentCost: Double = 0.0
    private var selectedMode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInput = findViewById<View>(R.id.btnInput)
        val btnCalc = findViewById<View>(R.id.btnCalc)
        val btnExit = findViewById<View>(R.id.btnExit)

        btnInput.setOnClickListener {
            showInputWindow()
        }

        btnCalc.setOnClickListener {
            showCalcWindow()
        }

        btnExit.setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnInput -> {
                showInputWindow()
                true
            }
            R.id.btnCalc -> {
                showCalcWindow()
                true
            }
            R.id.btnExit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showInputWindow() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.input_window, null)
        dialogBuilder.setView(dialogView)

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroupMode)

        dialogBuilder.setTitle("Окно ввода данных")
        dialogBuilder.setPositiveButton("Сохранить") { _, _ ->

            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = dialogView.findViewById<RadioButton>(selectedRadioButtonId)
            selectedMode = selectedRadioButton?.text.toString()
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
        }
        dialogBuilder.setNegativeButton("Отмена", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun showCalcWindow() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.calc_window, null)
        dialogBuilder.setView(dialogView)

        val tarMode = dialogView.findViewById<TextView>(R.id.tarMode)
        val tarResult = dialogView.findViewById<TextView>(R.id.tarResult)

        tarMode.text = "Тариф: $selectedMode"
        tarResult.text = "Результат: ${calculateRent()}"

        dialogBuilder.setTitle("Окно с результатами")
        dialogBuilder.setPositiveButton("OK", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun calculateRent(): Double {

        return rentCost
    }
}
