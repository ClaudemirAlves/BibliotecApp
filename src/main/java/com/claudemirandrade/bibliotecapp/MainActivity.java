package com.claudemirandrade.bibliotecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.claudemirandrade.bibliotecapp.livro.MainLivroFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new MainLivroFragment()).commit();
        }
    }
}