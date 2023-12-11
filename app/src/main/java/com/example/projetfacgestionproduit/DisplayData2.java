package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData2 extends AppCompatActivity {
    private int[] imageIds = {R.drawable.aa, R.drawable.bb, R.drawable.cc, R.drawable.dd};
    Helperr helperr;
    SQLiteDatabase sqLiteDatabase;
    String[]item;
    int[]id;
    ListView listView;
    Button confirmation,Annuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data2);

        Log.d("DisplayData2", "onCreate called");
        helperr=new Helperr(DisplayData2.this);

        findid();
        displayData();
        confirmation.setOnClickListener(v -> {
            Intent i1 =new Intent(DisplayData2.this, confirmation.class);
            startActivity(i1);
           // supprimerToutesLesDonnees();

        });
        Annuler.setOnClickListener(v -> {
            Intent i2 =new Intent(DisplayData2.this, Produit.class);
            startActivity(i2);
        });
        helperr.deleteAllProducts();
    }

   /* private void supprimerToutesLesDonnees() {
        helperr = new Helperr(DisplayData2.this);
        SQLiteDatabase db = helperr.getWritableDatabase();
        db.delete("cartes", null, null);
        db.close();
    }*/
    private void displayData() {

        sqLiteDatabase=helperr.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from cartes",null);
        if(cursor.getCount()>0)
        {id=new int[cursor.getCount()];
            item=new String[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                id[i]=cursor.getInt(0);
                item[i]= cursor.getString(1);
                i++;
            }
            Custom custom=new Custom();
            listView.setAdapter(custom);
            /*listView.setOnItemClickListener((parent, view, position, id) -> {
                // Appeler la méthode pour afficher le dialogue de confirmation de suppression
                showDeleteConfirmationDialog(position);
            });*/
        }
        }






   /*private void showDeleteConfirmationDialog(final int position) {
        // Utilisez une boîte de dialogue (AlertDialog) pour obtenir la confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez-vous supprimer cet élément?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    // Supprimer l'élément de la base de données et mettre à jour la liste
                    deleteItem(position);
                })
                .setNegativeButton("Non", (dialog, which) -> {
                    // Annuler la suppression
                    dialog.dismiss();
                });

        // Afficher la boîte de dialogue
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/
    /*private void deleteItem(int position) {
        // Supprimer l'élément de la base de données
        sqLiteDatabase = helperr.getWritableDatabase();
        int deletedRows = sqLiteDatabase.delete("cartes", "id=?", new String[]{String.valueOf(id[position])});

        if (deletedRows > 0) {
            Toast.makeText(DisplayData2.this, "L'élément a été supprimé", Toast.LENGTH_LONG).show();

            // Mettre à jour la liste après la suppression
            displayData();
        } else {
            Toast.makeText(DisplayData2.this, "Erreur lors de la suppression", Toast.LENGTH_LONG).show();
        }
    }*/









    private void findid() {
        listView=(ListView)findViewById(R.id.lv2);
        confirmation = findViewById(R.id.confimation);
        Annuler = findViewById(R.id.Annuler);
    }

    private class Custom extends BaseAdapter {


        @Override
        public int getCount() {
            return item.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = LayoutInflater.from(DisplayData2.this).inflate(R.layout.singledata2, parent, false);

            TextView textView = itemView.findViewById(R.id.textView2);
            ImageView imageView = itemView.findViewById(R.id.imageView2);

            // Mise à jour du texte
            textView.setText(item[position]);

            int imageIndex = position % imageIds.length;
            imageView.setImageResource(imageIds[imageIndex]);

            return itemView;

        }
    }
}