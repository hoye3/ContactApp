package com.example.clifners6171.contactapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DatabaseHelper myDb;
    EditText editName, editNumber, editAddress;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editNumber = (EditText) findViewById(R.id.editText_number);
        editAddress = (EditText) findViewById (R.id.editText_address);

    }

    public void addData (View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString(), editAddress.getText().toString());

        if(isInserted) {
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            String message = "Data inserted correctly";
            //Toast toast = new Toast(context, message, duration);

            Toast.makeText(context, message, duration).show();
        }
        else {
            Log.d("MyContact", "Data insertion unsuccessful");
            //Create toast message to user indicating data inserted incorrectly

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            String message = "Data not inserted correctly";
            //Toast toast = new Toast(context, message, duration);

            Toast.makeText(context, message, duration).show();
        }
    }
}
