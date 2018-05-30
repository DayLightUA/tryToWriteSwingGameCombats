package bin;

public class RestoringThread extends Thread {

    private Character character;
    private int incrementHP;
    private int incrementMP;
    private int maxHP;
    private int maxMP;
    private int characterLevel;

    public RestoringThread(Character character) {
        super();
        this.character = character;
    }

    @Override
    public void start() {
        characterLevel = character.getLevel();
        maxHP = character.getMaxHP();
        maxMP = character.getMaxMP();
        incrementHP = maxHP/(characterLevel*10);
        incrementMP = maxMP/(characterLevel*10);
        restorCycle();
    }

    private void restorCycle() {
        boolean haveMaxHp = false;
        boolean haveMaxMP = false;
        while (haveMaxHp && haveMaxMP){
            try {
                wait(1000);
                haveMaxHp = restorPoints("HP");
                haveMaxMP = restorPoints("MP");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized boolean restorPoints(String pointsType) {
        int resultPoints;
        boolean result = true;
        if (pointsType.equals("HP")) {
            resultPoints = restorPointsCalc(character.getHP(), maxHP, incrementHP);

        }
        else if (pointsType.equals("MP")){
            resultPoints = restorPointsCalc(character.getMP(), maxMP, incrementMP);
            if (resultPoints<0) character.setMP(-resultPoints);
            else {
                character.setMP(resultPoints);
                result = false;
            }
        }
        return result;
    }

    private int restorPointsCalc (int points, int maxPoints, int increment) {
        if (points < (maxPoints+increment)){
            points += increment;
        } else {
            points = -maxPoints;
        }
        return points;
    }
}
