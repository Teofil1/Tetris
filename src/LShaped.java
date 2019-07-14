import javax.swing.*;

public class LShaped extends Figure {

    public LShaped() {
        createElement();
        loadImages();
        super.move();

    }

    @Override
    public void loadImages() {
        ImageIcon iid = new ImageIcon("picturesFigure/LShaped.png");
        dotImage = iid.getImage();
    }

    @Override
    public void createElement() {
        y[0] = -20;
        x[0] = 100;
        y[1] = -40;
        x[1] = 100;
        y[2] = -60;
        x[2] = 100;
        y[3] = -60;
        x[3] = 80;
    }

    @Override
    public void turnOver() {
        if (indexTurnOver == 0) {

            y[0] -= 40;
            x[2] -= 20;
            y[2] += 20;
            x[3] -= 20;
            y[3] += 20;

            specialElement = true;
            checkCollisions();
            indexTurnOver = 1;
        } else if (indexTurnOver == 1) {
            y[0] += 40;
            y[1] += 20;
            x[1] -= 20;
            x[3] += 20;
            y[3] -= 20;

            specialElement = false;
            checkCollisions();
            indexTurnOver = 2;
        } else if (indexTurnOver == 2) {
            y[0] -= 40;
            y[1] -= 40;
            x[2] -= 20;
            x[3] -= 20;

            specialElement = true;
            checkCollisions();
            indexTurnOver = 3;
        } else if (indexTurnOver == 3) {
            y[0] += 40;
            x[1] += 20;
            y[1] += 20;
            x[2] += 40;
            y[2] -= 20;
            x[3] += 20;

            specialElement = false;
            checkCollisions();
            indexTurnOver = 0;
        }
    }

    @Override
    public void checkCollisions() {
        if(x[3] == -20)
            for (int i = 0; i < dots; i++)
                x[i]+=20;
        if ((specialElement == true && y[2] == 400) || (specialElement == false && y[0] == 400))
            for (int i = 0; i < dots; i++)
                y[i] -= 20;
    }
}
