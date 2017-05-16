package com.example.clifners6171.contactapp;

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
            //buffer.append(res.moveToNext());
        }

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d("MyContact", message);


    }
}
