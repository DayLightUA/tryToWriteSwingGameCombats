package Races;

import Professions.Profession;

public interface Race{

    int useMainSkill(int level);
    Profession[] getProfessions();
    String getName();
    String getImgLink();
    String getDefenceSkillName();
    int getDefenceSkillType();
    String getAttackSkillName();
    int getAttackSkillType();
}
