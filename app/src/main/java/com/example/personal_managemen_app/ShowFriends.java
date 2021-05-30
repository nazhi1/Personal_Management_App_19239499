package com.example.personal_managemen_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShowFriends extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHelper myDB;
    ArrayList<String> user_id,user_fname, user_lname, user_age, user_gender,user_address;
    MyAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        myDB = new DatabaseHelper(ShowFriends.this);
        user_id = new ArrayList<>();
        user_fname = new ArrayList<>();
        user_lname = new ArrayList<>();
        user_age = new ArrayList<>();
        user_gender = new ArrayList<>();
        user_address = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new MyAdapter(ShowFriends.this,this, user_id, user_fname, user_lname,
                user_age,user_gender,user_address);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowFriends.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.getData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_fname.add(cursor.getString(1));
                user_lname.add(cursor.getString(2));
                user_age.add(cursor.getString(3));
                user_gender.add(cursor.getString(4));
                user_address.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.delete_all){
//            confirmDialog();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(ShowFriends.this);
               // myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ShowFriends.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}