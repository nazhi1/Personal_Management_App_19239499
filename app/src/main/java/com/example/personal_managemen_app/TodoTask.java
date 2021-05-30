package com.example.personal_managemen_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TodoTask extends AppCompatActivity {
    EditText task_name, location;
    Button add_button,button2;
    TodoHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_task);

        //Initialize Database
        myDB = new TodoHelper( TodoTask.this );

        task_name = findViewById(R.id.task_name);
        location = findViewById(R.id.location);

        add_button = findViewById(R.id.button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodoHelper myDB = new TodoHelper(TodoTask.this);
                myDB.addBook(task_name.getText().toString().trim(),
                        location.getText().toString().trim(), "Not Completed");
            }
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.readAllData();
                if (res.getCount() == 0){
                    showMessage("Error", "Data not found!");
                }

                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append( "TASK: " + res.getString( 0 ) + "\n" );
                        buffer.append( "Task Name: " + res.getString( 1 ) + "\n" );
                        buffer.append( "Location: " + res.getString( 2 ) + "\n" );
                        buffer.append( "Status: " + res.getString( 3 ) + "\n" );
                    }

                    showMessage( "Todo Tasks", buffer.toString() );

                }
            }
        });
    }
    //Method for creating AlertDialog box
    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }
}