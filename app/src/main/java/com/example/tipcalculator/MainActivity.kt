package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val rawCost = binding.costOfService.text.toString()
        val cost = rawCost.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip("0.0")
            return
        }
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        val roundUp = binding.roundUpSwitch.isChecked
        var rawTip = tipPercent * cost

        if (roundUp) {
            rawTip = ceil(rawTip)
        }

        val tip = NumberFormat.getCurrencyInstance().format(rawTip)
        displayTip(tip)
    }

    private fun displayTip(tip: String) {
        binding.tipResult.text = getString(R.string.tip_amount, tip)
    }
}