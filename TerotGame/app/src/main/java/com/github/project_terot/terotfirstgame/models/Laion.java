package com.github.project_terot.terotfirstgame.models;

import java.io.Serializable;

public class Laion extends Pantherai implements Serializable {

    private static final int L_STR = 15;
    private static final int L_DEX = 15;
    private static final int L_INT = 15;
    private static final String L_IMG = "laion_sprite";
    private static final Color L_COL = Color.YELLOW;

    public Laion() {
        super(L_STR, L_DEX, L_INT, L_IMG, L_COL);
    }
}
