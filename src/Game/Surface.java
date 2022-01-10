package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Surface extends JPanel implements ActionListener {
    private final int DELAY = 10;
    private double width;
    private double height;
    private Timer timer;
    private int degree = 0;
    private int degree1 = 0;
    private int degree2 = 0;
    private Color color;
    private Random random;

    public Surface() {
        initTimer();
        random = new Random();
        initColor();
    }

    private void initColor() {
        color = new Color(Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256);
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    private Color getColor() {
        return color;
    }

    public void doDrawing(Graphics2D g2d) {
        width = getWidth();
        height = getHeight();
        Rectangle2D bigRect = new Rectangle2D.Double(0, 0, 150, 10);
        Rectangle2D leftRect = new Rectangle2D.Double(0, 0, 100, 10);
        Rectangle2D rightRect = new Rectangle2D.Double(0, 0, 100, 10);
        Rectangle2D center = new Rectangle2D.Double(0, 0, 100, 10);

        AffineTransform aT = new AffineTransform();
        aT.translate(width / 2 - 75, height / 2 - 5);
        aT.rotate(Math.toRadians(degree), 75, 5);
        GeneralPath path = new GeneralPath();
        path.append(aT.createTransformedShape(bigRect), false);
        g2d.setPaint(getColor());
        g2d.fill(path);

        AffineTransform aT1 = new AffineTransform();
        aT1.translate(width / 2 - width / 4 - 50, height / 2 - 5);
        aT1.rotate(Math.toRadians(degree), 50, 5);
        path.append(aT1.createTransformedShape(leftRect), false);
        g2d.setPaint(getColor());
        g2d.fill(path);

        AffineTransform aT2 = new AffineTransform();
        aT2.translate(width / 2 + width / 4 - 50, height / 2 - 5);
        aT2.rotate(Math.toRadians(-degree), 50, 5);
        path.append(aT2.createTransformedShape(rightRect), false);
        g2d.setPaint(getColor());
        g2d.fill(path);

        AffineTransform aT3 = new AffineTransform();
        aT3.translate(width / 2 + width / 4 - 50, height -20);
        path.append(aT3.createTransformedShape(center), false);
        g2d.setPaint(getColor());
        g2d.fill(path);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (degree == 360) {
            degree = 0;
            initColor();
        }
        if(degree1==360){
            degree1=0;
            initColor();
        }
        degree1+=2;
        degree2+=2;
        degree++;
        super.paintComponent(g);
        doDrawing((Graphics2D) g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
