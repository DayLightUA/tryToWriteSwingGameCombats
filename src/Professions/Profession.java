package Professions;

import bin.Constants;

public abstract class Profession {
    String professionName;
    String iconLink = "img//";
    String defenceSkillName;
    int defenceSkillType = Constants.MAGIC_TYPE;
    String attackSkillName;
    int attackSkillType = Constants.MAGIC_TYPE;

    abstract int[] useProfSkill(int level);
    abstract int useDefenceSkill(int level);
    public String getProfessionName(){
        return professionName;
    }
    public String getImgLink(){
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
