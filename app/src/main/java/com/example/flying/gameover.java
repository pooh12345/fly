package com.example.flying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameover extends AppCompatActivity {
private Button play;
    private Button me;

private TextView t;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        play = findViewById(R.id.button2);
        me = findViewById(R.id.button);

        String sc = getIntent().getExtras().get("score").toString();

       // int highScore = gamePrefs.getInt("HighScore", 0);


        int score = getIntent().getIntExtra("score",0);

        System.out.println(score);
SharedPreferences set = getSharedPreferences("game", Context.MODE_PRIVATE);

int high = set.getInt("hs",0);

        System.out.println(high);
if(score > high) {

    SharedPreferences.Editor editor = set.edit();
    editor.putInt("hs" , score);
    editor.commit();



}















        t = findViewById(R.id.textView5);
        t.setText("score " + sc);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(gameover.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(gameover.this,MainActivity2.class);
                startActivity(a);
                finish();
            }
        });

    }
}