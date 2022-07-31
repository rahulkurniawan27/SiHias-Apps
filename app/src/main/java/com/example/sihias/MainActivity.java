package com.example.sihias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "101";
    String channelnotif = "channelku";
    String channelid = "default";

    private static final String TAG = "PushNotification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        getToken();

    }

    public void notif(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, channelid )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle( "SiHias" )
                .setSubText("Data anda diperbaharui secara realtime.")
                .setContentText( "Anda telah memulai montoring tanaman hias." )
                .setDefaults(Notification.DEFAULT_SOUND);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( channelnotif , "channel notifikasi SiHias" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. BLUE ) ;
            mBuilder.setChannelId( channelnotif ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }

    public void mulai (View view) {
        Intent intent = new Intent(MainActivity.this, Konten.class);
        startActivity(intent);
        notif();
    }

    public void tentang(View view) {
        Intent intent = new Intent(MainActivity.this, Tentang.class);
        startActivity(intent);
    }

    public void keluar(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        builder.setTitle("Konfirmasi Keluar");
        builder.setMessage("Apakah kamu yakin ingin keluar?");
        builder.setPositiveButton("Ya", (dialog, which) -> finish());
        builder.setNegativeButton("Batal", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        builder.setTitle("Konfirmasi Keluar");
        builder.setMessage("Apakah kamu yakin ingin keluar?");
        builder.setPositiveButton("Ya", (dialog, which) -> finish());
        builder.setNegativeButton("Batal", null);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Log.d(TAG, "onComplete: Failed to get token");
                }
                String token=task.getResult();
                Log.d(TAG, "onComplete: "+token);
            }
        });
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotification";
            String description = "Received from firebase";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, name, importance
            );
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void cekJaringan(View view) {
        Intent intent = new Intent(MainActivity.this, wifiManager.class);
        startActivity(intent);
    }
}