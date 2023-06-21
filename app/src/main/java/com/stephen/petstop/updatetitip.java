package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

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

public class updatetitip extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button total1, btnsimpan, btnkembali;
    Spinner jenis;
    EditText et_nama, et_perlakuan, et_durasi;
    TextView tv_id, tv_biaya, tv_detailbiaya, header;
    String d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatetitip);
        getSupportActionBar().hide();

        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        dbHelper = new DataHelper(this);
        tv_id = findViewById(R.id.tv_id);
        jenis = findViewById(R.id.jenis_update);
        et_nama = findViewById(R.id.et_nama);
        et_perlakuan = findViewById(R.id.et_perlakuan);
        et_durasi = findViewById(R.id.et_durasi);
        tv_biaya = findViewById(R.id.tv_biaya);
        tv_detailbiaya = findViewById(R.id.tv_detailbiaya);
        total1 = findViewById(R.id.btn_total);
        btnsimpan = findViewById(R.id.btnsimpan);
        btnkembali = findViewById(R.id.btnkembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM titip WHERE nama = '" +getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            String a = cursor.getString(1).toString();
            int b;
            if(a.equals("Anjing")){
                b = 0;
            }else{
                b = 1;
            }
            tv_id.setText(cursor.getString(0).toString());
            jenis.setSelection(b);
            et_nama.setText(cursor.getString(2).toString());
            et_durasi.setText(cursor.getString(3).toString());
            et_perlakuan.setText(cursor.getString(4).toString());
        }
        total1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jenis.getSelectedItem().toString().equals("Anjing")){
                    int a = Integer.parseInt(et_durasi.getText().toString());
                    int b = 50000;
                    int c = a * b;
                    d = String.valueOf(c);
                    tv_biaya.setText("Rp."+d+",-");
                    tv_detailbiaya.setText("Jenis Hewan : Anjing \n(Durasi "+a+" x "+b+"/Hari");
                }else{
                    int a = Integer.parseInt(et_durasi.getText().toString());
                    int b = 30000;
                    int c = a * b;
                    d = String.valueOf(c);
                    tv_biaya.setText("Rp."+d+",-");
                    tv_detailbiaya.setText("Jenis Hewan : Kucing \n(Durasi "+a+" x "+b+"/Hari");
                }
            }
        });
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update titip set jenis='"+
                        jenis.getSelectedItem().toString() + "', nama = '" +
                        et_nama.getText().toString() + "', perlakuan = '" +
                        et_perlakuan.getText().toString() + "', durasi = '" +
                        et_durasi.getText().toString() + "', biaya = '" +
                        d + "' where id='" +
                        tv_id.getText().toString() + "'");
                Toast.makeText(updatetitip.this, "Berhasil Ubah Data", Toast.LENGTH_SHORT).show();
                list.li.RefreshList();
            }
        });
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}