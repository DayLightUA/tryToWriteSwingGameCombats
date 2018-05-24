package GUIclasses;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DayLightUA on 24.05.2018.
 */
public class GameGUI {
    private static GameGUI instanceOfGame;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel topMenuPanel;
    private JPanel characterPanel;
    private JPanel enemysListPanel;
    private JPanel choosedEnemyPanel;
    private JPanel chatPanel;

    private GameGUI(){}

    public static GameGUI getInstance(){
        if (instanceOfGame == null) return instanceOfGame = new GameGUI();
        else return instanceOfGame;
    }

    public void go() {
        initGUI();


    }

    private void initGUI() {
        mainFrame = new JFrame("SwingCombats");
        mainPanel = new JPanel(new BorderLayout());
        topMenuPanel = new JPanel();
        characterPanel = new JPanel();
        enemysListPanel = new JPanel();
        choosedEnemyPanel = new JPanel();
        chatPanel = new JPanel();

        mainPanel.add(topMenuPanel, BorderLayout.NORTH);
        mainPanel.add(characterPanel, BorderLayout.WEST);
        mainPanel.add(enemysListPanel, BorderLayout.CENTER);
        mainPanel.add(choosedEnemyPanel, BorderLayout.EAST);
        mainPanel.add(chatPanel, BorderLayout.SOUTH);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setVisible(true);
    }
}
