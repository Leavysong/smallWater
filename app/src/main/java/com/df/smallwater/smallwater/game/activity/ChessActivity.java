package com.df.smallwater.smallwater.game.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.df.smallwater.smallwater.R;
import com.df.smallwater.smallwater.game.view.ChessView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChessActivity extends AppCompatActivity {

    @Bind(R.id.custon_chess_main)
    ChessView custonChessMain;
    @Bind(R.id.bt_restart)
    Button btRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_restart)
    public void onViewClicked() {
        custonChessMain.myreStart();
    }
}
