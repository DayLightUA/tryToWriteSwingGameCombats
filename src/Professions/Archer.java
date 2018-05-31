package Professions;

public class Archer extends Profession{

    private String profesionName = "Archer";
    icon

    @Override
    public int[] useProfSkill(int level) {
        int damage;
        int needMP;
        damage = (int) (level*(Math.random()*3-1)+Math.random()*10)+10;
        needMP =(int) Math.round(((double) damage + (Math.random() * level * 2 - level)) / 10.0);
        return new int[] {damage, needMP};
    }

    @Override
    int useDefenceSkill(int level) {
        return 0;
    }
}
