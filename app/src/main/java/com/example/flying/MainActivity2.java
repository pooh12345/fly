package com.example.flying;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private Button play1;
    MediaPlayer mediaPlayer;
    private Button credit;
    protected void onPause() {
        //mediaPlayer.stop();
       // mediaPlayer.release();

        mediaPlayer.pause();
       // length=Mediaplayer.getCurrentPosition();

        super.onPause();
    }

    public void onResume(){

        mediaPlayer.start();
        super.onResume();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main2);


        play1 = findViewById(R.id.button3);
        credit = findViewById(R.id.button4);
        mediaPlayer = MediaPlayer.create(this, R.raw.ov);
        //mediaPlayer.prepare();
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);

        mediaPlayer.start();



        TextView highScoreTxt = findViewById(R.id.textView2);

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTxt.setText("HighScore: " + prefs.getInt("hs", 0));




        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(a);
            }
        });


        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent a = new Intent(MainActivity2.this,MainActivity.class);
                //startActivity(a);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
                dialog.setTitle("credit");
                dialog.setMessage("images from pexels.com " + "" + "sound from freesound.org");
                //dialog.show();
                 dialog.show();





            }
        });









    }
}