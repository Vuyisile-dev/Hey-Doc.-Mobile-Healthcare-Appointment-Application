package com.example.heydoc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname,email,password;
    Button register;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.registerBtn);

        db = new DatabaseHelper(this);

        register.setOnClickListener(v -> {

            boolean inserted = db.registerUser(
                    fullname.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString());

            if(inserted){

                Toast.makeText(this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT).show();

                finish();

            }else{

                Toast.makeText(this,
                        "Registration Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}