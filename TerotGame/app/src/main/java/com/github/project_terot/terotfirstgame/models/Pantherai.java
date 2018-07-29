package com.github.project_terot.terotfirstgame.models;

public abstract class Pantherai {

    private int strength, dexterity, intelligence;
    private String drawablePngPath;
    private Color pantheraiColor;
    // mane/abilitiji

    public Pantherai(int strength, int dexterity, int intelligence, String drawablePngPath, Color pantheraiColor){
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.drawablePngPath = drawablePngPath;
        this.pantheraiColor = pantheraiColor;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getDrawablePngPath() {
        return drawablePngPath;
    }

    public Color getPantheraiColor() {
        return pantheraiColor;
    }
}
