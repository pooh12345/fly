package com.example.flying;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class draw extends View {
int lifec;
    private int sound;
    private int sound1;
    private int sound2;
    private SoundPool soundPool;
boolean sp = true;
    boolean sp1 = true;
    private int redx,redy,redspeed = 25;
    private Paint red = new Paint();
private Bitmap f[] = new Bitmap[2];
    private Bitmap back;
private int fishx = 10;
private int score1;
    private int fishy;
    private int fishspeed;
    private int cawi,cahe;
private boolean touch = false;
private int yellowx, yellowy,yellowspeed = 16;
    private Paint score = new Paint();
    private Paint yello = new Paint();
    private int greenx,greeny,greenspeed = 20;
    private Paint green = new Paint();
    private Bitmap life[] = new Bitmap[2];
    MediaPlayer mediaPlayer;
    private MainActivity activity;



    public draw(Context context)  {
        super(context);
        f[0]= BitmapFactory.decodeResource(getResources(),R.drawable.rsz_bird);
        f[1]= BitmapFactory.decodeResource(getResources(),R.drawable.rsz_bird2);
        back = BitmapFactory.decodeResource(getResources(),R.drawable.land);
        yello.setColor(Color.YELLOW);
        yello.setAntiAlias(false);

        green.setColor(Color.GREEN);
        green.setAntiAlias(false);

        red.setColor(Color.RED);
        red.setAntiAlias(false);
        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishy = 550;
        score1 = 0;
        lifec = 3;
        //MediaPlayer mp = MediaPlayer.create(this, R.raw.failsound);
        //mp.start();
        //soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

       // sound = soundPool.load(activity, R.raw.shoot, 1);

        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes
                    audioAttributes
                    = new AudioAttributes
                    .Builder()
                    .setUsage(
                            AudioAttributes
                                    .USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(
                            AudioAttributes
                                    .CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
        }
        else {
            soundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
        }
        sound = soundPool.load(context, R.raw.coin, 1);
        sound1 = soundPool.load(context, R.raw.combobreak, 1);
        //mediaPlayer = MediaPlayer.create(context, R.raw.ov);
        //mediaPlayer.prepare();
        //mediaPlayer.setLooping(true); // Set looping
       // mediaPlayer.setVolume(100, 100);

      //  mediaPlayer.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cawi = canvas.getWidth();
                cahe = canvas.getHeight();


        canvas.drawBitmap(back,0,0,null);
        int minfiy = f[0].getHeight();
        int maxfiy = cahe - f[0].getHeight() * 3 ;
        fishy = fishy + fishspeed;
        if (fishy < minfiy) {
            fishy = minfiy;
        }

        if (fishy > maxfiy) {
            fishy = maxfiy;
        }
        fishspeed = fishspeed + 2;
        if(touch) {
            canvas.drawBitmap(f[1],fishx,fishy,null);
            touch = false;
        }else{
            canvas.drawBitmap(f[0],fishx,fishy,null);

        }
        yellowx = yellowx - yellowspeed;
        if (hit(yellowx,yellowy)) {
            score1 = score1 + 10;
//yellowx = yellowx - 100;
            yellowx = -100;

            soundPool.play(sound, 1, 1, 0, 0, 1);
        }

        if(yellowx < 0) {
            yellowx = cawi + 21;
            yellowy = (int) Math.floor(Math.random() * (maxfiy - minfiy)) + minfiy;

        }
        canvas.drawCircle(yellowx,yellowy,30,yello);
        greenx = greenx - greenspeed;
        if (hit(greenx,greeny)) {
            score1 = score1 + 20;
//yellowx = yellowx - 100;
            greenx = -100;

            soundPool.play(sound, 1, 1, 0, 0, 1);
        }



        if(greenx < 0) {
            greenx = cawi + 21;
            greeny = (int) Math.floor(Math.random() * (maxfiy - minfiy)) + minfiy;

        }
        canvas.drawCircle(greenx,greeny,30,green);

        redx = redx - redspeed;
        if (hit(redx,redy)) {
            //score1 = score1 + 20;
//yellowx = yellowx - 100;

            soundPool.play(sound1, 1, 1, 0, 0, 1);
            redx = -100;
            lifec--;
            if (lifec == 0) {
               // mediaPlayer.stop();
                //mediaPlayer.release();
                //Toast.makeText(getContext(), "GAME OVER!", Toast.LENGTH_LONG).show();
Intent over = new Intent(getContext(), gameover.class);
                over.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                over.putExtra("score",score1);
               // over.putExtra("score",score1);
getContext().startActivity(over);








            }
        }

        if(redx < 0) {
            redx = cawi + 21;
            redy = (int) Math.floor(Math.random() * (maxfiy - minfiy)) + minfiy;

        }
        canvas.drawCircle(redx,redy,30,red);
        canvas.drawText("score " + score1,20,60,score);
        if(score1 > 200 && sp) {
            yellowspeed = yellowspeed + 10;
            greenspeed = greenspeed + 10;
            redspeed = redspeed + 10;
            sp = false;


        }

        if(score1 > 600 && sp1) {
            yellowspeed = yellowspeed + 6;
            greenspeed = greenspeed + 6;
            redspeed = redspeed + 6;
            sp1 = false;


        }
        for (int i = 0 ; i < 3 ; i++) {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if(i < lifec) {
                canvas.drawBitmap(life[0],x,y,null);

            }else{

                canvas.drawBitmap(life[1],x,y,null);
            }



        }

        //canvas.drawBitmap(f,0,0,null);

        //canvas.drawBitmap(life[0],580,10,null);
        //canvas.drawBitmap(life[0],680,10,null);
        //canvas.drawBitmap(life[0],780,10,null);

    }
    public boolean hit(int x , int y) {
if(fishx < x && x < (fishx + f[0].getWidth()) && fishy < y && y < (fishy + f[0].getHeight())) {
return true;

}
        return false;
    }


    public boolean onTouchEvent(MotionEvent e) {
      if (e.getAction() == MotionEvent.ACTION_DOWN) {
          touch = true;
          //INCLEASE TO FAST
         // fishspeed = -22;
          fishspeed = -30;
      }

return true;

    }









}
