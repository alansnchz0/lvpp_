package com.lavapp.lavapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.video_background) VideoView backgroundVideo;
    @BindView(R.id.button_register) Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //inject views
        ButterKnife.bind(this);

        //get video uri
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.background);
        backgroundVideo.setVideoURI(uri);
        backgroundVideo.start();

        //attach listener to video view
        backgroundVideo.setOnPreparedListener(backgroundVideoOnPreparedListener);

        //attach listener to buttons
        registerButton.setOnClickListener(this);
    }

    MediaPlayer.OnPreparedListener backgroundVideoOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            //enable looping
            mediaPlayer.setLooping(true);
            //mute video
            mediaPlayer.setVolume(0,0);
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_register:
                Intent intent = getStartIntent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                //do nothing...
                break;
        }
    }

    public static Intent getStartIntent(Context context, Class cls) {

        return new Intent(context, cls);
    }
}
