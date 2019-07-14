import javax.swing.*;

public class Line extends Figure {

    public Line() {
        createElement();
        loadImages();
        super.move();

    }

    @Override
    public void loadImages() {

        ImageIcon iid = new ImageIcon("picturesFigure/line.png");
        dotImage = iid.getImage();
    }

    @Override
    public void createElement() {
        for (int i = 0; i < dots; i++) {
            y[i] = 0 - i * DOT_SIZE;
            x[i] = 80;
        }
    }


    @Override
    public void turnOver() {
        if (indexTurnOver == 0) {

            x[0] += 40;
            y[0] -= 20;
            x[1] += 20;
            y[2] += 20;
            x[3] -= 20;
            y[3] += 40;

            checkCollisions();
            indexTurnOver = 1;
        } else {
            x[0] -= 40;
            y[0] += 20;
            x[1] -= 20;
            y[2] -= 20;
            x[3] += 20;
            y[3] -= 40;

            checkCollisions();
            indexTurnOver = 0;
        }

    }

    @Override
    public void checkCollisions() {
        if (x[3] == -20)
            for (int i = 0; i < dots; i++)
                x[i] += 20;
        if (x[0] == 220)
            for (int i = 0; i < dots; i++)
                x[i] -= 40;
        if (x[0] == 200)
            for (int i = 0; i < dots; i++)
                x[i] -= 20;
        if (y[0] == 400)
            for (int i = 0; i < dots; i++)
                y[i] -= 20;

    }


}
