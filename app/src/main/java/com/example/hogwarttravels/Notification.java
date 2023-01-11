package com.example.hogwarttravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Notification extends AppCompatActivity {

    private TextView citynameTV, updated, temperature, temperaturefeelslike, statuss, timesunrise, timesunset, press, humi, windspeed;
    private LocationManager locationManager;
    private int PERMISSION_CODE=1;
    private String cityname;
    private Intent serviceintent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        serviceintent = new Intent(getApplicationContext(),MyService.class);
        stopService(new Intent(getApplicationContext(),MyService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.ImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.weather:
                        return true;
                    case R.id.calculator:
                        startActivity(new Intent(getApplicationContext(), Calculator.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.flashlight:
                        startActivity(new Intent(getApplicationContext(), Flashlight.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });

        timesunrise = findViewById(R.id.sunrise);
        statuss = findViewById(R.id.status);
        temperaturefeelslike = findViewById(R.id.feelslike);
        temperature = findViewById(R.id.temp);
        citynameTV = findViewById(R.id.idTVcityname);
        updated = findViewById(R.id.updatedat);
        timesunset = findViewById(R.id.sunset);
        windspeed = findViewById(R.id.wind);
        press = findViewById(R.id.pressure);
        humi = findViewById(R.id.humidity);

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Notification.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);

        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null){cityname = getCityName(location.getLongitude(),location.getLatitude());
            getWeatherInfo(cityname);
        } else {
            cityname = "London";
            getWeatherInfo(cityname);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Prolvide the permission", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private String getCityName(double longitude, double latitude){
        String cityname = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude,longitude,10);
            for (Address adr : addresses){
                if (adr!=null){
                    String city = adr.getLocality();
                    if (city!=null && !city.equals("")){
                        cityname=city;
                    }else{

                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return cityname;
    }

    private void getWeatherInfo(String cityname){
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=5dcb621b3a0e8ddb4035463afa96e52b&units=metric";

        citynameTV.setText(cityname);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat std = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH);
        String date = std.format(calendar.getTime());
        updated.setText("Updated: "+date);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String update1 = jsonObject.getJSONObject("main").getString("temp");
                    temperature.setText(update1+" °C");
                    Long update4 = jsonObject.getJSONObject("sys").getLong("sunrise");
                    Date date2 = new Date(update4*1000);
                    SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("HH:mm:ss");
                    String formattedDate2 = sdf2.format(date2);
                    timesunrise.setText(formattedDate2);
                    Long update5 = jsonObject.getJSONObject("sys").getLong("sunset");
                    Date date = new Date(update5*1000);
                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
                    String formattedDate = sdf.format(date);
                    timesunset.setText(formattedDate);
                    String update6 = jsonObject.getJSONObject("wind").getString("speed");
                    windspeed.setText(update6+" m/s");
                    String update7 = jsonObject.getJSONObject("main").getString("pressure");
                    press.setText(update7+" hpa");
                    String update8 = jsonObject.getJSONObject("main").getString("humidity");
                    humi.setText(update8 + " %");
                    String update2 = jsonObject.getJSONObject("main").getString("feels_like");
                    temperaturefeelslike.setText(update2 +" °C");
                    String update3 = jsonObject.getJSONObject("weather").getString("description");
                    statuss.setText(update3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Notification.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Notification.this);
        requestQueue.add(stringRequest);

    }

}