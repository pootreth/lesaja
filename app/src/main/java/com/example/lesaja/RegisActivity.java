package com.example.lesaja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegisActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGEEE = "hmm";

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
        EditText editText = (EditText)findViewById(R.id.editText5);
        String tambah = editText.getText().toString();
        final String hasil=message.concat(tambah);
        EditText editText2 = (EditText)findViewById(R.id.editText6);
        final String message2 = editText2.getText().toString();
        EditText editText3 = (EditText)findViewById(R.id.editText7);
        final String message3 = editText3.getText().toString();
        String pesan=hasil.concat("-");
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
            Intent intent = new Intent(RegisActivity.this, VerifActivity.class);
            intent.putExtra(EXTRA_MESSAGEEE,pesan);
            startActivity(intent);
        }
    }
    public void Balik(View arg0) {
        finish();
    }
}
