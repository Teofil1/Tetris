import java.awt.*;

public abstract class Figure{

    Image dotImage;
    final int DOT_SIZE = 20;
    private final int ALL_DOTS = 200;
    int[] x = new int[ALL_DOTS];
    int[] y = new int[ALL_DOTS];
    int dots=4;
    int indexTurnOver=0;
    boolean specialElement=false; //where x[0] and y[0] is not the lowest part of the element but
                                  // x[2] and y[2] is the lowest part (look the file "README"

    public abstract void loadImages();

    public abstract void createElement();

    public void move(){
        for (int i = dots; i >= 0; i--)
            y[i]+=20;
    }

    public abstract void turnOver();

    public abstract void checkCollisions();
}
