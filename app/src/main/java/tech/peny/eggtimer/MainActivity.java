 package tech.peny.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
     TextView timerTextView;
     SeekBar timerSeekBar;
     Boolean counterIsActive = false;
     CountDownTimer countDownTimer;
     Button goButton;

     public void resetTimer(){
         timerTextView.setText("0:30");
         timerSeekBar.setProgress(30);
         timerSeekBar.setEnabled(true);
         countDownTimer.cancel();
         goButton.setText("GO!");
         counterIsActive = false;
     }
     public void buttonClicked(View view){
            resetTimer();

         if(counterIsActive){

         }else{
             counterIsActive = true;
             timerSeekBar.setEnabled(false);
             goButton.setText("Stop!!");
             Log.i("Button Pressed", "nice");
             countDownTimer =  new CountDownTimer(timerSeekBar.getProgress() * 10000 + 100,1000){

                 @Override
                 public void onTick(long millisUntilFinished) {
                     updateTimer((int)millisUntilFinished/1000);
                 }

                 @Override
                 public void onFinish() {
                     Log.i("Finished", "Timer all done!");
                     MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.tolling);
                     mplayer.start();
                     resetTimer();
                 }
             }.start();
         }


     }

     public void updateTimer(int secondsLeft){
         int minutes = secondsLeft / 60;
         int seconds  = secondsLeft - minutes * 60;

         String secondString = Integer.toString(seconds);

         if(secondString.equals("0")){
             secondString = "00";
         }

         timerTextView.setText(Integer.toString(minutes)+ ":"+ secondString);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);

       timerTextView = findViewById(R.id.countdownTextView);
       goButton = findViewById(R.id.goButton);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}