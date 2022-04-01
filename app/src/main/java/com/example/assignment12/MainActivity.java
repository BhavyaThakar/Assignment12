package com.example.assignment12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.assignment12.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Glide.with(this)
                .load("https://images.unsplash.com/photo-1648650203419-37baac21c8a8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1364&q=80")
                .into(binding.ivShowImage);
    }

    public void cursorLoaderExample(View view) {
        Intent intent = new Intent(MainActivity.this, LoaderActivity.class);
        startActivity(intent);
    }
}