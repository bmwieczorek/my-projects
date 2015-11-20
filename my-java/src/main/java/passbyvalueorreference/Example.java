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
        Point p1 = new Point(0, 1);
        Point p2 = new Point(2, 3);
        System.out.println(p1.x + ":" + p1.y); // 0 1
        System.out.println(p2.x + ":" + p2.y); // 2 3
        swap(p1, p2);
        System.out.println(p1.x + ":" + p1.y); // 100 200 since swap internally modifies the state of Point referenced by p1
        System.out.println(p2.x + ":" + p2.y); // 2   3 // remain unchanged since no internal state modification Point referenced by p2
        int i = 1;
        int j = 2;
        swap(i, j);
        System.out.println(i + ":" + j); // 1 2 (remain unchanged since swap gets values of primitives 

    }

    static void swap(Point p1, Point p2) { // gets value of the reference to Point object (not the Point object directly) 
        p1.x = 100;
        p1.y = 200;
        Point p = p1;
        p1 = p2;
        p2 = p;
    }

    static void swap(int i, int j) { // get values of primitives
        int tmp = i;
        i = j;
        j = tmp;
    }
}
