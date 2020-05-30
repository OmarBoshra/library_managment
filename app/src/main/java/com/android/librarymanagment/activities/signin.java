package com.android.librarymanagment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.librarymanagment.R;
import com.android.librarymanagment.dataBase;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_sign_in);

        EditText email= findViewById(R.id.email);
        EditText password= findViewById(R.id.password);



        buttons(email,password);
    }


    private void buttons(final EditText email,final EditText password) {

        Button Register = findViewById(R.id.register);
        Button Login = findViewById(R.id.login);



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent view = new Intent(signin.this, MainPage.class);
                startActivity(view);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_email;
                String user_password;

                if ((user_email=email.getText().toString().trim()).isEmpty() || (user_password=password.getText().toString().trim()).isEmpty())
                    Toast.makeText(signin.this, "No inserted data !", Toast.LENGTH_SHORT).show();
                else {
                    dataBase data = new dataBase(signin.this);

                    String user_name;
                    if ((user_name = data.signIn(user_email, user_password)).isEmpty())
                        Toast.makeText(signin.this, "No register account please register", Toast.LENGTH_SHORT).show();
                    else {
                        Intent view = new Intent(signin.this, MainPage.class);
                        view.putExtra("user_name", user_name);
                        startActivity(view);

                    }
                }
            }
        });
    }
}
