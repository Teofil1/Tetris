import javax.swing.*;

public class Square extends Figure {

    public Square() {
        createElement();
        loadImages();
        super.move();

    }

    @Override
    public void loadImages() {
        ImageIcon iid = new ImageIcon("picturesFigure/square.png");
        dotImage = iid.getImage();
    }

    @Override
    public void createElement() {
        y[0] = -20;
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

    }

    @Override
    public void checkCollisions() {

    }
}
