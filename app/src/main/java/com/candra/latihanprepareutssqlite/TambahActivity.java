package com.candra.latihanprepareutssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candra.latihanprepareutssqlite.Database.MyDB;

public class TambahActivity extends AppCompatActivity
{
    private EditText etJudul, etDesc;
    private Button btnAdd;
    private MyDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        db = new MyDB(TambahActivity.this);
        etJudul = findViewById(R.id.et_judul);
        etDesc = findViewById(R.id.et_desc);
        btnAdd = findViewById(R.id.btn_simpan);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String judul, desc;
                judul = etJudul.getText().toString();
                desc = etDesc.getText().toString();

                if(judul.trim().equals(""))
                {
                    etJudul.setError("Judul masih kosong !");
                    etJudul.requestFocus();
                }
                else if(desc.trim().equals(""))
                {
                    etDesc.setError("Deskripsi masih kosong !");
                }
                else
                {
                    long ex = db.tambahNote(judul, desc);

                    if(ex == -1)
                    {
                        Toast.makeText(TambahActivity.this, "Note Gagal di Tambahkan !", Toast.LENGTH_SHORT).show();
                        etJudul.requestFocus();
                    }
                    else {
                        Toast.makeText(TambahActivity.this, "Note Berhasil di Tambahkan !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}