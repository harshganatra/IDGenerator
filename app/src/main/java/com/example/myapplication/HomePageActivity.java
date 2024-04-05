package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.myapplication.databinding.ActivityHomePageBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class HomePageActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            Log.d("debug","yes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Home");
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

        TextView name = (TextView) findViewById(R.id.nameTextView);
        TextView dep = (TextView) findViewById(R.id.depTextView);
        ImageView dp = (ImageView) findViewById(R.id.imageView);
        LinearLayout ordersBox = (LinearLayout) findViewById(R.id.ordersBox);
        LinearLayout topBox = (LinearLayout) findViewById(R.id.topButtonBox);
        LinearLayout statusBox = (LinearLayout) findViewById(R.id.statusBox);
        LinearLayout searchBox = (LinearLayout) findViewById(R.id.searchBox);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        TextView buttonText= (TextView) findViewById(R.id.buttonTextView);
        TextView statusText= (TextView) findViewById(R.id.textView2);
        Button ordersButton = (Button) findViewById(R.id.button2);
        ImageView img3 = (ImageView) findViewById(R.id.imageView3);
        ImageView img4 = (ImageView) findViewById(R.id.img4);

        if (type == 1){
            String uri = "@drawable/vinayak";  // where myresource (without the extension) is the file
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            dp.setImageDrawable(res);
        }
        name.setText(emp.name);
        dep.setText(emp.department);

        if(!emp.department.equals("House of People") || !emp.status.equals("Completed") ) {
            ordersBox.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.INVISIBLE);
        }

        if (emp.status.equals("Completed")) {
            statusBox.setVisibility(View.GONE);
        } else if (emp.status.equals("Incomplete")) {
            //update box and button
            statusText.setText("Status: "+emp.status);
            buttonText.setText("Please click to complete your profile:");
            searchBox.setVisibility(View.GONE);
        } else {
            statusText.setText("Status: "+emp.status);
                searchBox.setVisibility(View.GONE);
        }

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emp.status.equals("Incomplete")) {
                    Intent intent = new Intent(HomePageActivity.this,UserFormActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", type); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                } else {
                    String str =
                            "BEGIN:VCARD\n" +
                                    "VERSION:3.0\r\n" +
                                    "FN:" + emp.name +
                                    "EMAIL:" + emp.email + "\r\n" +
                                    "TEL;CELL:" + emp.mobile +"\r\n" +
                                    "END:VCARD\r\n";

                    QRCodeWriter writer = new QRCodeWriter();
                    try {
                        BitMatrix bitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, 512, 512);
                        int width = bitMatrix.getWidth();
                        int height = bitMatrix.getHeight();
                        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                            }
                        }

                        img4.setImageBitmap(bmp);
                        img4.setVisibility(View.VISIBLE);
                        Log.e("debug","set image");
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img4.setVisibility(View.GONE);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAnchorView(R.id.fab)
                    //    .setAction("Action", null).show();
                Intent intent = new Intent(HomePageActivity.this,UserFormActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", type); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        searchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,UserProfileActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", type); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,OrdersActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", type); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
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