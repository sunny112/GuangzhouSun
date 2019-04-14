package amazonOA;

import java.util.Comparator;
import java.util.PriorityQueue;

class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }
public class Kcloset {
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] res = new Point[k];
        if(points == null || points.length == 0) return res;
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b){
                // int diff = Double.compare(getDistance(a, origin) - getDistance(b, origin));
                int diff = getDistance(b, origin) - getDistance(a, origin);
                if(diff == 0){
                    if (a.x > b.x) {
                        return 0;
                    }else if (a.x < b.x) {
                        return 1;
                    }else {
                        if (a.y <= b.y) {
                            return 1;
                        }else{
                            return 0;
                        }
                    }
                }
                return diff;
            }
        });
        for(int i = 0; i < points.length; i++){
            if(i < k){
                pq.offer(points[i]);
            }else{
                Point top = pq.peek();
                int topDistance = getDistance(top, origin);
                int curDistance = getDistance(points[i], origin);
                if(topDistance >= curDistance){
                    pq.poll();
                    pq.offer(points[i]);
                }
            }
        }

        while(k - 1 >= 0){
            res[--k] = pq.poll();
        }
        return res;
    }
    private int getDistance(Point a, Point b){
        return (a.x * a.x - b.x * b.x) + (a.y * a.y - b.y * b.y);
    }
    public static void main(String[] arg){
        Kcloset sol = new Kcloset();
        Point p1 = new Point(4,6);
        Point p2 = new Point(4,6);
        Point p3 = new Point(4,6);
        Point p4 = new Point(-4,-6);
        Point p5 = new Point(-4,6);
        Point[] points = new Point[5];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;
        sol.kClosest(points, new Point(0, 0), 3);
    }
}
