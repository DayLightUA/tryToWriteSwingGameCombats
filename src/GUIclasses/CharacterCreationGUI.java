package GUIclasses;


import Professions.Profession;
import Races.Elf;
import Races.Human;
import Races.Orc;
import Races.Race;

import javax.swing.*;
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
    private ButtonGroup races;
    private ButtonGroup professions;

    private boolean isSubmited;
    private boolean isClosed;

    public void go(){
        creationFrame = new JFrame("Create the Character!!!");
        creationPanel = new JPanel();
        submitButton = new JButton("Submit!");
        submitButton.addActionListener((AbstractAction) (e) -> {setSubmited();});
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((AbstractAction) (e) -> {setClosed();});
        buttonBox = Box.createHorizontalBox();
        buttonBox.add(submitButton);
        buttonBox.add(cancelButton);
        initRaces();
        initProfessions();
    }

    private void initRaces() {
        races = new ArrayList<>();
        races.add(new Human());
        races.add(new Elf());
        races.add(new Orc());

    }
}
