package com.example.ex5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber1, editTextNumber2;
    private Spinner spinnerOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        spinnerOperator = findViewById(R.id.spinnerOperator);

        // Define an array of operations
        String[] operations = {"+", "-", "*", "/"};

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner
        spinnerOperator.setAdapter(adapter);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number1 = Double.parseDouble(editTextNumber1.getText().toString());
                double number2 = Double.parseDouble(editTextNumber2.getText().toString());
                String operator = spinnerOperator.getSelectedItem().toString();

                double result = performCalculation(number1, number2, operator);

                // Pass the result to the ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }

    private double performCalculation(double number1, double number2, String operator) {
        double result = 0.0;
        if (operator.equals("+")) {
            result = number1 + number2;
        } else if (operator.equals("-")) {
            result = number1 - number2;
        } else if (operator.equals("*")) {
            result = number1 * number2;
        } else if (operator.equals("/")) {
            if (number2 != 0) {
                result = number1 / number2;
            } else {
                // Handle division by zero error
                result = Double.NaN;
            }
        }
        return result;
    }
}