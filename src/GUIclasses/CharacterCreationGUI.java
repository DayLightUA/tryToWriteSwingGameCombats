package GUIclasses;


import Professions.Profession;
import Races.Race;
import bin.WithIconLink;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CharacterCreationGUI {
    private JFrame creationFrame;
    private JPanel creationPanel;
    private Box racesBox;
    private Box professionsBox;
    private Box selectionBox;
    private Box buttonBox;
    private JTextField nickNameField;
    private JButton submitButton;
    private JButton cancelButton;
    private ButtonGroup racesBGroup;
    private ButtonGroup professionsBGroup;
    private List<Race> races;
    private List<Profession> professions;

    private boolean isSubmited;
    private boolean isClosed;

    public void go(){
        creationFrame = new JFrame("Create the Character!!!");
        creationPanel = new JPanel();
        submitButton = new JButton("Submit!");
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSubmited();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClosed();
            }
        });
        buttonBox = Box.createHorizontalBox();
        buttonBox.add(submitButton);
        buttonBox.add(cancelButton);
        initRaces();
        initProfessions();
        creationFrame.setSize(400, 600);
        creationFrame.setVisible(true);
    }

    private void initRaces() {
        racesBox = Box.createHorizontalBox();
        racesBGroup = new ButtonGroup();
        races = new ArrayList<>();
        int i = 0;
        for (Race r:Race.allRaces){
            races.add(r);
            JButton button = createButton(r, i);
            button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initProfessions();
                }
            });
            racesBGroup.add(button);
            racesBox.add(button);
        }
        ((JButton)racesBox.getComponent(0)).setSelected(true);
    }

    private void initProfessions() {
        Profession[] profs = takeProfessionsOfSelectedRace();
        professionsBox = Box.createHorizontalBox();
        professionsBGroup = new ButtonGroup();
        int i = 0;
        for (Profession p:profs){
            professions.add(p);
            JButton button = createButton(p, i);
            professionsBGroup.add(button);
            professionsBox.add(button);
        }
        ((JButton)professionsBox.getComponent(0)).setSelected(true);
    }

    private Profession[] takeProfessionsOfSelectedRace() {
        Profession[] professions = null;
        for (JButton raceButton: (JButton[]) racesBox.getComponents()){
            if (raceButton.isSelected()){
                Race race = races.get(raceButton.getMnemonic());
                professions = race.getProfessions();
            }
        }
        return professions;
    }

    private JButton createButton(WithIconLink linkObj, int i) {
        ImageIcon noSelectButtonIcon = new ImageIcon(linkObj.getIconLink());
        ImageIcon selectButtonIcon = new ImageIcon(linkObj.getIconLink().replaceAll(".jpg", "Select.jpg"));
        JButton button = new JButton();
        button.setIcon(noSelectButtonIcon);
        button.setSelectedIcon(selectButtonIcon);
        button.setMnemonic(i);
        return button;
    }

    private void setClosed() {
        creationFrame.setVisible(false);
        isClosed = true;
        creationFrame.dispose();
    }

    private void setSubmited() {
        if (nickNameField.getText()!=null) {
            isSubmited = true;
            creationFrame.setVisible(false);
        }
    }

    public String getNickName() {
        return nickNameField.getText();
    }

    public Race getRace() {
        return races.get(racesBGroup.getSelection().getMnemonic());
    }

    public Profession getProfession() {
        return professions.get(professionsBGroup.getSelection().getMnemonic());
    }

    public boolean isSubmited(){
        return isSubmited;
    }

    public boolean isClosed(){
        return isClosed;
    }
}
