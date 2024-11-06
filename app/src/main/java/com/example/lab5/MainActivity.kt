package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Currency

class MainActivity : AppCompatActivity() {

    private lateinit var saleSlider : SeekBar
    private lateinit var saleText : TextView
    private lateinit var costValue : EditText
    private lateinit var usdCheck : RadioButton
    private lateinit var eurCheck : RadioButton
    private lateinit var pndCheck : RadioButton
    private lateinit var okButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        saleSlider = findViewById(R.id.saleValSlider)
        saleText = findViewById(R.id.saleValueText)
        costValue = findViewById(R.id.costInputDecimal)
        usdCheck = findViewById(R.id.usdRadioButton)
        eurCheck = findViewById(R.id.euroRadioButton)
        pndCheck = findViewById(R.id.poundRadioButton)
        okButton = findViewById(R.id.calcButton)

        var saleVal : Double

        saleSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                saleText.text = progress.toString() + "%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        okButton.setOnClickListener() {
            var currency : Int = 1
            var currencyName : String = "Default"

            try {
                val baseCost = costValue.text.toString().toDouble()

                if (usdCheck.isChecked) {
                    currency = 93
                    currencyName = "Доллар"
                }
                if (eurCheck.isChecked) {
                    currency = 105
                    currencyName = "Евро"
                }
                if (pndCheck.isChecked) {
                    currency = 126
                    currencyName = "Фунт"
                }

                val finalCost = (baseCost/currency) * (1 - (saleSlider.progress/100))

                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("FINAL_COST", finalCost)
                    putExtra("CURRENCY_NAME", currencyName)
                }
                startActivity (intent)
            }
            catch (e : Exception) {
                Toast.makeText(this, "Не указана цена", Toast.LENGTH_SHORT).show()
            }
        }
    }
}