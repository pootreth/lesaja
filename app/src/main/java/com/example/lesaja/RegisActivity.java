package com.example.lesaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegisActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGEEE = "hmm";
    private DatabaseReference database;
    private EditText editText;
    private String pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void Pindah(View view) {
        String message="+62";
        editText = (EditText)findViewById(R.id.editText5);
        String tambah = editText.getText().toString();
        final String hasil=message.concat(tambah);
        EditText editText2 = (EditText)findViewById(R.id.editText6);
        final String message2 = editText2.getText().toString();
        EditText editText3 = (EditText)findViewById(R.id.editText7);
        final String message3 = editText3.getText().toString();
        database = FirebaseDatabase.getInstance().getReference();
        pesan=hasil.concat("-");
        pesan=pesan.concat(message2);
        pesan=pesan.concat("-");
        pesan=pesan.concat(message3);
        if(message2.length()==0 || message3.length()==0 || hasil.length()==1)
        {
            if(hasil.length()==1)
            {
                editText.setError("Harap isi Nomor Telepon Anda");
            }
            if(message2.length()==0)
            {
                editText2.setError("Harap isi Nama Anda");
            }
            if(message3.length()==0)
            {
                editText3.setError("Harap isi Email Anda");
            }
        }
        else if (hasil.length() < 11 || hasil.length() > 15 || !isEmailValid(message3)){
            if(!isEmailValid(message3)){
                editText3.setError("Email Anda tidak valid");
            }
            if(hasil.length() < 11 || hasil.length() > 15) {
                editText.setError("Nomor Telepon Anda tidak valid");
            }
        }
        else{
            database.child("Users")
                    .orderByChild("notelp")
                    .equalTo(hasil)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String clubkey="";

                            for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                clubkey = childSnapshot.getKey();
                            }
                            if (clubkey.equals("")){
                                Intent intent = new Intent(RegisActivity.this, VerifActivity.class);
                                intent.putExtra(EXTRA_MESSAGEEE,pesan);
                                startActivity(intent);

                            }
                            else{
                                editText.setError("Nomor Telepon Sudah terdaftar");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            editText.setError("Nomor Telepon Tidak terdaftar");
                        }
                    });
        }
    }
    public void Balik(View arg0) {
        finish();
    }
}
