package com.example.first;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView revertInput;
    TextView operationType;
    TextView operationResult;
    TextView firstArgument;
    TextView secondArgument;

    ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        handleResult(data);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revertInput = findViewById(R.id.textToRevertInputText);
        firstArgument = findViewById(R.id.firstArgumentInputText);
        secondArgument = findViewById(R.id.secondArgumentInputText);
        operationType = findViewById(R.id.operationTextView);
        operationResult = findViewById(R.id.operationResultTextView);

    }

    public void openRevertedTextActivity(View view) {
        String text = revertInput.getText().toString();
        Intent intent = new Intent(this, RevertedActivity.class);
        intent.putExtra("text", text);

        startActivity(intent);
    }

    public void runOperation(View view) {
        Intent intent = new Intent()
                .putExtra("first", firstArgument.getText().toString())
                .putExtra("second", secondArgument.getText().toString())
                .setAction("First")
                .setType("text/plain");
        launcher.launch(intent);
    }

    protected void handleResult(Intent intent) {
        operationType.setText(intent.getExtras().get("operation").toString());
        operationResult.setText(intent.getExtras().get("result").toString());
    }
}
