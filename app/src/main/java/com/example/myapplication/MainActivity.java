package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    EditText website, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
    }

    public void go_to_website(View view) {
        String s = website.getText().toString();
        if (!s.startsWith("https://") && !s.startsWith("http://")){
            s = "http://" + s;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(intent);
    }

    public void call_to_phone(View view) {
        Log.e("In", "phone:"+ Uri.parse("tel:" + String.valueOf(phone.getText())));
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + String.valueOf(phone.getText())));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
//            return;
        } else {
            //You already have permission
            try {
                startActivity(intent);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
