package com.example.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputValue = view.findViewById<EditText>(R.id.input_value)
        val unitFromSpinner = view.findViewById<Spinner>(R.id.unit_from_spinner)
        val unitToSpinner = view.findViewById<Spinner>(R.id.unit_to_spinner)
        val convertButton = view.findViewById<Button>(R.id.convert_button)
        val resultText = view.findViewById<TextView>(R.id.result_text)

        convertButton.setOnClickListener {
            val value = inputValue.text.toString().toDoubleOrNull()
            val fromUnit = unitFromSpinner.selectedItem.toString()
            val toUnit = unitToSpinner.selectedItem.toString()

            if (value != null) {
                val result = convert(value, fromUnit, toUnit)
                resultText.text = "$value $fromUnit = $result $toUnit"
            } else {
                resultText.text = "Invalid Input"
            }
        }
    }

    private fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        val lengthUnits = mapOf(
            "Meters" to 1.0,
            "Kilometers" to 1000.0,
            "Centimeters" to 0.01,
            "Millimeters" to 0.001
        )

        return when {
            lengthUnits.containsKey(fromUnit) && lengthUnits.containsKey(toUnit) -> {
                value * (lengthUnits[fromUnit] ?: 1.0) / (lengthUnits[toUnit] ?: 1.0)
            }
            else -> value
        }
    }
}
