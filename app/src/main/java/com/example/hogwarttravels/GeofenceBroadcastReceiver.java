package com.example.hogwarttravels;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReceiv";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
      //  Toast.makeText(context, "Geofence Triggered", Toast.LENGTH_SHORT).show();


        NotificationHelper notificationHelper = new NotificationHelper(context);

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        
        if (geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: ERROR");
            return;
        }

        List<Geofence> geofenceList =  geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence: geofenceList){
            Log.d(TAG, "onReceive: " + geofence.getRequestId());
        }

        Location location = geofencingEvent.getTriggeringLocation();
        
        int transitionType = geofencingEvent.getGeofenceTransition();

        switch(transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Dotarłeś/aś do celu","",Notification.class);
                break;
                case Geofence.GEOFENCE_TRANSITION_DWELL:
                    Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
                    notificationHelper.sendHighPriorityNotification("Dotarłeś/aś do celu","",Notification.class);
                    break;
        }
    }
}