package com.github.project_terot.terotfirstgame.models;

import java.util.ArrayList;

public class AbilityATK extends Ability {

    private ArrayList<Color> goodAgainstList;
    private ArrayList<Color> halfGoodAgainstList;

    public AbilityATK(Color abilityColor, String colorImgPath) {
        super(abilityColor, colorImgPath);

        this.goodAgainstList = AbilityColorCalculator.giveGoodAgainst(abilityColor);
        this.halfGoodAgainstList = AbilityColorCalculator.giveHalfGoodAgainst(abilityColor);
    }
}
