import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Canvas extends JPanel {
    public CircleIntersectionsProgram circleIntersectionsProgram;
    public static int scale = 300;
    public static double margin = 300;
    public JFrame f;

    final static double canvasWidth = 1000;
    final static double canvasHeight = 800;

    public Canvas(CircleIntersectionsProgram circleIntersectionsProgram) {
        this.circleIntersectionsProgram = circleIntersectionsProgram;
        this.f = new JFrame();
        f.setSize((int) canvasWidth, (int) canvasHeight);
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Circle circle : circleIntersectionsProgram.allCircles) drawCircle(g, circle);
                //for(Point point : tmiTaak.allEventPoints) drawPoint(g,point, Color.black);
                for(Point point : circleIntersectionsProgram.intersectionPoints) drawPoint(g,point, Color.red);
                //for (Line2D line : tmiTaak.allScanLines) Canvas.drawScanLine(g, line, Color.blue);
                //for(Point point : tmiTaak.allScanlineCircleIntersections) drawPoint(g,point, Color.green);
            }
        };
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void drawCircle(Graphics gg, Circle circle) {
        /* Cast it to Graphics2D */
        Graphics2D g = (Graphics2D) gg;
        /* Enable anti-aliasing and pure stroke */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        /* Construct a shape and draw it */
        Ellipse2D.Double shape = new Ellipse2D.Double((margin + (circle.getCenterX() * scale)) - (circle.getRadius() * scale),
                                                      ((canvasHeight - margin) - (circle.getCenterY() * scale)) - (circle.getRadius() * scale),
                                                       circle.getRadius() * 2 * scale, circle.getRadius() * 2 * scale);
        g.setColor(Color.BLACK);
        g.draw(shape);
    }

    public static void drawPoint(Graphics gg, Point point, Color color){
        double radiusOfPoint = 0.01;

        /* Cast it to Graphics2D */
        Graphics2D g = (Graphics2D) gg;
        /* Enable anti-aliasing and pure stroke */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        /* Construct a shape and draw it */
        Ellipse2D.Double shape = new Ellipse2D.Double((margin + (point.x * scale)) - (radiusOfPoint * scale),
                ((canvasHeight - margin) - (point.y * scale)) - (radiusOfPoint * scale),
                radiusOfPoint * 2 * scale, radiusOfPoint * 2 * scale);
        g.setPaint(color);
        g.draw(shape);
        g.fill(shape);
    }

    public static void drawScanLine(Graphics gg, Line2D line, Color color){
        /* Cast it to Graphics2D */
        Graphics2D g = (Graphics2D) gg;
        /* Enable anti-aliasing and pure stroke */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g.setPaint(color);
        g.draw(new Line2D.Double((line.getX1() * scale) + margin, canvasHeight, (line.getX2() * scale) + margin, 0));
    }

}
