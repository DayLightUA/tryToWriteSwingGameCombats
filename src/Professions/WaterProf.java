package Professions;

public class WaterProf implements Profession {
    private String profName = "WaterProf";
    @Override
    public long[] useProfSkill(long level) {
        long damage;
        long needMP;
        damage = (long) (level*(Math.random()*3-1)+Math.random()*10)+10;
        needMP = Math.round(((double) damage + (Math.random() * level * 2 - level)) / 10.0);
        System.out.println("Water magic attak");
        return new long[] {damage, needMP};
    }

    public String getProfessionName() {
        return profName;
    }
}
