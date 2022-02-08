import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Algorithm1 {

    CircleIntersectionsProgram circleIntersectionsProgram;
    ArrayList<Point> allIntersections;

    public Algorithm1(CircleIntersectionsProgram circleIntersectionsProgram){
        this.circleIntersectionsProgram = circleIntersectionsProgram;
        this.allIntersections = new ArrayList<>();
    }

    public List<Point> doAlgorithm1(){
        for(Circle circle1 : CircleIntersectionsProgram.allCircles) {
            for (Circle circle2 : CircleIntersectionsProgram.allCircles) {
                if(!circle1.equals(circle2)) {
                    this.allIntersections.addAll(circleIntersectionsProgram.calculateIntersection(circle1, circle2));
                }
            }
        }
        this.allIntersections = circleIntersectionsProgram.removeDuplicates(this.allIntersections);
        return this.allIntersections;
    }
}
