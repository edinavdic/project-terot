package com.github.project_terot.terotfirstgame.models;

import java.io.Serializable;

public class Jaguair extends Pantherai implements Serializable {

    private static final int J_STR = 15;
    private static final int J_DEX = 15;
    private static final int J_INT = 15;
    private static final String J_IMG = "jaguair_sprite";
    private static final Color J_COL = Color.GREEN;

    public Jaguair() {
        super(J_STR, J_DEX, J_INT, J_IMG, J_COL);
    }
}
