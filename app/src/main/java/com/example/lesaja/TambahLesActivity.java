package com.example.lesaja;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lesaja.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ValueEventListener;

import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.text.TextUtils;

import static android.text.TextUtils.isEmpty;

public class TambahLesActivity extends AppCompatActivity {

    private Button btSubmit;
    private EditText edittext ;
    private Calendar myCalendar ;
    private EditText chooseTime;
    private DatabaseReference database;
    private String matapel;
    private String tingkat;
    private String uid;
    private int keyss;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_les);

        edittext = (EditText) findViewById(R.id.Birthday);
        myCalendar = Calendar.getInstance();
        chooseTime = findViewById(R.id.Time);
        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeFragment.EXTRA_MESSAGEE);
        String[] parts = message.split("-");
        matapel = parts[0];
        tingkat = parts[1];
        ConstraintLayout root = (ConstraintLayout) findViewById(R.id.root);
        if (tingkat.equals("SD")) {
            root.setBackgroundColor(Color.parseColor("#B51D1C"));
        }
        if (tingkat.equals("SMP"))
        {
            root.setBackgroundColor(Color.parseColor("#1A2380"));
        }
        if(tingkat.equals("SMA"))
        {
            root.setBackgroundColor(Color.parseColor("#2CB5FA"));
        }

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            private void updateLabel() {
                String myFormat = "EEE, dd MMM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        edittext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TambahLesActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(TambahLesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String hourtext;
                        String minuttext;
                        if(hourOfDay<10){
                            hourtext="0"+hourOfDay;
                        }
                        else {
                            hourtext=hourOfDay+"";
                        }
                        if(minutes <10){
                            minuttext="0"+minutes;
                        }
                        else{
                            minuttext=minutes+"";
                        }
                        chooseTime.setText(hourtext + ":" + minuttext);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });

        btSubmit = (Button) findViewById(R.id.button4);
        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(edittext.getText().toString()) &&
                        !isEmpty(chooseTime.getText().toString())){
                    submitLes(new Les(edittext.getText().toString(),
                            chooseTime.getText().toString(), matapel, tingkat, uid));
                    }
                else {
                    Snackbar.make(findViewById(R.id.button4), "Data Les tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            chooseTime.getWindowToken(), 0);
                }
            }
        });


    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void submitLes(Les les) {

        database.child("les").push().setValue(les).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                edittext.setText("");
                chooseTime.setText("");
                Snackbar.make(findViewById(R.id.button4), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public void Balik(View arg0) {

        finish();

    }
    }







