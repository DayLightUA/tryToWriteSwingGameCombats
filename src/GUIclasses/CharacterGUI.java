package GUIclasses;

import Professions.Profession;
import Races.Race;

import javax.swing.*;
import java.awt.*;

public class CharacterGUI extends JPanel{

    private Image raceImg;
    private Image professionImg;


    public CharacterGUI(String nickName, Race race, Profession profession) {
        super(new BorderLayout());
        raceImg = Toolkit.getDefaultToolkit().createImage(race.getImgLink());
        professionImg = Toolkit.getDefaultToolkit().createImage(profession.getImgLink());
        JPanel icon = new JPanel();
        icon.setSize(200,400);
        Graphics g = icon.getGraphics();
        g.drawImage(raceImg, 0, 0, null);
        g.drawImage(professionImg, 0, 0, null);
        this.add(icon, BorderLayout.CENTER);

    }

    public void setMaxHelthManaPoints(int maxHP, int maxMP) {
    }

    public void setHelthManaPoints(Integer integer, Integer integer1) {
    }

    public int getAttackType() {
        return attackType;
    }

    public int getAttackPosition() {
        return attackPosition;
    }

    public int getDefenceType() {
        return defenceType;
    }

    public int getDefencePosition() {
        return defencePosition;
    }
}
