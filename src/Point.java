import java.util.*;

public class Point {



    private int x;
    private int y;
    private double cf;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getCf() {
        return cf;
    }

    public void setCf(double cf) {
        this.cf = cf;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }


    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;

    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }


    public static List<Point> makeListOfOccupiedPoints(String stringPoints) {
        List<Point> points = new java.util.ArrayList();
        String[] fullPoints = stringPoints.split(",");
        for (String fullPoint : fullPoints)
        {
            fullPoint = fullPoint.replaceAll("[{]", "").replaceAll("[}]", "");
            String[] xy = fullPoint.split(";");
            points.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
        }
        return points;
    }

}

