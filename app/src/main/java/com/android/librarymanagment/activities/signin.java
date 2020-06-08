package com.android.librarymanagment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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

        ImageButton librayDetails = findViewById(R.id.library_details);
        Button Register = findViewById(R.id.register);
        Button Login = findViewById(R.id.login);


        librayDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase db = new dataBase(signin.this);

              int [] details =db.libraryDetails();

                libraryDetailsDialog(details);

            }

            private void libraryDetailsDialog(int [] details) {


                final Dialog d = new Dialog(signin.this);
                d.setContentView(R.layout.library_details);
                d.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);


                TextView booksNumber = d.findViewById(R.id.booksNumber);
                TextView total_topRated = d.findViewById(R.id.total_topRated);
                TextView staffnumber = d.findViewById(R.id.staffnumber);
                TextView awards = d.findViewById(R.id.awards);
                TextView computers_number = d.findViewById(R.id.computers_number);

                booksNumber.setText(details[0]+"");
                total_topRated.setText(details[1]+"");
                staffnumber.setText(details[2]+"");
                awards.setText(details[3]+"");
                computers_number.setText(details[4]+"");

                d.show();
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent view = new Intent(signin.this, registration.class);
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

                    String user_id;
                    String user_name;
                    String user_type;

                    if ((data.signIn(user_email, user_password)).length==0)
                        Toast.makeText(signin.this, "No register account please register", Toast.LENGTH_SHORT).show();
                    else {
                        user_id=data.signIn(user_email, user_password)[0];
                        user_name=data.signIn(user_email, user_password)[1];
                        user_type=data.signIn(user_email, user_password)[2];

                        Intent view = new Intent(signin.this, MainPage.class);
                        view.putExtra("user_id", user_id);
                        view.putExtra("user_name", user_name);
                        view.putExtra("user_type", user_type);
                        startActivity(view);

                    }
                }
            }
        });
    }
}
