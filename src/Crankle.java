import javax.swing.*;

public class Crankle extends Figure {

    public Crankle() {

        createElement();
        loadImages();
        super.move();

    }

    @Override
    public void loadImages() {
        ImageIcon iid = new ImageIcon("picturesFigure/crankle.png");
        dotImage = iid.getImage();
    }

    @Override
    public void createElement() {

        specialElement = true;

        y[0] = -60;
        x[0] = 100;
        y[1] = -40;
        x[1] = 100;
        y[2] = -20;
        x[2] = 80;
        y[3] = -40;
        x[3] = 80;
    }

    @Override
    public void turnOver() {
        if (indexTurnOver % 2 == 0) {

            y[0] += 20;
            x[1] -= 20;
            y[2] -= 40;
            x[3] -= 20;
            y[3] -= 20;

            specialElement = false;
            checkCollisions();
            indexTurnOver++;
        } else {
            y[0] -= 20;
            x[1] += 20;
            y[2] += 40;
            x[3] += 20;
            y[3] += 20;

            specialElement = true;
            checkCollisions();
            indexTurnOver++;
        }
    }

    @Override
    public void checkCollisions() {
        if (x[3] == -20)
            for (int i = 0; i < dots; i++)
                x[i] += 20;
        if ((specialElement == true && y[2] == 400) || (specialElement == false && y[0] == 400))
            for (int i = 0; i < dots; i++)
                y[i] -= 20;
    }
}