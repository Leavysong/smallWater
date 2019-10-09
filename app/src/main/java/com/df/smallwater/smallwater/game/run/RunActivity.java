package com.df.smallwater.smallwater.game.run;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.df.smallwater.smallwater.R;

public class RunActivity extends AppCompatActivity {

    MediaPlayer menuLoop;
    private Toast loadMessage;
    private Runnable gameLauncher;
    private Intent gameIntent;
    private Handler mHandler;
    private android.widget.Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);


        loadMessage = Toast.makeText(getApplicationContext(), "loading game...", Toast.LENGTH_SHORT );
        loadMessage.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);

        gameIntent = new Intent(this, RunMainActivity.class);
        mPlayButton = (android.widget.Button)findViewById(R.id.startButton);
        mPlayButton.setClickable(true);
        mPlayButton.setEnabled(true);
        gameLauncher = new Runnable() {

            public void run() {
                mPlayButton.setClickable(false);
                mPlayButton.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                startActivityForResult(gameIntent, 0);
            }
        };

        mHandler = new Handler();
    }

    public void playGame(View view) {

        // Loading Toast
        loadMessage.show();
        Settings.SHOW_FPS = false;
        mHandler.post(gameLauncher);
    }

    public void playGameWithFPS(View view) {

        // Loading Toast
        loadMessage.show();
        Settings.SHOW_FPS = true;
        mHandler.post(gameLauncher);
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            showDialog(1);
            mHandler.postDelayed(new Runnable() {

                public void run() {
                    mPlayButton.setClickable(true);
                    mPlayButton.getBackground().clearColorFilter();
                }
            }, 10000);
        } else {
            mPlayButton.setClickable(true);
            mPlayButton.getBackground().clearColorFilter();
        }

    }


    protected Dialog onCreateDialog(int id) {
        return new AlertDialog.Builder(this)
                .setTitle("Error while changing view")
                .setMessage("System needs some time to free memory. Please try again in 10 seconds.")
                .setCancelable(true)
                .create();
    }
}
