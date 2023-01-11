package com.example.hogwarttravels;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MediaPlayer player;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(getApplicationContext(),R.raw.victory);
        player.setLooping(false);
    }

    public void onStart(Intent intent, int startid){
        player.start();
    }

    public void onDestroy(){
        player.stop();
    }
}
