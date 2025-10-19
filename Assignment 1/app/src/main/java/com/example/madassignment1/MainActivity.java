package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the UI layout for this Activity
        setContentView(R.layout.activity_main);

        // Initialize UI elements by finding them in the layout
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        // Create a single, shared OnClickListener for all operation buttons
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditTexts
                String s1 = etNum1.getText().toString();
                String s2 = etNum2.getText().toString();

                // Check if either input field is empty
                if (s1.isEmpty() || s2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }

                // Convert text to numbers
                double num1 = Double.parseDouble(s1);
                double num2 = Double.parseDouble(s2);
                double result = 0;

                // Determine which button was clicked using an if-else if block
                int id = v.getId();
                if (id == R.id.btnAdd) {
                    result = num1 + num2;
                } else if (id == R.id.btnSub) {
                    result = num1 - num2;
                } else if (id == R.id.btnMul) {
                    result = num1 * num2;
                } else if (id == R.id.btnDiv) {
                    // Handle division by zero
                    if (num2 == 0) {
                        Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return; // Stop further execution
                    }
                    result = num1 / num2;
                }

                // Display the result in the TextView
                tvResult.setText("Result: " + result);
            }
        };

        // Attach the listener to each button
        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }
}