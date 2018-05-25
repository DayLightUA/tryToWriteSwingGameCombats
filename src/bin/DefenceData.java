package bin;

import java.io.Serializable;

/**
 * Created by DayLightUA on 25.05.2018.
 */
public class DefenceData implements Serializable {

    private int defencePoints;
    private int defenceType;
    private int whereDefence;

    private  boolean isKilled;

    public void setDefenceParametrs(int defencePoints, int defenceType, int whereDefence) {
        this.defencePoints = defencePoints;
        this.defenceType = defenceType;
        this.whereDefence = whereDefence;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public int getDefencePoints() {
        return defencePoints;
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
