package com.example.madassignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        GridLayout grid = findViewById(R.id.gridLayout);

        // Set click listeners for all buttons
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button btn = (Button) grid.getChildAt(i);
            btn.setOnClickListener(v -> handleInput(btn.getText().toString()));
        }
    }

    private void handleInput(String value) {
        switch (value) {
            case "AC":
                input = "";
                tvDisplay.setText("0");
                break;
            case "=":
                calculateResult();
                break;
            default:
                input += value;
                tvDisplay.setText(input);
                break;
        }
    }

    private void calculateResult() {
        try {
            double result = evaluateExpression(input);
            tvDisplay.setText(String.valueOf(result));
            input = String.valueOf(result);
        } catch (Exception e) {
            tvDisplay.setText("Error");
            input = "";
        }
    }

    private double evaluateExpression(String expr) {
        expr = expr.replace("×", "*").replace("÷", "/").replace("−", "-");

        if (expr.contains("sin")) return Math.sin(getNumber(expr, "sin"));
        if (expr.contains("cos")) return Math.cos(getNumber(expr, "cos"));
        if (expr.contains("tan")) return Math.tan(getNumber(expr, "tan"));
        if (expr.contains("log")) return Math.log10(getNumber(expr, "log"));
        if (expr.contains("ln"))  return Math.log(getNumber(expr, "ln"));
        if (expr.contains("√"))   return Math.sqrt(getNumber(expr, "√"));
        if (expr.contains("^")) {
            String[] parts = expr.split("\\^");
            return Math.pow(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }
        if (expr.contains("!")) {
            int n = Integer.parseInt(expr.replace("!", ""));
            return factorial(n);
        }

        return simpleEval(expr);
    }

    private double getNumber(String expr, String op) {
        return Double.parseDouble(expr.replace(op, ""));
    }

    private double simpleEval(String exp) {
        char[] ops = {'+', '-', '*', '/'};
        for (char op : ops) {
            int idx = exp.indexOf(op);
            if (idx != -1) {
                double a = Double.parseDouble(exp.substring(0, idx));
                double b = Double.parseDouble(exp.substring(idx + 1));
                return applyOp(a, b, op);
            }
        }
        return Double.parseDouble(exp);
    }

    private double applyOp(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
        }
        return b;
    }

    private int factorial(int n) {
        if (n < 0) throw new ArithmeticException("Invalid factorial");
        int result = 1;
        for (int i = 1; i <= n; i++) result *= i;
        return result;
    }
}
