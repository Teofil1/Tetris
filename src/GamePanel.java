import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private final int SIZE_HEIGHT = 400;
    private Timer timer;
    private int timerDelay = 1000;
    boolean inGame = true;
    List collectionElements = new ArrayList<Figure>();
    List<Integer> arrayindexElement = new ArrayList();
    int numberInCollection = -1;
    boolean endRoad = false;
    boolean error = false;
    int numberDotsInRow = 0;
    RightPanel rightPanel = new RightPanel();

    GamePanel() {
        requestFocus();
        setBackground(Color.black);
        setSize(200, 400);
        timer = new Timer(timerDelay, this);
        timer.start();
        launchNewElementInGame();
        addKeyListener(new FieldKeyListener());
        rightPanel.newGame.addActionListener(this);
        rightPanel.pause.addActionListener(this);
        rightPanel.start.addActionListener(this);
        rightPanel.exit.addActionListener(this);
        setFocusable(true);
    }

    public void launchNewElementInGame() {
        arrayindexElement.add(new Random().nextInt(7) + 1);
        if (arrayindexElement.size() == 1) arrayindexElement.add(new Random().nextInt(7) + 1);
        numberInCollection++;

        if (arrayindexElement.get(arrayindexElement.size() - 2) == 1) collectionElements.add(new Line());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 2) collectionElements.add(new Square());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 3) collectionElements.add(new LShaped());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 4) collectionElements.add(new BackLShaped());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 5) collectionElements.add(new Crankle());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 6) collectionElements.add(new BackCrankle());
        else if (arrayindexElement.get(arrayindexElement.size() - 2) == 7) collectionElements.add(new TShaped());

        rightPanel.nextFigure.paintNextElements(arrayindexElement);


    }

    public void checkEndRoad() {
        Figure currentFigure = (Figure) collectionElements.get(numberInCollection);
        outterLoop:
        for (int i = 0; i < collectionElements.size() - 1; i++) {
            Figure e = (Figure) collectionElements.get(i);
            for (int j = 0; j < e.dots; j++)
                for (int k = 0; k < e.dots; k++)
                    if (currentFigure.y[j] + 20 == e.y[k] && currentFigure.x[j] == e.x[k]) {
                        endRoad = true;
                        break outterLoop;
                    }
        }

        if ((currentFigure.specialElement == false && currentFigure.y[0] >= 380) || endRoad == true) {
            endRoad = false;
            checkFilledRows();
            launchNewElementInGame();
        } else if ((currentFigure.specialElement == true && currentFigure.y[2] >= 380) || endRoad == true) {
            endRoad = false;
            checkFilledRows();
            launchNewElementInGame();
        }

    }

    public void chekCollisionsBetweenElements() {

        Figure currentFigure = (Figure) collectionElements.get(numberInCollection);
        outterLoop:
        for (int i = 0; i < collectionElements.size() - 1; i++) {
            Figure e = (Figure) collectionElements.get(i);
            for (int j = 0; j < e.dots; j++)
                for (int k = 0; k < e.dots; k++)
                    if (currentFigure.y[j] == e.y[k] && currentFigure.x[j] == e.x[k]) {
                        error = true;     // the elements of the figure are superimposed on each other
                        break outterLoop;
                    }
        }
    }

    public void checkFilledRows() {

        for (int i = 0; i <= 380; ) {
            for (int j = 0; j <= 180; ) {
                for (int k = 0; k < collectionElements.size(); k++) {
                    Figure e = (Figure) collectionElements.get(k);
                    for (int l = 0; l < e.dots; l++)
                        if (e.x[l] == j && e.y[l] == i)
                            numberDotsInRow++;
                }
                j += 20;
            }
            if (numberDotsInRow == 10) {
                clearRow(i);
                rightPanel.updateScore();
                acceleration();
                lowerRows(i);
            }
            i += 20;
            numberDotsInRow = 0;
        }
    }

    public void clearRow(int i) {
        for (int j = 0; j < collectionElements.size(); j++) {
            Figure e = (Figure) collectionElements.get(j);
            for (int k = 0; k < e.dots; k++)
                if (e.y[k] == i)
                    e.y[k] = SIZE_HEIGHT;
        }
    }

    public void lowerRows(int i) {
        for (int j = 0; j < collectionElements.size(); j++) {
            Figure e = (Figure) collectionElements.get(j);
            for (int k = 0; k < e.dots; k++)
                if (e.y[k] < i)
                    e.y[k] += 20;
        }
    }

    public void acceleration() {
        if (rightPanel.score % 60 == 0) {
            rightPanel.updateLevel();
            /*collectionElements.clear();    //ofcommentary, if you want to with each level cleared the field of play
            numberInCollection = -1;*/
            if (timerDelay >= 50) {
                timer.stop();
                timerDelay *= 0.6;
                timer = new Timer(timerDelay, this);
                timer.start();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            for (int j = 0; j < collectionElements.size(); j++) {
                Figure e = (Figure) collectionElements.get(j);
                for (int i = 0; i < e.dots; i++) {
                    if (e.y[i] == SIZE_HEIGHT) continue;
                    g.drawImage(e.dotImage, e.x[i], e.y[i], this);
                }
            }
            chekCollisionsBetweenElements();
            if (error == true) inGame = false;
        }
        if (inGame == false) {
            g.setColor(Color.white);
            g.drawString("Game Over", this.getWidth() / 3, SIZE_HEIGHT / 2);
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == rightPanel.newGame) {
            if (rightPanel.pause.isEnabled() == true) timer.stop();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "\n" +
                    "Are you sure you want to quit the current game?", "choose one", dialogButton);
            if (dialogResult == 0) {
                collectionElements.clear();
                arrayindexElement.clear();
                numberInCollection = -1;
                timerDelay = 1000;
                timer = new Timer(timerDelay, this);
                numberDotsInRow = 0;
                rightPanel.level = 0;
                rightPanel.score = -10;
                rightPanel.updateLevel();
                rightPanel.updateScore();
                launchNewElementInGame();
                timer.start();
                this.requestFocus();
                if (rightPanel.pause.isEnabled() == false) {
                    rightPanel.pause.setEnabled(true);
                    rightPanel.start.setEnabled(false);
                }
                error = false;
                inGame = true;

            } else {
                if (rightPanel.pause.isEnabled() == true) {
                    timer.start();
                    this.requestFocus();
                }
            }
        }
        if (source == rightPanel.pause) {
            timer.stop();
            rightPanel.pause.setEnabled(false);
            rightPanel.start.setEnabled(true);
        }
        if (source == rightPanel.start) {
            timer.start();
            rightPanel.pause.setEnabled(true);
            rightPanel.start.setEnabled(false);
            this.requestFocus();
        }
        if (source == rightPanel.exit) {
            if (rightPanel.pause.isEnabled() == true) timer.stop();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "\n" +
                    "Are you sure you want to quit?", "choose one", dialogButton);
            if (dialogResult == 0) System.exit(0);
            else {
                if (rightPanel.pause.isEnabled() == true) {
                    timer.start();
                    this.requestFocus();
                }
            }
        }
        if (inGame) {
            if (rightPanel.pause.isEnabled() == true) {
                if (numberInCollection >= 0) {
                    checkEndRoad();
                    ((Figure) collectionElements.get(numberInCollection)).move();
                }
                repaint();
            }
        }


    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && ((Figure) collectionElements.get(numberInCollection)).x[3] > 0) {

                for (int i = 0; i < 4; i++) ((Figure) collectionElements.get(numberInCollection)).x[i] -= 20;
                chekCollisionsBetweenElements();
                if (error == true) {
                    for (int i = 0; i < 4; i++) ((Figure) collectionElements.get(numberInCollection)).x[i] += 20;
                    error = false;
                }
                repaint();
            }
            if (key == KeyEvent.VK_RIGHT && ((Figure) collectionElements.get(numberInCollection)).x[0] < 180) {
                for (int i = 0; i < 4; i++) ((Figure) collectionElements.get(numberInCollection)).x[i] += 20;
                chekCollisionsBetweenElements();
                if (error == true) {
                    for (int i = 0; i < 4; i++) ((Figure) collectionElements.get(numberInCollection)).x[i] -= 20;
                    error = false;
                }
                repaint();
            }
            if (key == KeyEvent.VK_DOWN) {
                checkEndRoad();
                ((Figure) collectionElements.get(numberInCollection)).move();
                repaint();
            }
            if (key == KeyEvent.VK_SPACE) {
                ((Figure) collectionElements.get(numberInCollection)).turnOver();
                chekCollisionsBetweenElements();
                if (error == true) {
                    ((Figure) collectionElements.get(numberInCollection)).turnOver();
                    ((Figure) collectionElements.get(numberInCollection)).turnOver();
                    ((Figure) collectionElements.get(numberInCollection)).turnOver();
                }
                error = false;
                repaint();
            }
        }
    }

}
