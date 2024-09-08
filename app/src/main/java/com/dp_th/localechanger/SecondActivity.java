package com.dp_th.localechanger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import com.dp.localechanger.LocaleCompatActivity;
import com.dp.localechanger.Locales;

import java.util.Locale;

public class SecondActivity extends LocaleCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        setTitle(R.string.title);

        findViewById(R.id.toENButton).setOnClickListener(view -> updateLocale(new Locale("en")));
        findViewById(R.id.toItButton).setOnClickListener(view -> updateLocale(new Locale("it")));
        findViewById(R.id.toArButton).setOnClickListener(view -> updateLocale(Locales.INSTANCE.getArabic()));

        findViewById(R.id.backButton).setOnClickListener(view -> finish());
    }
}