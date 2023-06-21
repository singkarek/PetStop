package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnAbout, btnDaftar, btnHelp;
    TextView footer, header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        footer = findViewById(R.id.ftr);
        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/PinkyPromise.ttf");
        header.setTypeface(head);
        footer.setTypeface(type);
        btnDaftar = findViewById(R.id.btnDaftar);
        btnAbout = findViewById(R.id.btnAbout);
        btnHelp = findViewById(R.id.btnHelp);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.about);
                dialog.setTitle("Tentang Aplikasi");

                Button ok = dialog.findViewById(R.id.bt_ok);
                TextView tek3 = dialog.findViewById(R.id.tek3);
                TextView tek4 = dialog.findViewById(R.id.tek4);
                TextView tek5 = dialog.findViewById(R.id.tek5);
                TextView tek6 = dialog.findViewById(R.id.tek6);
                tek3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ig = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/singkarek?igshid=YmMyMTA2M2Y="));
                        startActivity(ig);
                    }
                });
                tek4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/FStephen14"));
                        startActivity(fb);
                    }
                });
                tek5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent ln = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/fierda-stephen-383993195"));
                        startActivity(ln);
                    }
                });
                tek6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:fierdastephen14@gmail.com"));
                        startActivity(mail);
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dftr = new Intent(MainActivity.this, titip.class);
                startActivity(dftr);
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hlp = new Intent(MainActivity.this, help.class);
                startActivity(hlp);
            }
        });
    }
}