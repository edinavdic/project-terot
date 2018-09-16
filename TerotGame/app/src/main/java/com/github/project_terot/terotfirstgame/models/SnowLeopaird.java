package com.github.project_terot.terotfirstgame.models;

import java.io.Serializable;

public class SnowLeopaird extends Pantherai implements Serializable {

    private static final int S_STR = 15;
    private static final int S_DEX = 15;
    private static final int S_INT = 15;
    private static final String S_IMG = "snow_leopaird_sprite";
    private static final Color S_COL = Color.BLUE;

    public SnowLeopaird() {
        super(S_STR, S_DEX, S_INT, S_IMG, S_COL);
    }
}
