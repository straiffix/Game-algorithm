import java.awt.*;
import java.util.Objects;

public class PairOfPoints {


    private Point point1;
    private Point point2;

    public boolean contains(PairOfPoints p){
//        if (point1.x == p.point1.x || point1.x == p.point2.x || point1.y == p.point1.y ||point1.y == p.point2.y || point2.x == p.point1.x || point2.x == p.point2.x ||
//                point2.y == p.point1.y || point2.y == p.point2.y) return true;
        if(point1.equals(p.point1)|| point1.equals(p.point2) || point2.equals(p.point1) || point2.equals(p.point2) ) return true;
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairOfPoints that = (PairOfPoints) o;
        return (Objects.equals(point1, that.point1) &&
                Objects.equals(point2, that.point2)) || (Objects.equals(point1, that.point2) &&
                Objects.equals(point2, that.point1)) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(point1, point2);
    }

    @Override
    public String toString() {
        return "{" + point1.getX() + ";" + point1.getY() + "},{" + point2.getX() + ";" + point2.getY() + "}";

    }

    public PairOfPoints(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
    }
    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }
}
