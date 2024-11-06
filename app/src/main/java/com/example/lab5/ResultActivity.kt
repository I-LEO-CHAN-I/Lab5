package com.example.lab5

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var costText : TextView
    private lateinit var currencyName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        costText = findViewById(R.id.finalCostValueText)
        currencyName = findViewById(R.id.currencyNameText)

        costText.text = "%.2f".format(intent.getDoubleExtra("FINAL_COST", 0.0))
        currencyName.text = "Валюта: " + intent.getStringExtra("CURRENCY_NAME")
    }
}