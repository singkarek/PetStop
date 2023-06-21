package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class daftar extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button total, daftar, batal;
    Spinner jenis;
    EditText nama, durasi, perlakuan;
    TextView biaya, detailbiaya, header;
    String d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        getSupportActionBar().hide();

        dbHelper = new DataHelper(this);
        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        jenis = findViewById(R.id.jenis);
        nama = findViewById(R.id.nama);
        durasi = findViewById(R.id.durasi);
        perlakuan = findViewById(R.id.perlakuan);
        biaya = findViewById(R.id.biaya);
        detailbiaya = findViewById(R.id.detailbiaya);
        total = findViewById(R.id.btn_total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (durasi == null){
                    Toast.makeText(daftar.this, "Silahkan masukkan durasi Titip", Toast.LENGTH_LONG).show();
                }else{
                    if(jenis.getSelectedItem().toString().equals("Anjing")) {
                        int a = Integer.parseInt(durasi.getText().toString());
                        int b = 50000;
                        int c = a * b;
                        d = String.valueOf(c);
                        biaya.setText("Rp."+d+",-");
                        detailbiaya.setText("Jenis Hewan : Anjing \n(Durasi "+a+" x "+b+"/Hari)");
                    }else{
                        int a = Integer.parseInt(durasi.getText().toString());
                        int b = 30000;
                        int c = a * b;
                        d = String.valueOf(c);
                        biaya.setText("Rp."+d+",-");
                        detailbiaya.setText("Jenis Hewan : Kucing \n(Durasi "+a+" x "+b+"/Hari");
                    }
                }
            }
        });
        daftar = findViewById(R.id.btn_daftar);
        batal = findViewById(R.id.btn_batal);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dial = new Dialog(daftar.this);
                dial.setContentView(R.layout.validasi);
                dial.setTitle("Validasi Data Titip");

                TextView vjenis = dial.findViewById(R.id.valid_jenis);
                TextView vnama = dial.findViewById(R.id.valid_nama);
                TextView vdurasi = dial.findViewById(R.id.valid_durasi);
                TextView vperlakuan = dial.findViewById(R.id.valid_perlakuan);
                TextView vbiaya = dial.findViewById(R.id.valid_biaya);

                vjenis.setText(jenis.getSelectedItem().toString());
                vnama.setText(nama.getText().toString());
                vdurasi.setText(durasi.getText().toString());
                vperlakuan.setText(perlakuan.getText().toString());
                vbiaya.setText(biaya.getText().toString());

                Button valid = dial.findViewById(R.id.valid);
                Button cancel = dial.findViewById(R.id.cancel);
                valid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("insert into titip(jenis, nama, durasi, perlakuan, biaya) values('" +
                                jenis.getSelectedItem().toString() + "','" +
                                nama.getText().toString() + "','" +
                                durasi.getText().toString() + "','" +
                                perlakuan.getText().toString() + "','" +
                                d + "')");
                        Toast.makeText(daftar.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                        dial.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dial.dismiss();
                    }
                });
                dial.show();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}