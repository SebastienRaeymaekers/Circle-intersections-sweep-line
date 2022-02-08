import javafx.scene.shape.Circle;

public class EventPoint extends Point implements Comparable<EventPoint>{

    public EventPoint(Double x, Double y, Circle circle, boolean isIntersection, boolean isStartOfSegment, CircleSegment cItersect1, CircleSegment cItersect2) {
        super(x, y, circle, isIntersection, isStartOfSegment, cItersect1, cItersect2);
    }

    @Override
    public int compareTo(EventPoint o) {
        if(isStartOfSegment && !o.isStartOfSegment && x.equals(o.x)) return -1;
        if(!isStartOfSegment && o.isStartOfSegment && x.equals(o.x)) return 1;
        else return this.x.compareTo(o.x);
    }
}