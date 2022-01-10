package firstpaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

public class FirstQuestion extends JFrame {
    public FirstQuestion() {
        initUI();
    }

    private void initUI() {
        final Surface surface = new Surface();
        add(surface);
        setTitle("FirstQuestion");
        setSize(800, 800);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                surface.getTimer().stop();
            }
        });
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FirstQuestion firstQuestion = new FirstQuestion();
        firstQuestion.setVisible(true);
    }
}

class Surface extends JPanel implements ActionListener {
    private final static int DELAY = 10;
    private double width;
    private double height;
    private double newXPos = 0;
    private double newYPos = 0;


    private Timer timer;

    public Surface() {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {
        return this.timer;
    }

    private void doDrawing(Graphics2D g2d) {
        width = getWidth();
        height = getHeight();
        double xPos = width / 2;
        double yPos = height / 2;
        if (newXPos < xPos + 5) {
            newXPos = xPos + 5;
            Line2D line2D = new Line2D.Double(xPos, yPos, newXPos, yPos);
            g2d.draw(line2D);
            System.out.println("Entered");
        }
        if (newYPos < yPos + 5) {
            newYPos=yPos+5;
            Line2D line2D1 = new Line2D.Double(newXPos, yPos, newXPos, newYPos);
            g2d.draw(line2D1);
            System.out.println("enter to y");
        }


//        Line2D line2D2 = new Line2D.Double(newXPos, newYPos, xPos - 10, newYPos);
//        Line2D line2D3 = new Line2D.Double(xPos - 10, newYPos, xPos - 10, yPos - 10);
//        g2d.draw(line2D2);
//        g2d.draw(line2D3);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing((Graphics2D) g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
