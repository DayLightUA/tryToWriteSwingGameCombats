package Races;

import Professions.Profession;
import Professions.Berserker;
import Professions.Shaman;

public class Orc implements Race{
    private String name = "Orc";
    private Profession[] professions = {new Berserker(), new Shaman()};

    @Override
    public long useMainSkill(long level) {
        System.out.println("Axe attak");
        return Math.round(Math.random()*(12+level));
    }

    public Profession[] getProfessions() {
        return professions;
    }

    @Override
    public Profession[] getProf() {
        return professions;
    }
    @Override
    public String getName() {
        return name;
    }
}
