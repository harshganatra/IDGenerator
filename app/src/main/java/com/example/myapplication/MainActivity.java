package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        EditText empID = (EditText) findViewById(R.id.editTextEmployeeID);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int type;
                boolean progress = false;
                String empIDtext = empID.getText().toString();
                Log.d("myTag", "This is my message"+empIDtext);
                /*if(empIDtext.equals("1")){
                    type  = 1;
                    progress = true;
                } else if(empIDtext.equals("2")){
                    type  = 2;
                    progress = true;
                } else if(empIDtext.equals("3")){
                    type  = 3;
                    progress = true;
                }*/
                try {
                    type = Integer.parseInt(empIDtext);
                    progress = true;
                } catch(NumberFormatException e){
                    Log.e("debug",e.toString());
                    type=0;
                }
                if(progress){
                    //Log.d("myTag", "This is my message 2 "+empIDtext);
                    Intent intent = new Intent(MainActivity.this,HomePageActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", type); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                    //finish();
                } else {
                    Toast.makeText(MainActivity.this, "Enter Employee ID as 1,2,3 or 4 for demo",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}