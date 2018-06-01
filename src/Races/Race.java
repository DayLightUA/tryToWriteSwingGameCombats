package Races;

import Professions.Profession;
import bin.Constants;
import bin.WithIconLink;

import java.util.List;

public abstract class Race implements WithIconLink{
    public static final Race[] allRaces = {new Human(), new Orc(), new Elf()};
    String name;
    String imgLink;
    String iconLink;
    String attackSkillName;
    String defenceSkillName;
    Profession[] professions;
    int defenceSkillType = Constants.PHYSICAL_TYPE;
    int attackSkillType = Constants.PHYSICAL_TYPE;

    abstract int useMainSkill(int level);
    abstract int useDefenceSkill(int level);
    public Profession[] getProfessions(){
        return professions;
    }
    public String getName(){
        return name;
    }
    public String getImgLink(){
        return imgLink;
    }
    public String getIconLink(){
        return iconLink;
    }

    public String getDefenceSkillName(){
        return defenceSkillName;
    }
    public int getDefenceSkillType(){
        return defenceSkillType;
    }
    public String getAttackSkillName(){
        return attackSkillName;
    }
    public int getAttackSkillType(){
        return attackSkillType;
    }

}
