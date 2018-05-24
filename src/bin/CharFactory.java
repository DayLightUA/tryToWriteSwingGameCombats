package bin;

import Professions.Profession;
import Races.Race;

/**
 * Created by DayLightUA on 24.05.2018.
 */
public class CharFactory {
    private String[] names = new String[]{"Mykola", "Vasyl",
            "Oleh", "Roman", "Yura", "Dmytro", "Ivan", "Evhen", "Stepan", "Petro"};

    private String nickName;
    private Race race;
    private Profession profession;

    public void createCharacter(boolean isBot){
        if (!isBot){
            readAllParamFromGUI();
        }else{
            this.nickName = names[(int)(Math.random()*10)];
            randomRace();
            randomProf();
        }
    }

    private void readAllParamFromGUI() {
    }
}
