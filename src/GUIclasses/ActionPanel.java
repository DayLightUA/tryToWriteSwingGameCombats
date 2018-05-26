package GUIclasses;

import Professions.Profession;
import Races.Race;
import bin.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel{

    private JComboBox skillTypeSelektor;
    private JComboBox defenceTypeSelektor;

    private JPanel strikePositionPanel;
    private ButtonGroup strikePositionSelektor;
    private JRadioButton headStrike;
    private JRadioButton bodyStrike;
    private JRadioButton groinStrike;
    private JRadioButton feetStrike;

    private JPanel defencePositionPanel;
    private ButtonGroup defencePositionSelektor;
    private JRadioButton headDefence;
    private JRadioButton bodyDefence;
    private JRadioButton groinDefence;
    private JRadioButton feetDefence;

    private JButton goButton;

    private ActionListener goButtonListener;
    private Race race;
    private Profession profession;

    public ActionPanel(ActionListener goButtonListener, Race race, Profession profession){
        this.goButtonListener = goButtonListener;
        this.race = race;
        this.profession = profession;
        initThis();
    }

    private void initThis() {
        initThisParts();
        setLayout(new GridBagLayout());
        add()
        add(strikePositionSelektor);
        add(strikePositionSelektor);
        add(strikePositionSelektor);

    }

    private void initThisParts() {
        initSkillTypeSelector();
        initDefenceTypeSelector();
        initSkillPositionSelector();
        initDefencePositionSelector();
    }

    private void initDefenceTypeSelector() {
    }

    private void initSkillPositionSelector() {
        headStrike = new JRadioButton("Head Strike:");
        headStrike.setMnemonic(Constants.HEAD_POSITION);
        bodyStrike = new JRadioButton("Body Strike:");
        bodyStrike.setMnemonic(Constants.BODY_POSITION);
        groinStrike = new JRadioButton("Groin Strike:");
        groinStrike.setMnemonic(Constants.GROIN_POSITION);
        feetStrike = new JRadioButton("Feet Strike:");
        feetStrike.setMnemonic(Constants.FEET_POSITION);
        
        strikePositionSelektor = new ButtonGroup();
        strikePositionSelektor.add(headDefence);
        strikePositionSelektor.add(bodyDefence);
        strikePositionSelektor.add(groinDefence);
        strikePositionSelektor.add(feetDefence);
        
        strikePositionPanel = new JPanel();
        strikePositionPanel.add(headDefence);
        strikePositionPanel.add(bodyDefence);
        strikePositionPanel.add(groinDefence);
        strikePositionPanel.add(feetDefence);
        strikePositionPanel.setLayout(new BoxLayout(strikePositionPanel, BoxLayout.Y_AXIS));
    }

    private void initDefencePositionSelector() {
        headDefence = new JRadioButton("Head Defence:");
        headDefence.setMnemonic(Constants.HEAD_POSITION);
        bodyDefence = new JRadioButton("Body Defence:");
        bodyDefence.setMnemonic(Constants.BODY_POSITION);
        groinDefence = new JRadioButton("Groin Defence:");
        groinDefence.setMnemonic(Constants.GROIN_POSITION);
        feetDefence = new JRadioButton("Feet Defence:");
        feetDefence.setMnemonic(Constants.FEET_POSITION);
        
        defencePositionSelektor = new ButtonGroup();
        defencePositionSelektor.add(headDefence);
        defencePositionSelektor.add(bodyDefence);
        defencePositionSelektor.add(groinDefence);
        defencePositionSelektor.add(feetDefence);
        
        defencePositionPanel = new JPanel();
        defencePositionPanel.add(headDefence);
        defencePositionPanel.add(bodyDefence);
        defencePositionPanel.add(groinDefence);
        defencePositionPanel.add(feetDefence);
        defencePositionPanel.setLayout(new BoxLayout(defencePositionPanel, BoxLayout.Y_AXIS));
    }


    public int getAttackType() {
        return skillTypeSelektor.getSelectedIndex();
    }

    public int getAttackPosition() {
        return strikePositionSelektor.getSelection().getMnemonic();
    }

    public int getDefenceType() {
        return defenceTypeSelektor.getSelectedIndex();
    }

    public int getDefencePosition() {
        return defencePositionSelektor.getSelection().getMnemonic();
    }

}
