package Races;

import Professions.Profession;

public interface Race{

    long useMainSkill(long level);
    Profession[] getProfessions();
    String getName();

}
