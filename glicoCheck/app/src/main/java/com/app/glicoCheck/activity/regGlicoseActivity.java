package com.app.glicoCheck.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.app.glicoCheck.R;
import com.app.glicoCheck.Util.DAOUsuario;
import com.app.glicoCheck.model.GlicoseUser;
import com.app.glicoCheck.model.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class regGlicoseActivity extends AppCompatActivity {
    CalendarView calendarView;
    private static final String TAG="CalendarActivity";
    String dateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_glicose);
       final EditText glicose = findViewById(R.id.editTextGlicose);
      // final EditText day = findViewById(R.id.editTextDay);

       calendarView = (CalendarView) findViewById(R.id.calendarView);
       calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               String date = i2+"/"+i1+"/"+i;
               Log.d(TAG,"OnSelectedDayChange: "+date);
               dateSelected = date;
           }
       });
        Button btnRegistrar = findViewById(R.id.botaoRegistrar);
        DAOUsuario dao = new DAOUsuario();
        btnRegistrar.setOnClickListener(v->{
            GlicoseUser user = new GlicoseUser(glicose.getText().toString(),dateSelected);
            dao.add(user).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Registrado com Sucesso", Toast.LENGTH_SHORT).show();
            });
        });
    }
    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
    public void goHistoricoGlicose(View v){
        Intent i = new Intent(this, HistoricoGlicoseActivity.class);
        startActivity(i);
    }
}