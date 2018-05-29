package bin;

import java.io.Serializable;

/**
 * Created by DayLightUA on 25.05.2018.
 */
public class DefenceData implements Serializable {

    private int defencePointsForAttackPosition;
    private int defenceType;
    private int whereDefence;

    private  boolean isKilled;

    public void setDefenceParametrs(int defencePoints, int defenceType, int whereDefence) {
        this.defencePointsForAttackPosition = defencePoints;
        this.defenceType = defenceType;
        this.whereDefence = whereDefence;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public int getDefencePointsForAttackPosition() {
        return defencePointsForAttackPosition;
    }

    public int getDefenceType() {
        return defenceType;
    }

    public int getWhereDefence() {
        return whereDefence;
    }

    public boolean isKilled() {
        return isKilled;
    }
}
