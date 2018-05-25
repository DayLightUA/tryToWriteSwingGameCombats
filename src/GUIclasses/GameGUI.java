package GUIclasses;

import bin.CharFactory;
import bin.Character;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    private static GameGUI instanceOfGame;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topMenuPanel;
    private JPanel characterPanel;
    private JPanel chatPanel;
    private Character myCharacter;

    private GameGUI(){}

    public static GameGUI getInstance(){
        if (instanceOfGame == null) return instanceOfGame = new GameGUI();
        else return instanceOfGame;
    }

    public void go() {
        initCharacter();
        initGUI();


    }

    private void initCharacter() {
        myCharacter = CharFactory.createCharacter(false);
    }

    private void initGUI() {
        mainFrame = new JFrame("SwingCombats");
        mainPanel = new JPanel(new BorderLayout());
        topMenuPanel = new JPanel();
        characterPanel = myCharacter.getCharPanel();
        chatPanel = new JPanel();

        mainPanel.add(topMenuPanel, BorderLayout.NORTH);
        mainPanel.add(characterPanel, BorderLayout.CENTER);
        mainPanel.add(chatPanel, BorderLayout.SOUTH);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setVisible(true);
    }
}
