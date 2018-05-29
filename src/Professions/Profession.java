package Professions;

public interface Profession {

    long[] useProfSkill(long level);
    String getProfName();
    String getImgLink();
    String getDefenceSkillName();
    int getDefenceSkillType();
    String getAttackSkillName();
    int getAttackSkillType();

}
