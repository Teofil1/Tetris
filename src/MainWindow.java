import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    GamePanel gamePanel = new GamePanel();

    MainWindow(){
        setSize(350,435);
        setResizable(false);
        setTitle("Tetris");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(300,100);
        setVisible(true);
        Container container = this.getContentPane();
        gamePanel.setPreferredSize(new Dimension(200 , 400));
        gamePanel.rightPanel.setPreferredSize(new Dimension(150 , 400));
        container.add(gamePanel , BorderLayout.WEST);
        container.add(gamePanel.rightPanel , BorderLayout.EAST);

    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }

}

