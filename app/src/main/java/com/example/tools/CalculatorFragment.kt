package com.example.tools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input1 = view.findViewById<EditText>(R.id.input1)
        val input2 = view.findViewById<EditText>(R.id.input2)
        val operatorSpinner = view.findViewById<Spinner>(R.id.operator_spinner)
        val calculateButton = view.findViewById<Button>(R.id.calculate_button)
        val resultText = view.findViewById<TextView>(R.id.result_text)

        calculateButton.setOnClickListener {
            val num1 = input1.text.toString().toDoubleOrNull()
            val num2 = input2.text.toString().toDoubleOrNull()
            val operator = operatorSpinner.selectedItem.toString()

            if (num1 != null && num2 != null) {
                val result = when (operator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error: Division by Zero"
                    else -> "Error"
                }

                resultText.text = result.toString()

                // Pass the result to the ConverterFragment via Intent
                val intent = Intent(activity, MainActivity::class.java).apply {
                    putExtra("calculation_result", result.toString())
                }
                startActivity(intent)
            } else {
                resultText.text = "Invalid Input"
            }
        }
    }
}
