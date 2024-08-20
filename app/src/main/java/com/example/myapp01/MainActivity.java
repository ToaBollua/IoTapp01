package com.example.myapp01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1, etNum2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResult = findViewById(R.id.tvResult);

        findViewById(R.id.btnAdd).setOnClickListener(v -> performCalculation('+'));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> performCalculation('-'));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> performCalculation('*'));
        findViewById(R.id.btnDivide).setOnClickListener(v -> performCalculation('/'));
    }

    @SuppressLint("DefaultLocale")
    private void performCalculation(char operation) {
        try {
            double num1 = Double.parseDouble(etNum1.getText().toString());
            double num2 = Double.parseDouble(etNum2.getText().toString());

            double result;
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    return;
            }

            tvResult.setText(String.format("%.2f", result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
