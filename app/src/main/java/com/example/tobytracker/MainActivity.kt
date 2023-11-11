package com.example.tobytracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var myKilometers = 0
    private lateinit var myKilometersTextView: TextView
    private var hisKilometers = 0
    private lateinit var hisKilometersTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myKilometersTextView = findViewById(R.id.myKilometersTextView)

        hisKilometersTextView = findViewById(R.id.hisKilometersTextView)

        val addKilometersButton = findViewById<Button>(R.id.addKilometersButton)
        val kilometersInput = findViewById<EditText>(R.id.kilometersInput)
        val hisAddKilometersButton = findViewById<Button>(R.id.hisAddKilometersButton)
        val hisKilometersInput = findViewById<EditText>(R.id.hisKilometersInput)

        addKilometersButton.setOnClickListener {
            // Get the input from the EditText
            val kilometersToAdd = kilometersInput.text.toString().toInt()

            // Update your kilometers and refresh the UI
            myKilometers += kilometersToAdd
            myKilometersTextView.text = "Your Kilometers: $myKilometers"

            // Clear the input field
            kilometersInput.text.clear()
        }

        hisAddKilometersButton.setOnClickListener {
            // Get the input from the EditText
            val kilometersToAdd = hisKilometersInput.text.toString().toInt()

            // Update your kilometers and refresh the UI
            hisKilometers += kilometersToAdd
            hisKilometersTextView.text = "Toby's Kilometers: $hisKilometers"

            // Clear the input field
            hisKilometersInput.text.clear()
        }
    }
}