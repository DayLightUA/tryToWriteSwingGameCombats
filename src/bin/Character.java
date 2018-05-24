package bin;

import Professions.Profession;
import Races.Elf;
import Races.Human;
import Races.Orc;
import Races.Race;

import java.util.Scanner;

public class Character {

    final Scanner scanner = new Scanner(System.in);

    private String nickName;
    private Race race;
    private Profession prof;
    private long maxHP;
    private long HP;
    private long maxMP;
    private long MP;
    private static final long HPLevelBonus=10;
    private static final long MPLevelBonus=2;
    private long level = 1;
    private int totalKills=0;
    private String[] names = new String[]{"Mykola", "Vasyl",
            "Oleh", "Roman", "Yura", "Dmytro", "Ivan", "Evhen", "Stepan", "Petro"};
    private boolean isBot;

    public Character(boolean isBot) {
        this.isBot = isBot;
        if (!isBot){
            System.out.println("Name is:");
            this.nickName = scanner.next();
        }else{
            this.nickName = names[(int)(Math.random()*10)];
        }
        setRace(isBot);
        setProfession(isBot);
        this.maxHP = (long) (Math.random()*20)+100;
        HP = maxHP;
        this.maxMP = (long) (Math.random()*5)+25;
        MP = maxMP;
        if (!isBot){
            System.out.println(toString());
        }

    }

    private void raceSwitch(int race){ //Використовується методом сетРайс (розбито для зменшення довжини методу)
        switch (race){
            case 1: {
                this.race = new Human(); break;
            }
            case 2: {
                this.race = new Elf(); break;
            }
            case 3: {
                this.race = new Orc(); break;
            }
            default: {
                System.out.println("Error one more pls");
                setRace(isBot);
                break;
            }
        }
    }

    private void profSwitch(int prof){ //Використовується методом сетПроф (розбито для зменшення довжини методу)
        switch (prof){
            case 0:{
                this.prof = race.getProf()[prof];
                break;
            }
            case 1: {
                this.prof = race.getProf()[prof];
                break;
            }
            default:{
                System.out.println("Error try again:");
                setProfession(isBot);
                break;
            }
        }
    }

    public void levelUp(){ //збільшує рівень та параметри якщо досягнуто певну кількість кілів
        if (totalKills>level*3) {
            maxHP+=HPLevelBonus+(long)(Math.random()*5 -2);
            maxMP+=MPLevelBonus+(long)(Math.random()*3 -1);
            level++;
            HP = maxHP;
            MP = maxMP;
            if (!isBot) {
                System.out.println("You have reached next level!!! Level: "
                        + level + "; HP: " + HP + "; MP: " + MP + ".");
            }
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

    public long attack(){ //проводиться вибір скіла та повертає значення ннанесених пошкоджень
        long damage=0;
        int attack = 0;
        if (isBot){
            if (MP>(2+Math.round(level/10.0))){
                attack = (int) (Math.random()*2);
            }
        } else {
            System.out.println("You have: "+HP+"HP; "+MP+"MP. Choose attack type (0 - Main Skill; 1 - Prof Skill):");
            attack = scanner.nextInt();
        }
        damage = skillSelection(attack);
        System.out.println("Damage = "+damage);
        return damage;
    }

    private long skillSelection(int attack){ //використовується методом атака (розбито для зменшення довжини методу)
        long damage =0;
        switch (attack){
            case 0:{
                damage = race.useMainSkill(level);
                break;
            }
            case 1:{
                long[] skillParam = prof.useProfSkill(level);
                if (skillParam[1]<=MP){
                    damage = skillParam[0];
                    MP -= skillParam[1];
                } else {
                    damage = 0;
                    System.out.println("You have enough of MP and don't attack. Next time try Main attack Skill");
                }
                break;
            } default: {
                System.out.println("Error attack choose isn't correct");
                attack();
            }
        }
        return damage;
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

    private void setProfession(boolean isBot){ //Метод для вибору провесій використовує метод профСвітч
        int prof;
        if (!isBot){
            System.out.println("Select one prof:");
            for (int i = 0; i<race.getProf().length; i++){
                System.out.println(i+" - "+ race.getProf()[i].getProfName());
            }
            prof = scanner.nextInt();
        } else {
            prof = (int) (Math.random()*2);
        }
        profSwitch(prof);
    }

    private void setRace(boolean isBot){ //Метод для вибору раси, використовує метод рейсСвітч
        int race;
        if(!isBot){
            System.out.println("Take race (1-Human, 2-Elf, 3-Orc)");
            race = scanner.nextInt();
        }else{
            race = (int) (Math.random()*3+1);
        }
        raceSwitch(race);
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
                "/ Profession: "+prof.getProfName()+
                "/level: "+level+"/ HP: "+HP+"/ MP: "+MP+"|";
        return result;
    }

}
