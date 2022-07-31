package com.example.sihias;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Objects;

public class Konten extends AppCompatActivity {

    private TextView nilaiTanah;
    private TextView nilaiSuhu;
    private TextView nilaiDebit;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konten);


        nilaiTanah = (TextView) findViewById(R.id.nilaiTanah);      //Inisiasi nilai kelembaban tanah
        mRef = new Firebase("https://realtime-sensor-ab125-default-rtdb.firebaseio.com/kelembabanTanah");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String kelembabanTanah = dataSnapshot.getValue(String.class);
                nilaiTanah.setText(kelembabanTanah);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        nilaiSuhu = (TextView) findViewById(R.id.nilaiSuhu);        //Inisiasi nilai suhu
        mRef = new Firebase("https://realtime-sensor-ab125-default-rtdb.firebaseio.com/suhu");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String suhu = dataSnapshot.getValue(String.class);
                nilaiSuhu.setText(suhu);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        nilaiDebit = (TextView) findViewById(R.id.nilaiDebit);             //Inisiasi nilai debit air
        mRef = new Firebase("https://realtime-sensor-ab125-default-rtdb.firebaseio.com/tinggiAir");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String debitair = dataSnapshot.getValue(String.class);
                nilaiDebit.setText(debitair);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void kembali (View view) {
        onBackPressed();
    }

}