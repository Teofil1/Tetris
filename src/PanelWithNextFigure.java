import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelWithNextFigure extends JPanel {

    Figure figure;
    int x;                //additional width for beautiful display of the figure in the panel
    int y;                //additional height for beautiful display of the figure in the panel

    public PanelWithNextFigure() {
        setPreferredSize(new Dimension(80, 120));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintNextElements(List<Integer> list) {

        if (list.get(list.size() - 1) == 1) {
            figure = new Line();
            x = 50;
            y = 60;
        } else if (list.get(list.size() - 1) == 2) {
            figure = new Square();
            x = 60;
            y = 60;
        } else if (list.get(list.size() - 1) == 3) {
            figure = new LShaped();
            x = 60;
            y = 70;
        } else if (list.get(list.size() - 1) == 4) {
            figure = new BackLShaped();
            x = 60;
            y = 70;
        } else if (list.get(list.size() - 1) == 5) {
            figure = new Crankle();
            x = 60;
            y = 70;
        } else if (list.get(list.size() - 1) == 6) {
            figure = new BackCrankle();
            x = 60;
            y = 70;
        } else if (list.get(list.size() - 1) == 7) {
            figure = new TShaped();
            x = 70;
            y = 60;
        }

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < figure.dots; i++) {
            g.drawImage(figure.dotImage, figure.x[i] - x, figure.y[i] + y, this);
        }
    }
}
