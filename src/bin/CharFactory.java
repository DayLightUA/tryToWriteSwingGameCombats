package bin;

import GUIclasses.CharacterCreationGUI;
import GUIclasses.CharacterPanel;
import Professions.Profession;
import Races.Elf;
import Races.Human;
import Races.Orc;
import Races.Race;


public class CharFactory {
    private static String[] names = new String[]{"Mykola", "Vasyl",
            "Oleh", "Roman", "Yura", "Dmytro", "Ivan", "Evhen", "Stepan", "Petro"};

    private static String nickName;
    private static Race race;
    private static Profession profession;

    public static Character createCharacter(boolean isBot){
        CharacterPanel characterPanel;
        if (!isBot){
            readAllParamFromGUI();
            characterPanel = new CharacterPanel(nickName, race, profession);
        }else{
            nickName = names[(int)(Math.random()*10)];
            race = randomRace();
            profession = randomProf();
            characterPanel = null;
        }
        return new Character(characterPanel, nickName, race, profession);
    }

    private static Profession randomProf() {
        return race.getProfessions()[(int)Math.random()*race.getProfessions().length];
    }

    private static Race randomRace(){
        switch ((int) Math.random()*3){
            case 0: return new Human();
            case 1: return  new Orc();
            case 2: return  new Elf();
            default: return new Human();
        }
    }

    private static void readAllParamFromGUI() {
        CharacterCreationGUI ccg = new CharacterCreationGUI();
        ccg.go();
        boolean haveParametres = false;
        while (!haveParametres){
            if (ccg.isSubmited()){
                nickName = ccg.getNickName();
                race = ccg.getRace();
                profession = ccg.getProfession();
                haveParametres = true;
            } else if(ccg.isClosed()){
                readAllParamFromGUI();
                haveParametres = true;
            }
        }
    }
}
