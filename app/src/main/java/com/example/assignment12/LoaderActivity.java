package com.example.assignment12;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class LoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {



    ListView listView;
    LoaderManager lManager;
    ArrayList<String> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        listView = findViewById(R.id.list_View);
        lManager = getLoaderManager();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }else {
            if (lManager.getLoader(1)==null){
                lManager.initLoader(1,null,this);
            }else{
                lManager.restartLoader(1,null,this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                lManager.initLoader(1,null,this);
            }
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this,ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
    }

    @SuppressLint("Range")
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.getCount()>0){
            while (data.moveToNext()){
                contacts.add(data.getString(data.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


}