package passbyvalueorreference;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Example {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        System.out.println(p1.x + ":" + p1.y);
        System.out.println(p2.x + ":" + p2.y);
        swap(p1, p2);
        System.out.println(p1.x + ":" + p1.y);
        System.out.println(p2.x + ":" + p2.y);
        int i = 1;
        int j = 2;
        swap(i, j);
        System.out.println(i + ":" + j);

    }

    static void swap(Point p1, Point p2) {
        p1.x = 100;
        p1.y = 200;
        Point p = p1;
        p1 = p2;
        p2 = p;
    }

    static void swap(int i, int j) {
        int tmp = i;
        i = j;
        j = tmp;
    }
}
