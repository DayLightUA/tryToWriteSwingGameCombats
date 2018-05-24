package GUIclasses;

/**
 * Created by DayLightUA on 24.05.2018.
 */
public class GameGUI {
    private static GameGUI instanceOfGame;

    private GameGUI(){}

    public static GameGUI getInstance(){
        if (instanceOfGame == null) return instanceOfGame = new GameGUI();
        else return instanceOfGame;
    }

    public void go() {
    }

    public void close() {
    }
}
