package Professions;

public class FireProf implements Profession {
    public String getProfName() {
        return profName;
    }

    private String profName = "FireProf";
    @Override
    public long[] useProfSkill(long level) {
        long damage;
        long needMP;
        damage = (long) (level*(Math.random()*3-1)+Math.random()*10)+10;
        needMP = Math.round(((double) damage + (Math.random() * level * 2 - level)) / 10.0);
        System.out.println("Fire magic attak");
        return new long[] {damage, needMP};
    }
}
