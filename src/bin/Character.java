package bin;

import GUIclasses.CharacterPanel;
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
    private boolean isBot = false;
    private CharacterPanel characterGUI;

    public Character(CharacterPanel characterGUI, String nickName, Race race, Profession profession) {
        if (characterGUI == null)this.isBot = true;
        else this.characterGUI = characterGUI;
        this.nickName = nickName;
        this.race = race;
        this.profession = profession;
        this.maxHP = (int) (Math.random()*20)+100;
        this.maxMP = (int) (Math.random()*5)+25;
        characterGUI.setMaxHelthManaPoints(maxHP, maxMP);
        characterGUI.setHelthManaPoints(HP = maxHP, MP = maxMP);
    }

    public void levelUp(){
        if (experience>experienceNextLevel) {
            experienceNextLevel*=2;
            maxHP+=(HPLevelBonus+(int)(Math.random()*5 -2))*level;
            maxMP+=(MPLevelBonus+(int)(Math.random()*3 -1))*level;
            level++;
            HP = maxHP;
            MP = maxMP;
        }
    }

    public void restore(int restoreConstant){
        if (restoreConstant==Constants.RESTOR_AUTORESTOR) autorestor();
        else useElixir(restoreConstant);
    }

    public AttackData myAttack(){
        if (isBot) return playerAttack(randomAttackType(), randomAttackPosition());
        else return playerAttack(characterGUI.getAttackType(), characterGUI.getAttackPosition());
    }

    public DefenceData takeAttackFromEnemy(AttackData enemyAttack){
        DefenceData myDefence = new DefenceData();
        int defenceType = characterGUI.getDefenceType();
        int defencePosition = characterGUI.getDefencePosition();
        int defencePoints = calculateDefencePoints(defenceType, defencePosition);
        myDefence.setDefenceParametrs(defencePoints, defenceType, defencePosition);
        calculateDamage(enemyAttack, myDefence);
        return myDefence;
    }

    public void setLevel(long level){
        int maxExp = experienceNextLevel;
        for (int i = this.level; i<=level; i++) maxExp*=2;
        experience = maxExp-1;
        for (int i=0; i<level; i++){
            levelUp();
        }
    }

    public int getLevel() {
        return level;
    }

    public String getNickName() {
        return nickName;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    @Override
    public String toString() {
        String result = "NickName: "+nickName+"/ Race: "+ race.getName()+
                "/ Profession: "+ profession.getProfName()+
                "/level: "+level+"/ HP: "+HP+"/ MP: "+MP+"|";
        return result;
    }



    private void autorestor() {
        Thread restoringTread = new RestoringThread(this);
        restoringTread.start();
    }
    private void useElixir(int restoreConstant) {
        int restorePoints;
        restorePoints = switchOfElixirSize(Math.abs(restoreConstant));
        if (restoreConstant>0){
            HP = ((HP+restorePoints)>maxHP ? maxHP : (HP + restorePoints));
        } else {
            MP = ((MP+restorePoints/2)>maxMP ? maxMP : (MP + restorePoints/2));
        }
    }
    private int switchOfElixirSize(int restoreConstantAbs) {
        switch (restoreConstantAbs){
            case Constants.RESTOR_SMALL_HP_ELIXIR: return Constants.SMALL_ELIXIR_POINTS;
            case Constants.RESTOR_MIDLE_HP_ELIXIR: return Constants.MIDLE_ELIXIR_POINTS;
            case Constants.RESTOR_BIG_HP_ELIXIR: return Constants.BIG_ELIXIR_POINTS;
            case Constants.RESTOR_FULL_HP_ELIXIR: return maxHP;
            default: return 0;
        }
    }


    private AttackData playerAttack(int attackType, int attackPosition) {
        AttackData myAttack = new AttackData();
        int attackPoints = calculateAttackPoints(attackType, attackPosition);
        myAttack.setAttackParametrs(attackPoints, attackType, attackPosition);
        return myAttack;
    }
    private int calculateAttackPoints(int attackType, int attackPosition) {

    }
    private int randomAttackType() {
        return (int)Math.random()*(Constants.ATTACK_TYPES);
    }
    private int randomAttackPosition() {
        return (int)Math.random()*(Constants.ATTACK_POSITIONS);
    }

    private int calculateDefencePoints(int defenceType, int defencePosition){

    }

    private int calculateDamage(AttackData myAttack, DefenceData myDefence){

    }




}
