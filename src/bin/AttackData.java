package bin;

import java.io.Serializable;

/**
 * Created by DayLightUA on 25.05.2018.
 */
public class AttackData implements Serializable{

    private int attackPoints;
    private int attackType;
    private int whereHit;


    public void setAttackParametrs(int attackPoints, int attackType, int whereHit) {
        this.attackPoints = attackPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getAttackType() {
        return attackType;
    }

    public int getWhereHit() {
        return whereHit;
    }
}
