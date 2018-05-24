package bin;

import java.util.Scanner;

public class Game {
    private Character player;
    private Character[] bots = new Character[5];
    private Scanner sc = new Scanner(System.in);

    public void go(){ //точка входу в ігру
        player = new Character(false);
        game();
    }

    private void game() { //сама ігра
        // цикл для проведення однієї битви,
        // з попередньою генерацією 5 противників та після якої можна вийти з гри і відновити життя
        boolean isGame = true;
        Character enemy;
        while (isGame){
            createBots();
            enemy = selectEnemy();
            batle(enemy);
            isGame = isGame();
            restoring();
        }
    }

    private void restoring() { //Запит на проведення відновлення
        System.out.println("Your status is: "+player.toString());
        System.out.println("You want to restore? (1 - Yes, 0 - No)");
        try{
            switch (sc.nextInt()){
                case 0:{
                    break;
                }
                case 1:{
                    player.restore(1); break;
                }
                case 13:{
                    player.restore(13); break;
                }
                default:{
                    System.out.println("Error... False statement! You no restored!!!");
                }
            }
        }catch (Exception e){e.printStackTrace(); restoring();}
    }

    private boolean isGame() { //запит на завершення гри
        boolean result = true;
        System.out.println("You want to Continue the game? (1 - Yes, 0 - No)");
        try{
            switch (sc.nextInt()){
                case 0:{
                    result = false; break;
                }
                case 1:{
                    result = true; break;
                }
                default:{
                    System.out.println("Error... False statement!");
                    isGame();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            isGame();
        }
        return result;
    }

    private void batle(Character enemy) { //битва з обраним ворогом, проводится до смерті одного з..
        boolean isAlive = true;
        while (isAlive){
            System.out.println("Your enemy: "+enemy.toString());
            isAlive = !enemy.getAttack(player.attack());
            if (!isAlive){
                player.killsUp();
                System.out.println("You win!!! Total kills are "+player.getTotalKills());
                player.levelUp();
                break;
            }
            isAlive = !player.getAttack(enemy.attack());
            if (!isAlive){
                System.out.println("You is killed by: "+enemy.getNickName());
                System.out.println(player.toString());
                System.out.println("You need to have been restored!!!");
                break;
            }
        }

    }

    private void createBots(){ //метод для створення віртуальних ворогів
        for (int i = 0; i < bots.length; i++){
            bots[i] = new Character(true);
            bots[i].setLevel(player.getLevel());
        }
    }

    private Character selectEnemy(){ //метод для вибору ворога для битви(нуль не верне!!! навіть при отриманні ексепшина вибере ворога з індексом 0)
        System.out.println("Please choise your Enemy");
        for (int i = 0; i< bots.length; i++){
            System.out.println("Enemy "+(i+1)+": "+bots[i].toString());
        }
        System.out.println("Enter the number of enemy:");
        int enemyIndex;
        try{
            enemyIndex = sc.nextInt() - 1;
            if (enemyIndex>=0 && enemyIndex<5){
                return bots[enemyIndex];
            } else selectEnemy();
            return null;
        } catch (Exception e){e.printStackTrace(); selectEnemy();}
        return null;
    }
}
