package com.example.personal_managemen_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText fName,lName,age,user_gender,address;
    Button update_button,btn_delete;
    DatabaseHelper myDB;


    String id, first_name, last_name, user_age,gender,user_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Initialize Database
        myDB = new DatabaseHelper( this );

        fName = findViewById(R.id.f_name);
        lName = findViewById(R.id.l_name);
        age = findViewById(R.id.age);
        user_gender=findViewById(R.id.gender);
        address=findViewById(R.id.address);
        update_button = findViewById(R.id.btnupdate);
        btn_delete=findViewById(R.id.btndelete);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(first_name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                first_name = fName.getText().toString().trim();
                last_name = lName.getText().toString().trim();
                user_age = age.getText().toString().trim();
                gender = user_gender.getText().toString().trim();
                user_address = address.getText().toString().trim();
                myDB.updateData(id, first_name, last_name, user_age,gender,user_address);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Are you sure to delete "+first_name+" ?");
                dialog.setPositiveButton("DELETE" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Action for "OK".
                     //   String u_id = id.getText().toString();

                        Integer res = myDB.deleteData(String.valueOf(id));
                        if(res > 0){
                            Toast.makeText( getApplicationContext(), "You have deleted one of your friends data", Toast.LENGTH_SHORT ).show();
                        }
                        else{
                            Toast.makeText( getApplicationContext(), "Unable to delete something went wrong", Toast.LENGTH_SHORT ).show();
                        }

                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("first_name") &&
                getIntent().hasExtra("last_name") && getIntent().hasExtra("user_age")&& getIntent().hasExtra("gender")
                && getIntent().hasExtra("user_address")){
            //Getting Data from Intent;
            id = getIntent().getStringExtra("id");
            first_name = getIntent().getStringExtra("first_name");
            user_age = getIntent().getStringExtra("user_age");
            last_name = getIntent().getStringExtra("last_name");
            gender = getIntent().getStringExtra("gender");
            user_address = getIntent().getStringExtra("user_address");

            //Setting Intent Data
            fName.setText(first_name);
            lName.setText(last_name);
            age.setText(user_age);
            user_gender.setText(gender);
            address.setText(user_address);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }


}
