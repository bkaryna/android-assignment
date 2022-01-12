package com.example.second;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String operation = "addition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showToast("Starting " + operation + " activity");

        Intent intent = getIntent();
        String first = intent.getStringExtra("first");
        String second = intent.getStringExtra("second");

        if(first == null && second == null) {
            showToast("No arguments");
            finish();
            return;
        }

        showToast("arguments: (" + first + "," + second + ")");
        showToast(calculateResult(first, second));

        Intent result = new Intent();
        result.putExtra("operation", operation);
        result.putExtra("result", calculateResult(first, second));

        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private String calculateResult(String first, String second) {
        Double result =  Double.parseDouble(first) + Double.parseDouble(second);
        return result.toString();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

