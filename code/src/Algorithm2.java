import javafx.scene.shape.Circle;
import java.util.*;

public class Algorithm2 {
    CircleIntersectionsProgram circleIntersectionsProgram;
    ArrayList<Point> allIntersections;
    public PriorityQueue<EventPoint> allEventPoints = new PriorityQueue<>();

    public Algorithm2(CircleIntersectionsProgram circleIntersectionsProgram){
        this.circleIntersectionsProgram = circleIntersectionsProgram;
        this.allIntersections = new ArrayList<>();
    }

    public List<Point> doAlgorithm2() {
        this.allEventPoints = circleIntersectionsProgram.getAllStartAndEndCirclePointsPQ();
        ArrayList<Circle> activeCircles = new ArrayList<>();
        int size = this.allEventPoints.size();
        for(int i = 0; i < size; i++){
            EventPoint eventPoint = this.allEventPoints.poll();
            if(eventPoint.isStartOfSegment) {
                activeCircles.add(eventPoint.circle);
                for (Circle activeCircle : activeCircles) {
                    if(!activeCircle.equals(eventPoint.circle))
                        this.allIntersections.addAll(circleIntersectionsProgram.calculateIntersection(eventPoint.circle, activeCircle));
                }
            }
            else activeCircles.remove(eventPoint.circle);
        }
        return this.allIntersections;
    }

}
