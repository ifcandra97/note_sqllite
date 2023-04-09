package com.candra.latihanprepareutssqlite.Database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.candra.latihanprepareutssqlite.MainActivity;
import com.candra.latihanprepareutssqlite.R;
import com.candra.latihanprepareutssqlite.UbahActivity;

import java.util.ArrayList;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHolderNote>
{
    private Context ctx;
    private ArrayList arrId, arrJudul, arrDesc;

    public AdapterNote(Context ctx, ArrayList arrId, ArrayList arrJudul, ArrayList arrDesc)
    {
        this.ctx = ctx;
        this.arrId = arrId;
        this.arrJudul = arrJudul;
        this.arrDesc = arrDesc;
    }

    @NonNull
    @Override
    public ViewHolderNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_item_note, parent, false);
        return new ViewHolderNote(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNote.ViewHolderNote holder, int position)
    {
        holder.tvId.setText(arrId.get(position).toString());
        holder.tvJudul.setText(arrJudul.get(position).toString());
        holder.tvDesc.setText(arrDesc.get(position).toString());
    }

    @Override
    public int getItemCount()
    {
        if(arrId.size() == 0)
        {
            return 0;
        }
        else
        {
            return arrId.size();
        }
    }

    public class ViewHolderNote extends RecyclerView.ViewHolder
    {

        private TextView tvId, tvJudul, tvDesc;

        public ViewHolderNote(@NonNull View itemView)
        {
            super(itemView);
            tvId = itemView.findViewById(R.id.id_note);
            tvJudul = itemView.findViewById(R.id.judul_note);
            tvDesc = itemView.findViewById(R.id.desc_note);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder message = new AlertDialog.Builder(ctx);
                    message.setTitle("Peringatan !");
                    message.setMessage("Anda ingin membuat perubahan pada Note :  " + tvJudul.getText().toString());
                    message.setCancelable(true);

                    message.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent x = new Intent(ctx, UbahActivity.class);
                            x.putExtra("varId", tvId.getText().toString());
                            x.putExtra("varJudul", tvJudul.getText().toString());
                            x.putExtra("varDesc", tvDesc.getText().toString());
                            ctx.startActivity(x);
                        }
                    });

                    message.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyDB db = new MyDB(ctx);
                            long ex = db.hapusNote(tvId.getText().toString());

                            if(ex == -1)
                            {
                                Toast.makeText(ctx, "Gagal Menghapus Note !", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "Berhasil Menghapus Note", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    message.show();

                    return false;
                }
            });



        }
    }
}
