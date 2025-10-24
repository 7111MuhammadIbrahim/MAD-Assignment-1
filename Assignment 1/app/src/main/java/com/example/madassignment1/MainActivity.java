package com.example.madassignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg1, rg2, rg3, rg4, rg5;
    Button submitBtn;
    TextView resultText;
// my 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);
        rg4 = findViewById(R.id.rg4);
        rg5 = findViewById(R.id.rg5);
        submitBtn = findViewById(R.id.submitBtn);
        resultText = findViewById(R.id.resultText);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;


                if (rg1.getCheckedRadioButtonId() == R.id.q1a3) score++;
                if (rg2.getCheckedRadioButtonId() == R.id.q2a2) score++;
                if (rg3.getCheckedRadioButtonId() == R.id.q3a3) score++;
                if (rg4.getCheckedRadioButtonId() == R.id.q4a2) score++;
                if (rg5.getCheckedRadioButtonId() == R.id.q5a2) score++;


                resultText.setText("Your Score: " + score + " / 5");
            }
        });
    }
}