package Races;

import Professions.Profession;
import Professions.Berserker;
import Professions.Shaman;

public class Orc extends Race{

    public Orc() {
        name = "Orc";
        professions = new Profession[]{new Berserker(), new Shaman()};
        imgLink += "img//imgOrc.jpg";
        iconLink += "img//iconOrc.jpg";
        attackSkillName = "";
        defenceSkillName = "";
    }

    @Override
    public int useMainSkill(int level) {
        return (int)Math.round(Math.random()*(12+level));
    }

    @Override
    int useDefenceSkill(int level) {
        return ;
    }
}
