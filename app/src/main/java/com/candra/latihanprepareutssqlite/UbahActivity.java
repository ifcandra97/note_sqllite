package com.candra.latihanprepareutssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candra.latihanprepareutssqlite.Database.MyDB;

public class UbahActivity extends AppCompatActivity
{

    private EditText etJudul, etDesc;
    private Button btnUpdate;
    private MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etJudul = findViewById(R.id.et_judul);
        etDesc = findViewById(R.id.et_desc);

        btnUpdate = findViewById(R.id.btn_ubah);

        Intent i = getIntent();
        String id = i.getStringExtra("varId");
        String judul = i.getStringExtra("varJudul");
        String desc = i.getStringExtra("varDesc");

        etJudul.setText(judul);
        etDesc.setText(desc);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempjudul, tempdesc;
                tempjudul = etJudul.getText().toString();
                tempdesc = etDesc.getText().toString();

                if(tempjudul.trim().equals(""))
                {
                    etJudul.setError("Judul masih kosong !");
                    etJudul.requestFocus();
                }
                else if(tempdesc.trim().equals(""))
                {
                    etDesc.setError("Deskripsi masih kosong !");
                    etDesc.requestFocus();
                }
                else
                {
                    db = new MyDB(UbahActivity.this);
                    long ex = db.ubahNote(id, tempjudul, tempdesc);

                    if(ex == -1)
                    {
                        Toast.makeText(UbahActivity.this, "Note Gagal di Ubah !", Toast.LENGTH_SHORT).show();
                        etJudul.requestFocus();
                    }
                    else {
                        Toast.makeText(UbahActivity.this, "Note Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}