import javafx.scene.shape.Circle;

import java.util.Objects;

public class CircleSegment implements Comparable<CircleSegment>{
    boolean upperSegment;
    Circle circle;
    double y;

    public CircleSegment(boolean upperSegment, Circle circle){
        this.upperSegment = upperSegment;
        this.circle = circle;
        this.y = this.circle.getCenterY();
    }

    public String toString(){
        return "upperSegment: " + this.upperSegment + " Circle: " + this.circle + "| y: " + this.y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        CircleSegment circleSegment = (CircleSegment) object;
        return Objects.equals(upperSegment, circleSegment.upperSegment) && Objects.equals(circle, circleSegment.circle);
    }

    @Override
    public int compareTo(CircleSegment o) {
        if ((circle.getCenterY() == o.circle.getCenterY()) && (circle.getCenterX() == o.circle.getCenterX()) && (circle.getRadius() == o.circle.getRadius()) && upperSegment == o.upperSegment) return 0;
        else if ((circle.getCenterY() == o.circle.getCenterY()) && upperSegment && o.upperSegment) return -1;
        else if ((circle.getCenterY() == o.circle.getCenterY()) && upperSegment && !o.upperSegment) return 1;
        else if ((circle.getCenterY() == o.circle.getCenterY()) && !upperSegment && o.upperSegment) return -1;
        else if ((circle.getCenterY() == o.circle.getCenterY()) && !upperSegment && !o.upperSegment) return 1;
        else return Double.compare(circle.getCenterY(), o.circle.getCenterY());
    }

}