import javafx.scene.shape.Circle;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class CircleIntersectionsProgram {
    // define program parameters
    public String inputFile = "in.txt";
    public String outputFile = "out.txt";
    public int algorithmNumber;
    public int numberOfCircles;
    public static List<Circle> allCircles = new ArrayList<>();
    public List<Point> intersectionPoints = new ArrayList<>();

    public long elapsedTime;

    public CircleIntersectionsProgram() throws IOException {
        System.out.println("-------InputFile Data:-------");
        TextFileIO.readFromInputFile(this);
        System.out.println("-----------------------------");

        long startTime = System.currentTimeMillis();

        if(algorithmNumber == 1) {
            System.out.println("-------BruteForce algorithm output-------");
            Algorithm1 algorithm1 = new Algorithm1(this);
            this.intersectionPoints = algorithm1.doAlgorithm1();
        }
        if(algorithmNumber == 2) {
            System.out.println("-------ScanLine algorithm output-------");
            Algorithm2 algorithm2 = new Algorithm2(this);
            this.intersectionPoints = algorithm2.doAlgorithm2();
        }
        if(algorithmNumber == 3) {
            System.out.println("-------Efficient ScanLine algorithm output-------");
            Algorithm3 efficientScanLineAlgorithm = new Algorithm3(this);
            this.intersectionPoints = efficientScanLineAlgorithm.doAlgorithm3();
        }
        if (this.intersectionPoints != null){
            //System.out.println("intersections: " + this.intersectionPoints.toString());
            long endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime);
            System.out.println("number of circles : " + numberOfCircles);
            System.out.println("number of intersections found: " + this.intersectionPoints.size());
            System.out.println("elapsedTime: " + elapsedTime);
        }
        System.out.println("-------------------------------------------------");
        TextFileIO.writeToOutputFile(outputFile, this.intersectionPoints, elapsedTime);
        paintResult(); // for debugging and testing purposes
    }

    public ArrayList<EventPoint> getAllStartAndEndCirclePointsList(){
        ArrayList<EventPoint> allStartAndEndPointOnSegments = new ArrayList<EventPoint>();
        for(Circle circle : allCircles){
            // add start and end segment point
            // convert to big decimal to avoid rounding errors
            BigDecimal bigDX = new BigDecimal(circle.getCenterX(), MathContext.DECIMAL64);
            BigDecimal bigDR = new BigDecimal(circle.getRadius(), MathContext.DECIMAL64);

            BigDecimal bigDX_min_bigDR = bigDX.subtract(bigDR);
            EventPoint startSegmentPoint = new EventPoint(bigDX_min_bigDR.doubleValue(), circle.getCenterY(), circle, false, true,null,null);

            BigDecimal bigDX_plus_bigDR = bigDX.add(bigDR);
            EventPoint endSegmentPoint = new EventPoint(bigDX_plus_bigDR.doubleValue(), circle.getCenterY(), circle, false, false,null,null);

            allStartAndEndPointOnSegments.add(startSegmentPoint);
            allStartAndEndPointOnSegments.add(endSegmentPoint);
        }
        return allStartAndEndPointOnSegments;
    }

    public PriorityQueue<EventPoint> getAllStartAndEndCirclePointsPQ(){
        PriorityQueue<EventPoint> allStartAndEndPointOnSegments = new PriorityQueue<>();
        for(Circle circle : allCircles){
            // add start and end segment point
            // convert to big decimal to avoid rounding errors
            BigDecimal bigDX = new BigDecimal(circle.getCenterX(), MathContext.DECIMAL64);
            BigDecimal bigDR = new BigDecimal(circle.getRadius(), MathContext.DECIMAL64);

            BigDecimal bigDX_min_bigDR = bigDX.subtract(bigDR);
            EventPoint startSegmentPoint = new EventPoint(bigDX_min_bigDR.doubleValue(), circle.getCenterY(), circle, false, true,null,null);

            BigDecimal bigDX_plus_bigDR = bigDX.add(bigDR);
            EventPoint endSegmentPoint = new EventPoint(bigDX_plus_bigDR.doubleValue(), circle.getCenterY(), circle, false, false,null,null);

            allStartAndEndPointOnSegments.add(startSegmentPoint);
            allStartAndEndPointOnSegments.add(endSegmentPoint);
        }
        return allStartAndEndPointOnSegments;
    }

    public List<EventPoint> calculateIntersection(CircleSegment circleSegment1, CircleSegment circleSegment2){
        Circle circle1 = circleSegment1.circle; Circle circle2 = circleSegment2.circle;
        double x1; double x2; double y1; double y2;
        double a = circle1.getRadius()*circle1.getRadius() - circle2.getRadius()*circle2.getRadius() + circle2.getCenterX()*circle2.getCenterX() - circle1.getCenterX()*circle1.getCenterX() + circle2.getCenterY()*circle2.getCenterY() - circle1.getCenterY()*circle1.getCenterY() ;
        if(circle1.getCenterY() != circle2.getCenterY()){
            double b = (circle1.getCenterX()-circle2.getCenterX())/(circle2.getCenterY()-circle1.getCenterY());
            double c = a/(2*(circle2.getCenterY()-circle1.getCenterY()));
            double d = (b*b) + 1;
            double e = 2*b*c - 2*circle1.getCenterX() - 2*circle1.getCenterY()*b;
            double f = circle1.getCenterX()*circle1.getCenterX() + c*c + circle1.getCenterY()*circle1.getCenterY() - circle1.getRadius()*circle1.getRadius() - 2*circle1.getCenterY() *c;
            double distance = e*e - 4 * d * f;
            distance = (double)Math.round(distance * 1000000000000000d) / 1000000000000000d;
            x1 = (-e + sqrt(distance))/ (2*d);
            y1 = b * x1 + c;
            x2 = (-e - sqrt(distance))/ (2*d);
            y2 = b * x2 + c;
        }
        else{
            double b = (circle1.getCenterY()-circle2.getCenterY())/(circle2.getCenterX()-circle1.getCenterX());
            double c = a/(2*(circle2.getCenterX()-circle1.getCenterX()));
            double d = (b*b) + 1;
            double e = 2*b*c - 2*circle1.getCenterX()*b - 2*circle1.getCenterY();
            double f = c*c - 2*circle1.getCenterX()*c + circle1.getCenterX()*circle1.getCenterX() + circle1.getCenterY()*circle1.getCenterY() - circle1.getRadius()*circle1.getRadius();
            double distance = e*e - 4 * d * f;
            distance = (double)Math.round(distance * 1000000000000000d) / 1000000000000000d;
            y1 = (-e + sqrt(distance))/ (2*d);
            x1 = b * y1 + c;
            y2 = (-e - sqrt(distance))/ (2*d);
            x2 = b * y2 + c;
        }
        ArrayList<EventPoint> intersections = new ArrayList<>();
        if(calculateDistanceBetweenCenters(circle1, circle2) == circle1.getRadius() + circle2.getRadius()){
            return Collections.singletonList(new EventPoint(circle1.getCenterX() + circle1.getRadius(),
                    circle1.getCenterY() + (circle2.getCenterY() - circle1.getCenterY()), circle1, true, false, null, null));
        }

        if(!Double.isNaN(x1) && !Double.isNaN(y1)) {
            intersections.add(new EventPoint(x1, y1, circle1, true, false, circleSegment1, circleSegment2));
        }
        if(x1 != x2 || y1 != y2) {
            if (!Double.isNaN(x2) && !Double.isNaN(y2)) {
                intersections.add(new EventPoint(x2, y2, circle1, true, false, circleSegment1, circleSegment2));
            }
        }
        return intersections;
    }

    public List<EventPoint> calculateIntersection(Circle circle1, Circle circle2){
        double x1; double x2; double y1; double y2;
        double a = circle1.getRadius()*circle1.getRadius() - circle2.getRadius()*circle2.getRadius() + circle2.getCenterX()*circle2.getCenterX() - circle1.getCenterX()*circle1.getCenterX() + circle2.getCenterY()*circle2.getCenterY() - circle1.getCenterY()*circle1.getCenterY() ;
        if(circle1.getCenterY() != circle2.getCenterY()){
            double b = (circle1.getCenterX()-circle2.getCenterX())/(circle2.getCenterY()-circle1.getCenterY());
            double c = a/(2*(circle2.getCenterY()-circle1.getCenterY()));
            double d = (b*b) + 1;
            double e = 2*b*c - 2*circle1.getCenterX() - 2*circle1.getCenterY()*b;
            double f = circle1.getCenterX()*circle1.getCenterX() + c*c + circle1.getCenterY()*circle1.getCenterY() - circle1.getRadius()*circle1.getRadius() - 2*circle1.getCenterY() *c;
            double distance = e*e - 4 * d * f;
            distance = (double)Math.round(distance * 1000000000000000d) / 1000000000000000d;
            x1 = (-e + sqrt(distance))/ (2*d);
            y1 = b * x1 + c;
            x2 = (-e - sqrt(distance))/ (2*d);
            y2 = b * x2 + c;
        }
        else{
            double b = (circle1.getCenterY()-circle2.getCenterY())/(circle2.getCenterX()-circle1.getCenterX());
            double c = a/(2*(circle2.getCenterX()-circle1.getCenterX()));
            double d = (b*b) + 1;
            double e = 2*b*c - 2*circle1.getCenterX()*b - 2*circle1.getCenterY();
            double f = c*c - 2*circle1.getCenterX()*c + circle1.getCenterX()*circle1.getCenterX() + circle1.getCenterY()*circle1.getCenterY() - circle1.getRadius()*circle1.getRadius();
            double distance = e*e - 4 * d * f;
            distance = (double)Math.round(distance * 1000000000000000d) / 1000000000000000d;
            y1 = (-e + sqrt(distance))/ (2*d);
            x1 = b * y1 + c;
            y2 = (-e - sqrt(distance))/ (2*d);
            x2 = b * y2 + c;
        }
        ArrayList<EventPoint> intersections = new ArrayList<>();

        if(calculateDistanceBetweenCenters(circle1, circle2) == circle1.getRadius() + circle2.getRadius()){
            return Collections.singletonList(new EventPoint(circle1.getCenterX() + circle1.getRadius(),
                    circle1.getCenterY() + (circle2.getCenterY() - circle1.getCenterY()), circle1, true, false, null, null));
        }

        if(!Double.isNaN(x1) && !Double.isNaN(y1)) {
            intersections.add(new EventPoint(x1, y1, circle1, true, false, null, null));
        }
        if(x1 != x2 || y1 != y2) {
            if (!Double.isNaN(x2) && !Double.isNaN(y2)) {
                intersections.add(new EventPoint(x2, y2, circle1, true, false, null, null));
            }
        }
        return intersections;
    }

    public double calculateDistanceBetweenCenters(Circle circle1, Circle circle2){
        return sqrt((circle1.getCenterX() - circle2.getCenterX())*(circle1.getCenterX() - circle2.getCenterX())
                + (circle1.getCenterY() - circle2.getCenterY())*(circle1.getCenterY() - circle2.getCenterY()));
    }

    public ArrayList<Point> removeDuplicates(ArrayList<Point> allIntersections){
        List<Point> newList = allIntersections.stream()
                .distinct()
                .collect(Collectors.toList());
        return (ArrayList<Point>) newList;
    }

    public void paintResult(){
        Canvas canvas = new Canvas(this);
    }

}
