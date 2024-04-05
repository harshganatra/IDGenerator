package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            Log.d("debug","toolbar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Details");
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Bundle extras = getIntent().getExtras();
        int type2=0;
        if (extras != null) {
            type2 = extras.getInt("key");
            //The key argument here must match that used in the other activity
        }
        final int type = type2;
        Employee emp=new Employee(type);

        TextView name = (TextView) findViewById(R.id.nameTextView2);
        TextView dep = (TextView) findViewById(R.id.depTextView2);
        TextView empID = (TextView) findViewById(R.id.empID);
        TextView doj = (TextView) findViewById(R.id.doj);
        TextView email = (TextView) findViewById(R.id.workEmail);
        TextView offPh = (TextView) findViewById(R.id.offPhone);
        TextView phone = (TextView) findViewById(R.id.perPhone);
        TextView linkedIn = (TextView) findViewById(R.id.linkedIn);
        TextView offName = (TextView) findViewById(R.id.offName);
        TextView offAdd = (TextView) findViewById(R.id.offAdd);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        name.setText(emp.name);
        dep.setText(emp.department);
        empID.setText(emp.employeeID);
        doj.setText(emp.DOJ);
        email.setText(emp.email);
        offPh.setText(emp.mobile);
        phone.setText(emp.perNum);
        linkedIn.setText("Click to Open");
        offName.setText(emp.offName);
        offAdd.setText(emp.offAddress);

        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(emp.linkedIN));
                startActivity(i);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates a new Intent to insert a contact
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emp.email)
                        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                        .putExtra(ContactsContract.Intents.Insert.PHONE, emp.mobile)
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                        .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, emp.perNum)
                        .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .putExtra(ContactsContract.Intents.Insert.NAME, emp.name)
                        .putExtra(ContactsContract.Intents.Insert.JOB_TITLE, emp.department)
                        .putExtra(ContactsContract.Intents.Insert.COMPANY, emp.offName)
                        .putExtra(ContactsContract.Intents.Insert.POSTAL, emp.offAddress)
                        .putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK)
                        .putExtra(ContactsContract.Intents.Insert.ACTION, emp.linkedIN);

                UserProfileActivity.this.startActivity(intent);

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}