package bin;

import Professions.Profession;
import Races.Race;


public class Character {

    private String nickName;
    private Race race;
    private Profession profession;
    private long maxHP;
    private long HP;
    private long maxMP;
    private long MP;
    private static final long HPLevelBonus=10;
    private static final long MPLevelBonus=2;
    private long level = 1;
    private int experience = 0;
    private int experienceNextLevel = 100;
    private boolean isBot;

    public Character(boolean isBot, String nickName, Race race, Profession profession) {
        this.isBot = isBot;
        this.nickName = nickName;
        this.race = race;
        this.profession = profession;
        this.maxHP = (long) (Math.random()*20)+100;
        HP = maxHP;
        this.maxMP = (long) (Math.random()*5)+25;
        MP = maxMP;
    }

    public void levelUp(){ //збільшує рівень та параметри якщо досягнуто певну кількість кілів
        if (experience>experienceNextLevel) {
            experienceNextLevel*=2;
            maxHP+=(HPLevelBonus+(long)(Math.random()*5 -2))*level;
            maxMP+=(MPLevelBonus+(long)(Math.random()*3 -1))*level;
            level++;
            HP = maxHP;
            MP = maxMP;
        }
    }

    public void restore(int i){ //відновлення здоров'я та мани до максимальних (присутній чіткод для моментального відновлення)
        if (i == 13){
            HP=maxHP;
            MP=maxMP;
            System.out.println("HP = "+HP+"; MP = "+MP);
        }
        if (i==1){
            System.out.println("Restoring...");
            while (HP < maxHP || MP < maxMP){
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException ex){}
                if (HP<(maxHP-3)) HP+=3;
                if (MP<maxMP) MP++;
                System.out.println("HP = "+HP+"; MP = "+MP);
            }
            HP = maxHP;
        }
        System.out.println("Character is restored!!!");
    }

    public long attack(){
        if (isBot) return randomAttack();
        else return playerAttack();
    }

    private long playerAttack() {
        return 0;
    }

    private long randomAttack() {
        return 0;
    }

    public void killsUp(){
        totalKills++;
    }

    public void setLevel(long level){
        totalKills =(int) level*3-2;
        for (int i=0; i<level; i++){
            levelUp();
        }
    }


    public boolean getAttack(long damage){ //Метод для отримання персонажем пошкодження нанесених ворогом (можна додати метод для захисних навиків)
        boolean isKilled = false;
        if (damage<HP){
            HP-=damage;
        }else{
            HP = 0;
            isKilled=true;
        }
        return isKilled;
    }

    public long getLevel() {
        return level;
    }

    public long getTotalKills(){
        return totalKills;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        String result = "NickName: "+nickName+"/ Race: "+ race.getName()+
                "/ Profession: "+ profession.getProfName()+
                "/level: "+level+"/ HP: "+HP+"/ MP: "+MP+"|";
        return result;
    }

}
