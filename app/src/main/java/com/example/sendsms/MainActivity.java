package com.example.sendsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sendsms.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        binding.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
//                        == PackageManager.PERMISSION_GRANTED)
//                {
//                    sendSMS();
//                }
//                else {
//                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
//                }
//            }
//        });

        binding.readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    ReadSMS();
                }
                else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS},101);
                }
            }
        });
    }

    @SuppressLint("Range")
    private void ReadSMS() {
        Sms object ;
        List<Sms> list = new ArrayList<Sms>();
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"),null, null, null,null);
        int total = cursor.getCount();
        while (cursor.moveToNext()){
            object = new Sms();
            object.setId(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.THREAD_ID)));
            object.setAddress(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.ADDRESS)));
            object.setMsg(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.BODY)));
            object.setReadState(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.READ)));
            object.setTime(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.DATE)));
            object.setType(cursor.getString(cursor.getColumnIndex(Telephony.TextBasedSmsColumns.TYPE)));
            list.add(object);
        }

        for (int i= 0; i<list.toArray().length; i++) {
            if (Objects.equals(list.get(i).getType(), "2")) {
                Log.d("AAA", list.get(i).toString());
            }
        }
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            ReadSMS();
        }
        else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

//    private void sendSMS() {
//        String phone  = binding.numberPhone.getText().toString();
//        String message = binding.sms.getText().toString();
//
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(phone, null, message, null, null);
//        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//    }

}