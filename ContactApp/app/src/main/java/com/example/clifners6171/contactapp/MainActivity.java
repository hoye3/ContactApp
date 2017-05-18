package com.example.clifners6171.contactapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
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
    EditText editName, editNumber, editAddress, findName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editNumber = (EditText) findViewById(R.id.editText_number);
        editAddress = (EditText) findViewById (R.id.editText_address);
        findName = (EditText) findViewById (R.id.editText_findContact);

    }

    public void addData (View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString(), editAddress.getText().toString());

        if(isInserted) {
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            String message = "Data inserted correctly";

            Toast.makeText(context, message, duration).show();
        }
        else {
            Log.d("MyContact", "Data insertion unsuccessful");
            //Create toast message to user indicating data inserted incorrectly

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            String message = "Data not inserted correctly";

            Toast.makeText(context, message, duration).show();
        }
    }

    public void viewData (View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            Log.d("MyContact", "No data found in database");
        }

        StringBuffer buffer = new StringBuffer();
        //set up loop (while) w/ Cursor moveToNext method
        //  "append" each COL to the buffer
        //  use the getString method

        while(res.moveToNext()) {
            buffer.append("ID "+res.getString(0)+"\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Number: " +res.getString(2)+ "\n");
            buffer.append("Address: " + res.getString(3) +"\n");
        }

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //Cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
        //Log.d("MyContact", message);


    }

    public void findContact(View v) {
        Cursor res = myDb.getAllData();
       if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            Log.d("MyContact", "No data found in database");
        }
        Log.d("MyContact", String.valueOf(res.getCount()));
        StringBuffer buffer = new StringBuffer();
        boolean nameFound = false;

        while(res.moveToNext() && !nameFound) {
            if(findName.getText().toString().equals(res.getString(1))) {
                buffer.append("ID " + res.getString(0) + "\n");
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Number: " + res.getString(2) + "\n");
                buffer.append("Address: " + res.getString(3) + "\n");
                showMessage(findName.getText().toString(), buffer.toString());
                nameFound = true;
            }
        }
        if(!nameFound)
            showMessage("Error", "No contact found");
       // love stephanie;
        // love stephanie more;
        // :) <3;
        // :( </3;

        //android intend for extra credit??


    }
}
