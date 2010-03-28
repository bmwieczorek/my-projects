package designpatterns.flyweight.copy;


class Point {
    int price;

    String name;

    void advertise() {
        System.out.println(price + ":" + name);
    }

    public Point(int price, String name) {
        System.out.println("Created point:" + price + ":" + name);
        this.price = price;
        this.name = name;
    }
}

class NoDataPoint {
    public NoDataPoint() {
        System.out.println("Created no data point");
    }
    void advertise(int price, String name) {
        System.out.println(price + ":" + name);
    }
}

public class Example {

    //---------------------------------
    //         No Data point
    //---------------------------------
    int index = 0;
    int[] ints = new int[10];
    String[] strings = new String[10];
    NoDataPoint noDataPoint = new NoDataPoint();
    void push(int i, String name) {
        ints[index] = i;
        strings[index++] = name;
    }
    void popAll(){
        for (int i = 0; i < index; i++){
            noDataPoint.advertise(ints[i],strings[i]);
        }
    }

    //---------------------------------
    //          Data point
    //---------------------------------
    Point[] points = new Point[10];
    int p = 0;
    void add(int i, String name) {
        points[p++] = new Point(i, name);
    }
    void remove() {
        for (int i = 0; i < p; i++) {
            points[i].advertise();
        }
    }


    //---------------------------------
    //          Main
    //---------------------------------
    
    public static void main(String[] args) {
        Example example = new Example();
//        example.add(1, "a");
//        example.add(2, "bb");
//        example.add(3, "ccc");
//        example.remove();
        
        
        example.push(1, "a");
        example.push(2, "bb");
        example.push(3, "ccc");
        example.popAll();

        
    }
}
