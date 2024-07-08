package com.example.chronometre2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono2;
    int countLaps=0;
    int score;
    LinearLayout conteneur;
    ImageView lapbtn,playbtn,resetbtn,pausebtn;
    Long timewhenstopped;
    boolean running=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chrono2 = (Chronometer) findViewById(R.id.chrono2);
        lapbtn = (ImageView) findViewById(R.id.lapbtn);
        playbtn = (ImageView) findViewById(R.id.playbtn);
        resetbtn = (ImageView) findViewById(R.id.resetbtn);
        pausebtn = (ImageView) findViewById(R.id.pausebtn);
        conteneur = (LinearLayout) findViewById(R.id.conteneur);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        timewhenstopped =  chrono2.getBase() - SystemClock.elapsedRealtime();
        savedInstanceState.putLong("timeWhen",timewhenstopped);
        savedInstanceState.putBoolean("isRunning",running);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Boolean  run =savedInstanceState.getBoolean("isRunning");
        long time=savedInstanceState.getLong("timeWhen");
        chrono2.setBase(SystemClock.elapsedRealtime() +time );
        if (running==true){
            chrono2.start();
            playbtn.setVisibility(View.INVISIBLE);
            pausebtn.setVisibility(View.VISIBLE);
            lapbtn.setVisibility(View.VISIBLE);
        }

    }
    public void start (View v){

        chrono2.start();
        playbtn.setVisibility(View.INVISIBLE);
        pausebtn.setVisibility(View.VISIBLE);
        lapbtn.setVisibility(View.VISIBLE);
    }

    public void stop (View v){
        chrono2.stop();
        pausebtn.setVisibility(View.INVISIBLE);
        playbtn.setVisibility(View.VISIBLE);
        resetbtn.setVisibility(View.VISIBLE);
        lapbtn.setVisibility(View.INVISIBLE);
        running=false;
    }

    public void reset (View v){
        chrono2.setBase(SystemClock.elapsedRealtime());
        playbtn.setVisibility(View.VISIBLE);
        pausebtn.setVisibility(View.INVISIBLE);
        lapbtn.setVisibility(View.INVISIBLE);
        resetbtn.setVisibility(View.INVISIBLE);
        conteneur.removeAllViews();
        countLaps=0;
    }
    public void getLaps(View v){
        countLaps++;
        LayoutInflater inflater= (LayoutInflater)
                getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView=inflater.inflate(R.layout.row,null);
        TextView textValue=(TextView) addView.findViewById(R.id.txtContent);

        textValue.setText("Lap "+countLaps+" : "+chrono2.getText());
        textValue.setTextSize(Float.parseFloat("16"));
        textValue.setPadding(30,20,0,0);
        conteneur.addView(addView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}