package Races;

import Professions.Profession;

public interface Race{

    long useMainSkill(long level);
    Profession[] getProf();
    String getName();

}
