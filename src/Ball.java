import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;
import java.awt.Color;

import javax.swing.JComponent;

public class Ball extends JComponent {
    DoublePoint position;
    DoublePoint speed;
    double mass;

    double angle;
    int radius;

    double gravity = 0.01;

    public Ball(int x, int y, int vx, int vy, double mass) {
        super();

        position = new DoublePoint(x, y);
        this.speed = new DoublePoint(vx, vy);
        this.mass = mass;
        radius = (int)(mass*5);
        angle = 107;

        gravity *= mass;
    }

    private boolean checkCollision() {
        return position.y + radius >= getHeight();
    }

    private boolean checkWallCollision() {
        return position.x + radius >= getWidth() || position.x-radius <= 0;
    }

    public void physics() {

        if (checkCollision()) {
            angle = 270;

            // speed.x =  0;
            speed.y *=  -1;
        }

        if (checkWallCollision()) {
            speed.x *= -1;
        }

        speed.x +=  Math.cos(angle * Math.PI / 180) * (angle >= 180 && angle <= 360 ? -1.7 : 1);
        speed.y +=  Math.sin(angle * Math.PI / 180 ) * (angle >= 180 && angle <= 360 ? -1.7 : 1);

        position = new DoublePoint(
            (int)(position.x + speed.x),
            (int)(position.y + speed.y)
        );

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.blue);

        

        int x = (int)(position.x - radius);
        int y = (int)(position.y - radius);

        g.fillOval(x, y, radius*2, radius*2);
    }   
}
