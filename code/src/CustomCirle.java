import javafx.scene.shape.Circle;

class CustomCirle extends Circle implements Comparable<CustomCirle>{
    public CustomCirle(double x, double y, double radius){
        super(x, y, radius);
    }

    @Override
    public int compareTo(CustomCirle o) {
        if ((getCenterY() == o.getCenterY()) && (getCenterX() == o.getCenterX()) && (getRadius() == o.getRadius())) return 0;
        else return Double.compare(getCenterY(), o.getCenterY());
    }
}
