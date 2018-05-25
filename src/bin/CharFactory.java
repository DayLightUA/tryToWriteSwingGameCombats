package bin;

import Professions.Profession;
import Races.Elf;
import Races.Human;
import Races.Orc;
import Races.Race;

public class CharFactory {
    private String[] names = new String[]{"Mykola", "Vasyl",
            "Oleh", "Roman", "Yura", "Dmytro", "Ivan", "Evhen", "Stepan", "Petro"};

    private String nickName;
    private Race race;
    private Profession profession;

    public Character createCharacter(boolean isBot){
        if (!isBot){
            readAllParamFromGUI();
        }else{
            this.nickName = names[(int)(Math.random()*10)];
            race = randomRace();
            profession = randomProf();
        }
        return new Character(isBot, nickName, race, profession);
    }

    private Profession randomProf() {
        return race.getProfessions()[(int)Math.random()*race.getProfessions().length];
    }

    private Race randomRace(){
        switch ((int) Math.random()*3){
            case 0: return new Human();
            case 1: return  new Orc();
            case 2: return  new Elf();
            default: return new Human();
        }
    }

    private void readAllParamFromGUI() {
    }
}
