package Races;

import Professions.Profession;
import Professions.FireProf;
import Professions.WaterProf;

public class Elf implements Race {
    private String name = "Elf";
    private Profession[] professions = {new FireProf(), new WaterProf()};

    @Override
    public long useMainSkill(long level) {
        System.out.println("Bow attak");
        return Math.round(Math.random()*(12+level));
    }

    @Override
    public Profession[] getProfessions() {
        return professions;
    }

    @Override
    public String getName() {
        return name;
    }
}
