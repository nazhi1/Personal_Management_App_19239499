package com.example.personal_managemen_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFriend extends AppCompatActivity {
    public EditText LastName, FirstName,Gender,Age,Address;
    public Button btnAdd,btnView;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //Initialize Database
        myDB = new DatabaseHelper( this );
        //init views
        LastName =findViewById(R.id.l_name);
        FirstName= findViewById(R.id.f_name);
        Gender=findViewById(R.id.gender);
        Age=findViewById(R.id.age);
        Address=findViewById(R.id.address);

        //init buttons
        btnAdd=findViewById(R.id.btn_add);
        btnView=findViewById(R.id.btn_view);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_LName,user_Fname,user_Address,user_Age,user_Gender;
                user_LName = LastName.getText().toString();
                user_Fname = FirstName.getText().toString();
                user_Address = Address.getText().toString();
                user_Age=Age.getText().toString();
                user_Gender=Gender.getText().toString();

                boolean isInserted = myDB.instertData( user_Fname, user_LName, user_Gender, user_Age,user_Gender);

                if(isInserted == true){
                    Toast.makeText( AddFriend.this, "Data is inserted", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( AddFriend.this, "Something went wrong data is not inserted", Toast.LENGTH_SHORT ).show();

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFriend.this,ShowFriends.class);
                startActivity(intent);

            }
        });

    }

    private void showMessage(String title, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( toString );
        builder.show();
    }
}