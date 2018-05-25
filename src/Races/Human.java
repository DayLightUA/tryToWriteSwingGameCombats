package Races;

import Professions.Profession;
import Professions.Archer;
import Professions.Gladiator;

public class Human implements Race{
    private String name = "Human";
    private Profession[] professions = {new Gladiator(), new Archer()};

    @Override
    public long useMainSkill(long level) {
        System.out.println("Sword attak");
        return Math.round(Math.random()*(12+level));
    }

    public Profession[] getProfessions() {
        return professions;
    }
    @Override
    public String getName() {
        return name;
    }
}
