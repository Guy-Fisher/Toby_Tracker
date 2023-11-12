package com.example.tobytracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var myKilometers = 0
    private var hisKilometers = 0
    private lateinit var myKilometersTextView: TextView
    private lateinit var hisKilometersTextView: TextView
    private lateinit var myKilometersInput: EditText
    private lateinit var hisKilometersInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myKilometersTextView = findViewById(R.id.myKilometersTextView)
        hisKilometersTextView = findViewById(R.id.hisKilometersTextView)
        myKilometersInput = findViewById<EditText>(R.id.kilometersInput)
        hisKilometersInput = findViewById<EditText>(R.id.hisKilometersInput)

        val addKilometersButton = findViewById<Button>(R.id.addKilometersButton)
        val hisAddKilometersButton = findViewById<Button>(R.id.hisAddKilometersButton)

        addKilometersButton.setOnClickListener {
            myKilometers = handleAddKilometers(myKilometers, myKilometersTextView, myKilometersInput, "My Kilometers: ")
            // Clear the input field
            myKilometersInput.text.clear()
        }

        hisAddKilometersButton.setOnClickListener {
            hisKilometers = handleAddKilometers(hisKilometers, hisKilometersTextView, hisKilometersInput, "Toby's Kilometers: ")

            // Clear the input field
            hisKilometersInput.text.clear()
        }
    }
    private fun handleAddKilometers(oldKilometers: Int, textView: TextView, newKilometers: EditText, preText: String): Int {

        val kilometresToAdd = try {
            val inputNumber = newKilometers.text.toString().toInt()

            if (inputNumber < 0) {
                return oldKilometers
            }
            else{
                inputNumber
            }
        } catch (e: NumberFormatException) {
            // Handle the case where a non-integer value is entered
            // Display a message or handle it as appropriate
            return oldKilometers
        }

        // Update kilometers and refresh the UI
        val updatedKilometers = oldKilometers + kilometresToAdd

        textView.text = "$preText$updatedKilometers"

        return updatedKilometers
    }
}