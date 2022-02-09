package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_songs);
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        checkpermission();
    }
    public void checkpermission()
    {
      Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
          @Override
          public void onPermissionGranted(PermissionGrantedResponse response) {
              display();
          }

          @Override
          public void onPermissionDenied(PermissionDeniedResponse response) {

          }

          @Override
          public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
         token.continuePermissionRequest();
          }
      }).check();
    }

    private void display() {
        final ArrayList<File>songs=findsong(Environment.getExternalStorageDirectory());
        String[]items=new String[songs.size()];
        for (int i = 0; i <songs.size() ; i++) {
           items[i]=songs.get(i).getName();
        }
        ArrayAdapter <String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=listView.getItemAtPosition(i).toString();
                startActivity(new Intent(MainActivity.this,player.class).putExtra("songs",songs)
                .putExtra("songname",name).putExtra("pos",i));
            }
        });

    }

    private ArrayList<File> findsong(File f) {
        ArrayList<File>arrayList=new ArrayList<>();
        File[]files=f.listFiles();
        for (File single:files
             ) {
            if (single.isDirectory())
            {
                arrayList.addAll(findsong(single));
            }
            else {
                if(single.getName().endsWith(".mp3") || single.getName().endsWith(".m4a") || single.getName().endsWith(".wav") || single.getName().endsWith(".m4b"))
                {
                    arrayList.add(single);
                }
            }

        }
        return arrayList;
    }

    }