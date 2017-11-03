package io.github.raefaldhia.talentbandung_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by raefaldhia on 10/31/17.
 */

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText inputUsername = (EditText)findViewById(R.id.inputUsername);
        final EditText inputEmail = (EditText)findViewById(R.id.inputEmail);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputUsername.getText().toString().isEmpty()) {
                    inputUsername.setError("Please fill username");
                }
                if (inputEmail.getText().toString().isEmpty()) {
                    inputEmail.setError("Please fill email");
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("Username", inputUsername.getText().toString());
                    intent.putExtra("Email", inputEmail.getText().toString());

                    setResult(RESULT_OK, intent);

                    finish();
                }
            }
        });

        Button btnCancel= (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
