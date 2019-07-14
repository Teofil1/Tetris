import javax.swing.*;

public class BackLShaped extends Figure {

    public BackLShaped() {
        createElement();
        loadImages();
        super.move();

    }

    @Override
    public void loadImages() {
        ImageIcon iid = new ImageIcon("picturesFigure/backLShaped.png");
        dotImage = iid.getImage();
    }

    @Override
    public void createElement() {
        specialElement = true;     // this figure is always "special" (in any position)

        y[0] = -60;
        x[0] = 100;
        y[1] = -60;
        x[1] = 80;
        y[2] = -20;
        x[2] = 80;
        y[3] = -40;
        x[3] = 80;


    }

    @Override
    public void turnOver() {
        if (indexTurnOver == 0) {

            y[2] -= 20;
            x[2] += 20;
            x[3] -= 20;
            y[3] -= 20;

            checkCollisions();
            indexTurnOver = 1;
        } else if (indexTurnOver == 1) {

            y[1] += 20;
            x[1] += 20;
            y[2] += 20;
            x[3] += 20;
            y[3] += 40;

            checkCollisions();
            indexTurnOver = 2;
        } else if (indexTurnOver == 2) {

            y[0] += 20;
            x[1] -= 20;
            x[2] -= 40;
            y[2] -= 20;
            x[3] -= 20;
            y[3] -= 40;

            checkCollisions();
            indexTurnOver = 3;
        } else if (indexTurnOver == 3) {
            y[0] -= 20;
            y[1] -= 20;
            x[2] += 20;
            y[2] += 20;
            x[3] += 20;
            y[3] += 20;

            checkCollisions();
            indexTurnOver = 0;
        }
    }

    @Override
    public void checkCollisions() {
        if(x[3] == -20)
            for (int i = 0; i < dots; i++)
                x[i]+=20;
        if (y[2] == 400)
            for (int i = 0; i < dots; i++)
                y[i] -= 20;
    }
}
