package GUIclasses;

import Professions.Profession;
import Races.Race;

import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends JPanel{

    private ImageIcon raceImg;
    private ImageIcon professionImg;
    private JLabel nickName;



    public CharacterPanel(String nickName, Race race, Profession profession) {
        super(new BorderLayout());
        raceImg = new ImageIcon(race.getImgLink());
        professionImg = new ImageIcon(profession.getIconLink());
        JPanel icon = new JPanel();
        icon.setSize(225,400);
        Graphics g = icon.getGraphics();
        g.drawImage(raceImg.getImage(), 0, 0, null);
        g.drawImage(professionImg.getImage(), 175, 0, null);
        this.add(icon, BorderLayout.CENTER);
        this.nickName = new JLabel(nickName);
    }

    public void setMaxHelthManaPoints(int maxHP, int maxMP) {
    }

    public void setHelthManaPoints(Integer integer, Integer integer1) {
    }


}
