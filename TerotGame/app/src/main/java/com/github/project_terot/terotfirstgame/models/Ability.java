package com.github.project_terot.terotfirstgame.models;

import java.util.ArrayList;

public abstract class Ability {

    // static class
    public static class AbilityColorCalculator {

        public static double coeffMat[][] = {
                {1, 1, 1.5, 2, 1},
                {1.5, 1, 1, 1, 2},
                {1, 2, 1, 1, 1.5},
                {1, 1.5, 2, 1, 1},
                {2, 1, 1, 1.5, 1}
        };

        // TODO: public methods inside public nested class, maybe to use them in other classes

        public static ArrayList<Color> giveGoodAgainst(Color col){
            int idx = col.ordinal();
            ArrayList<Color> returnList = new ArrayList<>();
            for(int j = 0; j < coeffMat.length; j++){
                if(coeffMat[idx][j] == 2) returnList.add(Color.getColor(j));
            }
            return returnList;
        }

        public static ArrayList<Color> giveWeakAgainst(Color col){
            int idx = col.ordinal();
            ArrayList<Color> returnList = new ArrayList<>();
            for(int i = 0; i < coeffMat.length; i++){
                if(coeffMat[i][idx] == 2) returnList.add(Color.getColor(i));
            }
            return returnList;
        }

        public static ArrayList<Color> giveHalfGoodAgainst(Color col){
            int idx = col.ordinal();
            ArrayList<Color> returnList = new ArrayList<>();
            for(int j = 0; j < coeffMat.length; j++){
                if(coeffMat[idx][j] == 1.5) returnList.add(Color.getColor(j));
            }
            return returnList;
        }

        public static ArrayList<Color> giveHalfWeakAgainst(Color col){
            int idx = col.ordinal();
            ArrayList<Color> returnList = new ArrayList<>();
            for(int i = 0; i < coeffMat.length; i++){
                if(coeffMat[i][idx] == 1.5) returnList.add(Color.getColor(i));
            }
            return returnList;
        }

    }


    // ovo sve suvisno ovdje, good i halfgood trebaju samo u ATK ability,--SIGURNO nece trebati jer ce se weak i halfWeak racunati na osnovu boje zivotinje a ne DEF argumenta-- weak i halfWeak u DEF( mada i to myb suvisno jer ce se preko ATK sve racunati), dok u MIDu ne treba
    // ona nested klasa ce posluziti mozda u Pantherai
    private Color abilityColor;
    private String colorImgPath;
    //private ArrayList<Color> goodAgainstList;
    //private ArrayList<Color> weakAgainstList;
    //private ArrayList<Color> halfGoodAgainstList;
    //private ArrayList<Color> halfWeakAgainstList;


    public Ability(Color abilityColor, String colorImgPath) {
        this.abilityColor = abilityColor;
        this.colorImgPath = colorImgPath;
    }

    public Color getAbilityColor() {
        return abilityColor;
    }

    public String getColorImgPath() {
        return colorImgPath;
    }

}
