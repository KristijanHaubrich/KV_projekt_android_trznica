package com.example.trznica_cibalae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OfferActivity extends AppCompatActivity {

    private EditText et_ime;
    private EditText et_opis;
    private EditText et_cijena;
    private EditText et_kontakt;
    private Button btn_add;
    private TextView tv_ponuda_potražnja;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        initializeUI();
    }

    private void initializeUI() {
        btn_add = findViewById(R.id.btn_add);
        et_ime = findViewById(R.id.et_ime);
        et_opis = findViewById(R.id.et_opis);
        et_kontakt = findViewById(R.id.et_kontakt);
        et_cijena = findViewById(R.id.et_cijena);
        tv_ponuda_potražnja = findViewById(R.id.tv_ponuda_potražnja);

        ref = FirebaseDatabase.getInstance().getReference();

        if(getIntent().getIntExtra("category", 1) == 1){
            tv_ponuda_potražnja.setText("Nova ponuda");
            et_ime.setHint("Naslov ponude");
            et_opis.setHint("Opis ponude");
            et_cijena.setHint("Cijena");
        }
        if(!(getIntent().getIntExtra("category", 1) == 1)){
            tv_ponuda_potražnja.setText("Nova potražnja");
            et_ime.setHint("Naslov potražnje");
            et_opis.setHint("Opis potražnje");
            et_cijena.setHint("Najveća cijena koju ste voljni platiti");
        }

        et_kontakt.setHint("Kontakt");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ime = et_ime.getText().toString().trim();
                String opis = et_opis.getText().toString().trim();
                String cijena = et_cijena.getText().toString().trim();
                String kontakt = et_kontakt.getText().toString().trim();

                if(ime.isEmpty()){
                    et_ime.setError("Ime je nužno");
                    et_ime.requestFocus();
                }

                else if(opis.isEmpty()){
                    et_opis.setError("Opis je nužan");
                    et_opis.requestFocus();
                }

                else if(kontakt.isEmpty()){
                    et_kontakt.setError("Kontakt je nužan");
                    et_kontakt.requestFocus();
                }

                else if(cijena.isEmpty()){

                    if(!(getIntent().getIntExtra("category", 1) == 1)){
                        et_cijena.setError("Najveća cijena koju ste voljni platiti za proizvod je nužna");
                        et_cijena.requestFocus();
                    }

                    else{et_cijena.setError("Cijena je nužna");
                    et_cijena.requestFocus();}
                }

                else{
                    addToDataBase(ime, opis, cijena, kontakt);
                }
            }


        });

    }

    private void addToDataBase(String ime, String opis, String cijena, String kontakt) {

        if(getIntent().getIntExtra("category", 1) == 1){
            ref.child("Ponude").child(ime).setValue( new Job(ime,opis,cijena,kontakt));
            Toast.makeText(OfferActivity.this, "Uspješno dodana ponuda", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OfferActivity.this ,SupplyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            ref.child("Potražnja").child(ime).setValue( new Job(ime,opis,cijena, kontakt));
            Toast.makeText(OfferActivity.this, "Uspješno dodana potražnja", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OfferActivity.this ,DemandActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }
}