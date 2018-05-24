package bin;

/**
 * Created by DayLightUA on 24.05.2018.
 */
public class RestoringThread extends Thread {

    private Character character;
    private int incrementHP;
    private int incrementMP;
    private int characterLevel;

    public RestoringThread(Character character) {
        super();
        this.character = character;
    }

    @Override
    public synchronized void start() {
        characterLevel = character.getLevel();
        incrementHP = character.getMaxHP();
        incrementMP = character.getMaxMP();

        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
