package bin;

import Professions.Profession;
import Races.Race;


public class Character {

    private String nickName;
    private Race race;
    private Profession profession;
    private int maxHP;
    private Integer HP;
    private int maxMP;
    private Integer MP;
    private static final long HPLevelBonus=10;
    private static final long MPLevelBonus=2;
    private int level = 1;
    private int experience = 0;
    private int experienceNextLevel = 100;
    private boolean isBot;

    public Character(boolean isBot, String nickName, Race race, Profession profession) {
        this.isBot = isBot;
        this.nickName = nickName;
        this.race = race;
        this.profession = profession;
        this.maxHP = (int) (Math.random()*20)+100;
        HP = maxHP;
        this.maxMP = (int) (Math.random()*5)+25;
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

    public void restore(int restorConstant){
        if (restorConstant==Constants.RESTOR_AUTORESTOR) autorestor();
        else if (restorConstant<Constants.RESTOR_ELIXIR_MARGE) useHPElixir(restorConstant);
        else useMPElixir(restorConstant);
    }

    private void autorestor() {
        Thread restoringTread = new RestoringThread();
        restoringTread.
    }

    private void useHPElixir(int restorConstant) {
    }

    private void useMPElixir(int restorConstant) {
    }





    public long attack(){
        if (isBot) return randomAttack();
        else return playerAttack();
    }

    private int playerAttack() {
        return 0;
    }

    private int randomAttack() {
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

    public int getLevel() {
        return level;
    }

    public int getTotalKills(){
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
