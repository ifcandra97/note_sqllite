package com.candra.latihanprepareutssqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.candra.latihanprepareutssqlite.Database.AdapterNote;
import com.candra.latihanprepareutssqlite.Database.MyDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private MyDB db;
    private FloatingActionButton fabTambah;
    private RecyclerView rvNote;
    private AdapterNote adNote;
    private ArrayList<String> arrId, arrJudul, arrDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNote = findViewById(R.id.rv_note);

        db = new MyDB(MainActivity.this);
        fabTambah = findViewById(R.id.fab_add);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        tampilNote();
    }

    private void MasukDataKeArrayListDariSQLITE()
    {
        Cursor c = db.bacaNote();

        if(c.getCount() == 0)
        {
            Toast.makeText(this, "Tidak ada note !", Toast.LENGTH_SHORT).show();
        }
        else {
            while(c.moveToNext())
            {
                arrId.add(c.getString(0));
                arrJudul.add(c.getString(1));
                arrDesc.add(c.getString(2));
            }
        }
    }

    private void tampilNote()
    {
        arrId = new ArrayList<>();
        arrJudul = new ArrayList<>();
        arrDesc = new ArrayList<>();

        MasukDataKeArrayListDariSQLITE();

        adNote = new AdapterNote(MainActivity.this, arrId, arrJudul, arrDesc);
        rvNote.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvNote.setAdapter(adNote);

    }
}
