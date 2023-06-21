package com.stephen.petstop;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class help extends AppCompatActivity {
    WebView webview;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().hide();
        header = findViewById(R.id.hdr);
        Typeface head = Typeface.createFromAsset(getAssets(), "fonts/HappySundayRegular.ttf");
        header.setTypeface(head);
        webview = findViewById(R.id.webview);
        WebSettings webViewSettings = webview.getSettings();
        webViewSettings.setDefaultFontSize(12);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/bantuan.html");
    }
}