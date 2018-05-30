package Professions;

public interface Profession {

    int[] useProfSkill(int level);
    String getProfName();
    String getImgLink();
    String getDefenceSkillName();
    int getDefenceSkillType();
    String getAttackSkillName();
    int getAttackSkillType();
    int useDefenceSkill(int level);
}
