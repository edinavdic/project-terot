package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //only landscape
        setContentView(R.layout.activity_game);
    }
}
