import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    int score = 0;
    int level = 1;
    JLabel labelScore = new JLabel("Score: " + score);
    JLabel labelLevel = new JLabel("Level: " + level);
    JLabel labelNext = new JLabel("NEXT");
    PanelWithNextFigure nextFigure = new PanelWithNextFigure();
    JButton pause = new JButton("PAUSE");
    JButton start = new JButton("START");
    JButton newGame = new JButton("NEW GAME");
    JButton exit = new JButton("EXIT");

    public RightPanel() {

        setSize(150, 400);
        setBackground(Color.PINK);
        SpringLayout layoutForRightPanel = new SpringLayout();
        setLayout(layoutForRightPanel);

        add(labelNext);
        add(nextFigure);
        add(labelLevel);
        add(labelScore);
        add(newGame);
        add(pause);
        add(start);
        add(exit);

        newGame.setPreferredSize(new Dimension(120, 25));
        pause.setPreferredSize(new Dimension(60, 25));
        start.setPreferredSize(new Dimension(60, 25));
        exit.setPreferredSize(new Dimension(120, 25));

        labelLevel.setForeground(Color.BLUE);
        labelScore.setForeground(Color.BLUE);
        newGame.setBackground(Color.WHITE);
        pause.setBackground(Color.WHITE);
        start.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.RED);

        labelNext.setFont(new Font("Century Gothic", Font.BOLD, 15));
        newGame.setFont(new Font("Century Gothic", Font.BOLD, 15));
        start.setFont(new Font("Century Gothic", Font.BOLD, 8));
        pause.setFont(new Font("Century Gothic", Font.BOLD, 8));
        exit.setFont(new Font("Century Gothic", Font.BOLD, 15));
        start.setEnabled(false);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, labelNext, 57, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, labelNext, 15, SpringLayout.NORTH, this);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, nextFigure, 35, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, nextFigure, 5, SpringLayout.SOUTH, labelNext);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, labelLevel, 52, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, labelLevel, 25, SpringLayout.SOUTH, nextFigure);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, labelScore, 48, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, labelScore, 10, SpringLayout.SOUTH, labelLevel);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, newGame, 15, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, newGame, 20, SpringLayout.SOUTH, labelScore);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, pause, 15, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, pause, 0, SpringLayout.SOUTH, newGame);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, start, 0, SpringLayout.EAST, pause);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, start, 0, SpringLayout.SOUTH, newGame);

        layoutForRightPanel.putConstraint(SpringLayout.WEST, exit, 15, SpringLayout.WEST, this);
        layoutForRightPanel.putConstraint(SpringLayout.NORTH, exit, 0, SpringLayout.SOUTH, pause);

    }

    public void updateScore() {
        score += 10;
        labelScore.setText("Score: " + score);

    }

    public void updateLevel() {
        level++;
        labelLevel.setText("Level: " + level);
    }


}
