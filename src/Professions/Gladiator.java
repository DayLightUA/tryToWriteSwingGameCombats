package Professions;

public class Gladiator implements Profession {
    public String getProfName() {
        return profName;
    }

    private String profName = "Gladiator";
    @Override
    public long[] useProfSkill(long level) {
        long damage;
        long needMP;
        damage = (long) (level*(Math.random()*3-1)+Math.random()*10)+10;
        needMP = Math.round(((double) damage + (Math.random() * level * 2 - level)) / 10.0);
        System.out.println("Power sword attak");
        return new long[] {damage, needMP};
    }
}
