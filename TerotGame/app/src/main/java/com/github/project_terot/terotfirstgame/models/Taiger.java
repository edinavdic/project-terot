package com.github.project_terot.terotfirstgame.models;

import java.io.Serializable;

public class Taiger extends Pantherai implements Serializable {

    private static final int R_STR = 18;
    private static final int R_DEX = 15;
    private static final int R_INT = 15;
    private static final String R_IMG = "taiger_sprite";
    private static final Color R_COL = Color.RED;

    public Taiger() {
        super(R_STR, R_DEX, R_INT, R_IMG, R_COL);
    }
}
