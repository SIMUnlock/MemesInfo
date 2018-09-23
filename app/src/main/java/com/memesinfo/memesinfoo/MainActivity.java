package com.memesinfo.memesinfoo;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se crea el fragment con el container
        getSupportFragmentManager()
                .beginTransaction().replace (R.id.container, new ListadoFragment ())
                .commit ();
    }
}
