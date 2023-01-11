package com.example.hogwarttravels;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.navigation.NavigationView;

public class Settings extends AppCompatActivity {

    private ImageButton servicebtn2,close,textsizebtn,languagebtn,faqbtn,aboutbtn, exitbtn;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_activity);

        exitbtn = findViewById(R.id.exitbtn);
        aboutbtn = findViewById(R.id.aboutbtn);
        faqbtn = findViewById(R.id.faqbtn);
        servicebtn2 = findViewById(R.id.servicebtn);
        textsizebtn = findViewById(R.id.textsizebtn);
        languagebtn = findViewById(R.id.languagebtn);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.ImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        servicebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog();
            }
        });

        textsizebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog2();
            }
        });

        languagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog3();
            }
        });

        faqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog4();
            }
        });

        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), About.class));
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
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
                        startActivity(new Intent(getApplicationContext(), Notification.class));
                        overridePendingTransition(0, 0);
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
                        return true;
                }

                return false;
            }
        });

    }

    public void createNewDialog(){
        builder = new AlertDialog.Builder(this);
        final View serviceprivacy = getLayoutInflater().inflate(R.layout.settingsprivacy,null);

        close = (ImageButton) serviceprivacy.findViewById(R.id.close);

        builder.setView(serviceprivacy);
        dialog = builder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void createNewDialog2(){
        builder = new AlertDialog.Builder(this);
        final View serviceprivacy = getLayoutInflater().inflate(R.layout.textsize,null);

        close = (ImageButton) serviceprivacy.findViewById(R.id.close);

        builder.setView(serviceprivacy);
        dialog = builder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    public void createNewDialog3(){
        builder = new AlertDialog.Builder(this);
        final View serviceprivacy = getLayoutInflater().inflate(R.layout.language,null);

        close = (ImageButton) serviceprivacy.findViewById(R.id.close);

        builder.setView(serviceprivacy);
        dialog = builder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    public void createNewDialog4(){
        builder = new AlertDialog.Builder(this);
        final View serviceprivacy = getLayoutInflater().inflate(R.layout.faq,null);

        close = (ImageButton) serviceprivacy.findViewById(R.id.close);

        builder.setView(serviceprivacy);
        dialog = builder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}


