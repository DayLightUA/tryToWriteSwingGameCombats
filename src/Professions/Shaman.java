package Professions;

public class Shaman implements Profession {
    public String getProfessionName() {
        return profName;
    }

    private String profName = "Shaman";
    @Override
    public long[] useProfSkill(long level) {
        long damage;
        long needMP;
        damage = (long) (level*(Math.random()*3-1)+Math.random()*10)+10;
        needMP = Math.round(((double) damage + (Math.random() * level * 2 - level)) / 10.0);
        System.out.println("Nature magic attak");
        return new long[] {damage, needMP};
    }
}
