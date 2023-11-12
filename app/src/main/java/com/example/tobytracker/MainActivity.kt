package com.example.tobytracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var myKilometers = 0.0f
    private var hisKilometers = 0.0f
    private val myPreText = "My Kilometers: "
    private val hisPreText = "Toby's Kilometers: "
    private lateinit var myKilometersTextView: TextView
    private lateinit var hisKilometersTextView: TextView
    private lateinit var myKilometersInput: EditText
    private lateinit var hisKilometersInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myKilometersTextView = findViewById(R.id.myKilometersTextView)
        hisKilometersTextView = findViewById(R.id.hisKilometersTextView)
        myKilometersInput = findViewById<EditText>(R.id.myKilometersInput)
        hisKilometersInput = findViewById<EditText>(R.id.hisKilometersInput)

        val addKilometersButton = findViewById<Button>(R.id.addKilometersButton)
        val hisAddKilometersButton = findViewById<Button>(R.id.hisAddKilometersButton)
        val settingsButton: Button = findViewById(R.id.settingsButton)

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        myKilometers = sharedPreferences.getFloat("myKilometers", 0.0f)
        hisKilometers = sharedPreferences.getFloat("hisKilometers", 0.0f)

        // Set initial Text View Values
        myKilometersTextView.text = "$myPreText${formatKilometers(myKilometers)}"
        hisKilometersTextView.text = "$hisPreText${formatKilometers(hisKilometers)}"

        addKilometersButton.setOnClickListener {
            myKilometers = handleAddKilometers(myKilometers, myKilometersTextView, myKilometersInput, myPreText)

            editor.putFloat("myKilometers", myKilometers)

            editor.apply()
            // Clear the input field
            myKilometersInput.text.clear()

        }

        hisAddKilometersButton.setOnClickListener {
            hisKilometers = handleAddKilometers(hisKilometers, hisKilometersTextView, hisKilometersInput, hisPreText)

            editor.putFloat("hisKilometers", hisKilometers)

            editor.apply()

            // Clear the input field
            hisKilometersInput.text.clear()
        }
    }
    private fun handleAddKilometers(oldKilometers: Float, textView: TextView, newKilometers: EditText, preText: String): Float {

        val kilometresToAdd = try {
            val inputNumber = newKilometers.text.toString().toFloat()
            inputNumber
        } catch (e: NumberFormatException) {
            // Handle the case where a non-integer value is entered
            // Display a message or handle it as appropriate
            return oldKilometers
        }

        // Update kilometers and refresh the UI
        val updatedKilometers = oldKilometers + kilometresToAdd

        textView.text = "$preText${formatKilometers(updatedKilometers)}"

        return updatedKilometers
    }

    private fun formatKilometers(kilometers: Float): String{
        return String.format("%.2f", kilometers)
    }
}