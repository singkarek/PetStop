package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class titip extends AppCompatActivity {
    Button daftar, list;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titip);
        getSupportActionBar().hide();

        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        list = findViewById(R.id.btn_list);
        daftar = findViewById(R.id.btn_daftar2);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(titip.this, daftar.class);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(titip.this, list.class);
                startActivity(in);
            }
        });
    }
}
