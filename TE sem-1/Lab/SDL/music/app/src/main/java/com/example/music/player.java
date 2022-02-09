package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class player extends AppCompatActivity implements View.OnClickListener {
private ImageButton play,prev,next;
TextView curtime,maxtime,name;
private int pos;
Thread t;
Toolbar toolbar;
private AppCompatSeekBar seekbar;
    ArrayList<File>mysongs;
MediaPlayer mediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        play=findViewById(R.id.play);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);
        seekbar=findViewById(R.id.seek);
        curtime=findViewById(R.id.curtime);
        maxtime=findViewById(R.id.maxtime);
        name=findViewById(R.id.name);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (mediaplayer != null) {
            mediaplayer.stop();
        }
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaplayer!=null)
                {

                    if (mediaplayer.isPlaying())
                    {

                        Message msg = new Message();
                        msg.what = mediaplayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        sleep(1000);
                    }


                   // current=mediaplayer.getCurrentPosition();
                   // curtime.setText(createTimeLabel(total));
                    //seekbar.setProgress(current);
                }
            }
        });

        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        mysongs=(ArrayList)i.getParcelableArrayListExtra("songs");
        String sname=i.getStringExtra("name");
         pos=bundle.getInt("pos");
        Uri u= Uri.parse(mysongs.get(pos).toString());
        String same = mysongs.get(pos).getName().replace(".mp3", "").replace(".m4a", "").replace(".wav", "").replace(".m4b", "");
        name.setText(same);
        mediaplayer=MediaPlayer.create(getApplicationContext(),u);

        mediaplayer.start();
        play.setOnClickListener(this);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
       seekbar.setMax(mediaplayer.getDuration());
       String max=createTimeLabel(mediaplayer.getDuration());
       maxtime.setText(max);
       t.start();
        seekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        seekbar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            mediaplayer.seekTo(seekBar.getProgress());
            }
        });
    }

    @Override
    public void onClick(View view) {
       switch(view.getId())
       {
           case R.id.play :
               pause();
               break;
           case R.id.prev :
               previoussong();
               break;
           case R.id.next :
               nextsong();
       }
    }

    private void pause() {
        if (mediaplayer.isPlaying()){
            mediaplayer.pause();
            play.setImageResource(R.drawable.play);
        }
        else
        {
            mediaplayer.start();
            play.setImageResource(R.drawable.pause);
        }
    }
    private void previoussong()
    {
//        mediaplayer.stop();
//        mediaplayer.release();
//
//        pos=(((pos-1)<0)?(mysongs.size()-1):(pos-1))%mysongs.size();
//        Uri u=Uri.parse(mysongs.get(pos).toString());
//        mediaplayer=MediaPlayer.create(getApplicationContext(),u);
//        mediaplayer.start();
//        play.setBackgroundResource(R.drawable.pause);
        if (pos <= 0) {
            pos = mysongs.size() - 1;
        } else {
            pos--;
        }

        initPlayer(pos);
    }
    private void nextsong(){
//        mediaplayer.stop();
//        mediaplayer.release();
//        pos=((pos+1)%mysongs.size());
//        Uri u=Uri.parse(mysongs.get(pos).toString());
//        mediaplayer=MediaPlayer.create(getApplicationContext(),u);
//        mediaplayer.start();
        play.setImageResource(R.drawable.pause);
        if (pos < mysongs.size() - 1) {
            pos++;
        } else {
            pos = 0;

        }
        initPlayer(pos);

    }
    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }
    private void initPlayer(final int position) {

        if (mediaplayer != null && mediaplayer.isPlaying()) {
            mediaplayer.reset();
        }

        String sname = mysongs.get(position).getName().replace(".mp3", "").replace(".m4a", "").replace(".wav", "").replace(".m4b", "");
        name.setText(sname);
        Uri songResourceUri = Uri.parse(mysongs.get(position).toString());

        mediaplayer = MediaPlayer.create(getApplicationContext(), songResourceUri); // create and load mediaplayer with song resources
        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mediaplayer.getDuration());
                maxtime.setText(totalTime);
                seekbar.setMax(mediaplayer.getDuration());
                mediaplayer.start();
                play.setImageResource(R.drawable.pause);

            }
        });
        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int curSongPoition = position;
                // code to repeat songs until the
                if (curSongPoition < mysongs.size() - 1) {
                    curSongPoition++;
                    initPlayer(curSongPoition);
                } else {
                    curSongPoition = 0;
                    initPlayer(curSongPoition);
                }

                //playIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);

            }
        });
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            Log.i("handler ", "handler called");
            int current_position = msg.what;
            seekbar.setProgress(current_position);
            String cTime = createTimeLabel(current_position);
            curtime.setText(cTime);
        }
    };
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        mediaplayer.stop();
        super.onBackPressed();
    }
}