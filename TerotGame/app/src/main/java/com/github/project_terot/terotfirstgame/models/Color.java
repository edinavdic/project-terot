package com.github.project_terot.terotfirstgame.models;

public enum Color {
    YELLOW,
    RED,
    BLUE,
    ORANGE,
    GREEN;

    public static Color getColor(int idx){
        if(YELLOW.ordinal() == idx) return YELLOW;
        else if(RED.ordinal() == idx) return RED;
        else if(BLUE.ordinal() == idx) return BLUE;
        else if(ORANGE.ordinal() == idx) return ORANGE;
        else if(GREEN.ordinal() == idx) return GREEN;
        else return YELLOW;
    }

    public static final int ColorsCount = 5;
}
