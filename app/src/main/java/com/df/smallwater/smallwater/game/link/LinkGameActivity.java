package com.df.smallwater.smallwater.game.link;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;

import com.df.smallwater.smallwater.R;

public class LinkGameActivity extends Activity {
    private GameView gameView;

    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获得屏幕宽高
        Display display = getWindowManager().getDefaultDisplay();
        Config.setScreenWidth(display.getWidth());
        Config.setScreenHeight(display.getHeight());
        //设置内容布局
        setContentView(R.layout.activity_link_game);
        String configFile = "dw_config.json";
        if (TextUtils.isEmpty(configFile)) {
            finish();
            return;
        }
        gameView = findViewById(R.id.gameView);
        gameView.setTimeTv((TextView) findViewById(R.id.timeTv));
        gameView.setLevelTv((TextView) findViewById(R.id.levelTv));
        gameView.setBreakCardsBtn((Button) findViewById(R.id.breakCardsBtn));
        gameView.setNoteBtn((Button) findViewById(R.id.noteBtn));
        gameView.setPauseBtn((Button) findViewById(R.id.pauseBtn));
        GamePkg g = InnerGameReader.readGame(LinkGameActivity.this, configFile);
        //根据游戏资源包初始化游戏
        gameView.initWithGamePkg(g);

        //开始启动游戏
        gameView.showStartGameAlert();
    }



    protected void onPause() {
        gameView.pause();
        super.onPause();
    }



    protected void onResume() {
        gameView.resume();
        super.onResume();
    }
}
