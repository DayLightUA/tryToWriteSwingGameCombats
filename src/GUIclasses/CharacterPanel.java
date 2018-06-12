package GUIclasses;

import Professions.Profession;
import Races.Race;

import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends JPanel{

    private ImageIcon raceImg;
    private ImageIcon professionImg;
    private JLabel nickName;
    private JPanel icon;
    private JPanel statusBar;
    private int maxHP;
    private int maxMP;
    private Graphics statusBarGraphics;



    public CharacterPanel(String nickName, Race race, Profession profession) {
        super(new BorderLayout());
        raceImg = new ImageIcon(race.getImgLink());
        professionImg = new ImageIcon(profession.getIconLink());
        icon = new JPanel();
        icon.setSize(225,400);
        Graphics g = icon.getGraphics();
        g.drawImage(raceImg.getImage(), 0, 0, null);
        g.drawImage(professionImg.getImage(), 175, 0, null);
        this.add(icon, BorderLayout.CENTER);
        this.nickName = new JLabel(nickName);
        statusBar = new JPanel();
        statusBar.setSize(225, 30);
        statusBar.add(this.nickName, BorderLayout.NORTH);
    }

    public void setMaxHelthManaPoints(int maxHP, int maxMP) {
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        if (statusBarGraphics!=null) statusBarGraphics.dispose();
        statusBarGraphics = statusBar.getGraphics();
        statusBarGraphics.setColor(new Color(100, 10, 10));
        statusBarGraphics.drawRect(10, 10, 200, 5);
        char[] maxHPchars = (maxHP+"HP").toCharArray();
        statusBarGraphics.drawChars(maxHPchars, 0, maxHPchars.length, 211, 10 );
        statusBarGraphics.setColor(new Color(10, 10, 100));
        statusBarGraphics.drawRect(10, 20, 200, 5);
        char[] maxMPchars = (maxMP+"MP").toCharArray();
        statusBarGraphics.drawChars(maxMPchars, 0, maxMPchars.length, 211, 20 );
    }

    public void setHelthManaPoints(int HP, int MP) {
        if (statusBarGraphics==null) statusBarGraphics = statusBar.getGraphics();
        statusBarGraphics.setColor(new Color(240, 0, 0));
        statusBarGraphics.drawRect(10, 10, calcPointPercents(HP, maxHP)*2, 5);
        statusBarGraphics.setColor(new Color(0, 0, 240));
        statusBarGraphics.drawRect(10, 20, calcPointPercents(MP, maxMP)*2, 5);
        statusBarGraphics.setColor(Color.black);
        statusBarGraphics.drawChars((calcPointPercents(HP, maxHP)+"%").toCharArray(), 0, 3, 100, 10 );
        statusBarGraphics.drawChars((calcPointPercents(MP, maxMP)+"%").toCharArray(), 0, 3, 211, 20 );
    }

    private int calcPointPercents(int points, int maxpoints) {
        return Math.round(points*100/maxpoints);
    }


}
