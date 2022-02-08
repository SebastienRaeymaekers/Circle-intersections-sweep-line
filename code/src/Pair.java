import java.util.Objects;

public class Pair<S, T> {
    public S x;
    public T y;

    public Pair(S x, T y) {
        this.x = x;
        this.y = y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public S getX() {
        return x;
    }

    public void setX(S x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Pair pair = (Pair) object;
        return x.equals(pair.x) && y.equals(pair.y);
    }
}