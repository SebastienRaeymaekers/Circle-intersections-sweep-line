import javafx.scene.shape.Circle;

import java.util.Objects;

public class Point {
    public Double x; public Double y;
    Circle circle;
    boolean isStartOfSegment;
    boolean isIntersection;
    CircleSegment circleSegmentIntersection1;
    CircleSegment circleSegmentIntersection2;

    public Point(Double x, Double y, Circle circle, boolean isIntersection, boolean isStartOfCircle, CircleSegment cItersect1, CircleSegment cItersect2){
        this.x = round(x); this.y = round(y);
        this.circle = circle;
        this.isIntersection = isIntersection;
        this.isStartOfSegment = isStartOfCircle;
        this.circleSegmentIntersection1 = cItersect1;
        this.circleSegmentIntersection2 = cItersect2;
    }

    public Double round(Double value){
        return (double)Math.round(value * 10000000000d) / 10000000000d;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Point point = (Point) object;
        return x.equals(point.x) && y.equals(point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

}
