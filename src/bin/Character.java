package bin;

import GUIclasses.ActionPanel;
import GUIclasses.CharacterPanel;
import Professions.Profession;
import Races.Race;
import Items.Item;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


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
    private ActionPanel actionPanel;
    private List<Item> items = new LinkedList<>();

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
        if (isBot) return playerAttack(randomAttackDefenceType(), randomAttackDefencePosition());
        else return playerAttack(actionPanel.getAttackType(), actionPanel.getAttackPosition());
    }

    public DefenceData takeAttackFromEnemy(AttackData enemyAttack){
        DefenceData myDefence = new DefenceData();
        int defenceType = actionPanel.getDefenceType();
        int defencePosition = actionPanel.getDefencePosition();
        int defencePoints = calculateDefencePoints(defenceType, defencePosition, enemyAttack.getAttackType(), enemyAttack.getWhereHit());
        myDefence.setDefenceParametrs(defencePoints, defenceType, defencePosition);
        int damage = calculateDamage(enemyAttack, myDefence);
        myDefence.setKilled(damageMe(damage));
        return myDefence;
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
        int attackPositionMultipler = calculateAttackPositionMultipler(attackPosition);
        int atackTypePoints = calculateAttackTypePoints(attackType);
        return atackTypePoints*attackPositionMultipler/10;
    }

    private int calculateAttackPositionMultipler(int attackPosition) {
        switch (attackPosition){
            case Constants.HEAD_POSITION: return 13;
            case Constants.BODY_POSITION: return 10;
            case Constants.GROIN_POSITION: return 15;
            case Constants.FEET_POSITION: return 12;
            default: return 0;
        }
    }

    private int calculateAttackTypePoints(int attackType) {
        if (attackType == Constants.PHYSICAL_TYPE) return race.useMainSkill(level);
        else {
            int [] damagePointsAndMP = profession.useProfSkill(level);
            if (damagePointsAndMP[1]>= MP){
                MP -= damagePointsAndMP[0];
                return damagePointsAndMP[0];
            }
            else return 0;
        }
    }

    private int calculateDefencePoints(int defenceType, int defencePosition, int attackType, int attackPosition){
        int defencePositionMultipler = calculateDefencePositionMultipler(defencePosition, attackPosition);
        int defenceTypePoints = calculateDefenceTypePoints(defenceType, attackType);
        return defencePositionMultipler*defenceTypePoints/10;
    }

    private int calculateDefencePositionMultipler(int defencePosition, int attackPosition) {
        if (defencePosition == attackPosition) return calculateAttackPositionMultipler(defencePosition);
        else {
            if (Math.abs(defencePosition-attackPosition)==1 || Math.abs(defencePosition-attackPosition)==4)
                return calculateAttackPositionMultipler(defencePosition)/2;
            else return 1;
        }
    }

    private int calculateDefenceTypePoints(int defenceType, int attackType) {
        int defencePoints = 1;
        if (defenceType == Constants.PHYSICAL_TYPE) defencePoints = race.useDefenceSkill(level);
        if (defenceType == Constants.MAGIC_TYPE) defencePoints = profession.useDefenceSkill(level);
        if (defenceType==attackType) return defencePoints;
        else return defencePoints/2;
    }

    private int randomAttackDefenceType() {
        return (int)Math.random()*(Constants.ATTACK_TYPES);
    }
    private int randomAttackDefencePosition() {
        return (int)Math.random()*(Constants.ATTACK_POSITIONS);
    }

    private int calculateDamage(AttackData enemyAttack, DefenceData myDefence){
        int damage = enemyAttack.getAttackPoints()-myDefence.getDefencePointsForAttackPosition();
        if  (damage>=0) return damage;
        else return 0;
    }
    private boolean damageMe(int damage) {
        if (HP>damage){
            HP -= damage;
            return false;
        } else {
            HP = 0;
            return true;
        }
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

    public JPanel getCharPanel() {
        return characterGUI;
    }
}
