import java.util.*;

public class Board {

    private int size;
    boolean board [][];
    private int freePoints;
    LinkedList<Point> listOfFreePoints;

    public void setFreePoints(int freePoints) {
        this.freePoints = freePoints;
    }

    public int getFreePoints() {
        return freePoints;
    }



    public Board(int size, List<Point> listOfPoints){
        this.size = size;
        board = new boolean[size][size];
        this.freePoints = size * size;
        this.listOfFreePoints = new LinkedList<Point>();
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if (listOfPoints.contains(new Point(i, j))){
                    board[i][j]=false;
                    freePoints--;
                } else {
                    board[i][j]=true;
                }
            }
        }
    }

    public void makeListOfFreePoints(){
        for (int i = 0; i < size; i++){
            for(int j = 0; j< size; j++){
                if (board[i][j] == true){
                    Point point = new Point(i, j);
                    point.setCf(point.getX()*point.getY()*size*2/100);
                    if (point.getCf() > 1){
                        point.setCf(point.getCf() / (size * size));
                    }
                    listOfFreePoints.add(point);
                }
            }
        }
        Collections.sort(listOfFreePoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getCf(), o2.getCf()) * -1;
            }
        });

    }

    public  void move() {
        ArrayList<Point> closest;
        for (Point point : listOfFreePoints) {
            closest = checkClosest(point);
            if (closest.isEmpty() == false) {
                Player.print(point.getX(), point.getY(), closest.get(0).getX(), closest.get(0).getY());
                board[point.getX()][point.getY()] = false;
                board[closest.get(0).getX()][closest.get(0).getY()] = false;
                listOfFreePoints.remove(point);
                listOfFreePoints.remove(closest.get(0));
                freePoints-=2;
                break;
            }
        }
    }
    public  void moveFinal(PairOfPoints pair) {
                System.out.println(pair);
                board[pair.getPoint1().getX()][pair.getPoint1().getY()] = false;
                board[pair.getPoint2().getX()][pair.getPoint2().getY()] = false;
                listOfFreePoints.remove(pair.getPoint1());
                listOfFreePoints.remove(pair.getPoint2());
                freePoints-=2;

    }


    public ArrayList<Point> checkClosest(Point point){
        ArrayList<Point> closest = new ArrayList<Point>();
        if (checkOnCorner(point)==true){
            closest=findOnCorner(point);
        } else if (checkOnEdge(point)==true){
            closest = findOnEdge(point);
        } else closest = findUsual(point);
            return  closest;
    }


    public boolean checkOnCorner(Point point){
        if ((point.getX() == 0 && point.getY() == 0) || (point.getX() == size-1 && point.getY() == 0) || (point.getX() == 0 && point.getY() == size - 1) || (point.getX() == size-1 && point.getY() == size-1)){
            return true;
        } else return false;
    }

    public boolean checkOnEdge(Point point){
        if ((point.getX() == 0 || point.getY() == 0 || point.getX() == size-1 || point.getY() == size-1) && checkOnCorner(point)==false){
            return true;
        }
        else return false;
    }

    public ArrayList<Point> findUsual(Point point){
        ArrayList<Point> closest = new ArrayList<Point>();
        if (board[point.getX()-1][point.getY()]!=false){
            closest.add(new Point(point.getX()-1, point.getY()));
        }
        if (board[point.getX()+1][point.getY()]!=false){
            closest.add(new Point(point.getX()+1, point.getY()));
        }
        if (board[point.getX()][point.getY()+1]!=false){
            closest.add(new Point(point.getX(), point.getY()+1));
        }
        if (board[point.getX()][point.getY()-1]!=false){
            closest.add(new Point(point.getX(), point.getY()-1));
        }
        return closest;

    }
    public ArrayList<Point> findOnEdge(Point point){
        ArrayList<Point> closest = new ArrayList<Point>();
        if (point.getX() == 0){
            if (board[size-1][point.getY()]!=false){
                closest.add(new Point(size-1, point.getY()));
            }
            if (board[point.getX()+1][point.getY()]!=false){
                closest.add(new Point(point.getX()+1, point.getY()));
            }
            if (board[point.getX()][point.getY()+1]!=false){
                closest.add(new Point(point.getX(), point.getY()+1));
            }
            if (board[point.getX()][point.getY()-1]!=false){
                closest.add(new Point(point.getX(), point.getY()-1));
        }
        }
        if (point.getX() == size-1){
            if (board[point.getX()-1][point.getY()]!=false){
                closest.add(new Point(point.getX()-1, point.getY()));
            }
            if (board[0][point.getY()]!=false){
                closest.add(new Point(0, point.getY()));
            }
            if (board[point.getX()][point.getY()+1]!=false){
                closest.add(new Point(point.getX(), point.getY()+1));
            }
            if (board[point.getX()][point.getY()-1]!=false){
                closest.add(new Point(point.getX(), point.getY()-1));
            }
        }
        if (point.getY() == 0){
            if (board[point.getX()-1][point.getY()]!=false){
                closest.add(new Point(point.getX()-1, point.getY()));
            }
            if (board[point.getX()+1][point.getY()]!=false){
                closest.add(new Point(point.getX()+1, point.getY()));
            }
            if (board[point.getX()][point.getY()+1]!=false){
                closest.add(new Point(point.getX(), point.getY()+1));
            }
            if (board[point.getX()][size-1]!=false){
                closest.add(new Point(point.getX(), size-1));
            }
        }
        if (point.getY() == size - 1){
            if (board[point.getX()-1][point.getY()]!=false){
                closest.add(new Point(point.getX()-1, point.getY()));
            }
            if (board[point.getX()+1][point.getY()]!=false){
                closest.add(new Point(point.getX()+1, point.getY()));
            }
            if (board[point.getX()][0]!=false){
                closest.add(new Point(point.getX(), 0));
            }
            if (board[point.getX()][point.getY()-1]!=false){
                closest.add(new Point(point.getX(), point.getY()-1));
            }
        }
        return closest;
    }

    public ArrayList<Point> findOnCorner(Point point){
        ArrayList<Point> closest = new ArrayList<Point>();
        if (point.getX() == 0 && point.getY() == 0){
            if (board[size-1][point.getY()]!=false){
                closest.add(new Point(size-1, point.getY()));
            }
            if (board[point.getX()+1][point.getY()]!=false){
                closest.add(new Point(point.getX()+1, point.getY()));
            }
            if (board[point.getX()][point.getY()+1]!=false){
                closest.add(new Point(point.getX(), point.getY()+1));
            }
            if (board[point.getX()][size-1]!=false){
                closest.add(new Point(point.getX(), size-1));
            }
        }
        if (point.getX() == size-1 && point.getY() == 0){
            if (board[point.getX()-1][point.getY()]!=false){
                closest.add(new Point(point.getX()-1, point.getY()));
            }
            if (board[0][point.getY()]!=false){
                closest.add(new Point(0, point.getY()));
            }
            if (board[point.getX()][point.getY()+1]!=false){
                closest.add(new Point(point.getX(), point.getY()+1));
            }
            if (board[point.getX()][size-1]!=false){
                closest.add(new Point(point.getX(), size-1));
            }

        }
        if(point.getX() == 0 && point.getY() == size-1){
            if (board[size-1][point.getY()]!=false){
                closest.add(new Point(size-1, point.getY()));
            }
            if (board[point.getX()+1][point.getY()]!=false){
                closest.add(new Point(point.getX()+1, point.getY()));
            }
            if (board[point.getX()][0]!=false){
                closest.add(new Point(point.getX(), 0));
            }
            if (board[point.getX()][point.getY()-1]!=false){
                closest.add(new Point(point.getX(), point.getY()-1));
            }
        }
        if(point.getX() == size-1 && point.getY() == size-1){
            if (board[point.getX()-1][point.getY()]!=false){
                closest.add(new Point(point.getX()-1, point.getY()));
            }
            if (board[0][point.getY()]!=false){
                closest.add(new Point(0, point.getY()));
            }
            if (board[point.getX()][0]!=false){
                closest.add(new Point(point.getX(), 0));
            }
            if (board[point.getX()][point.getY()-1]!=false){
                closest.add(new Point(point.getX(), point.getY()-1));
            }
        }
        return closest;

    }
}
