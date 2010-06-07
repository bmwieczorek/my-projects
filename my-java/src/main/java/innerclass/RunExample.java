package innerclass;

public class RunExample {

    static void print(Shape shape) {
        System.out.println(shape.toString() + ",area=" + shape.area() + ",perimeter=" + shape.perimeter());
    }

    static class Triangle extends AbstractShape {
        private final int a, b, c;

        private final int p;

        public Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            p = (a + b + c) / 2;
        }

        public int area() {
            return (int) Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }

        public int perimeter() {
            return a + b + c;
        }

        @Override
        public String color() {
            return "trianglish";
        }

    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(3, 4);
        print(rectangle);
        Shape sqare = new Shape() {
            int a = 5;

            public int area() {
                return a * a;
            }

            public int perimeter() {
                return 4 * a;
            }

            // will not ever be used because is not part of API
            // public String color(){
            // return "squarish";
            // }

        };
        print(sqare);
        Shape triagle = new Triangle(3, 4, 5);
        print(triagle);
        System.out.println(((Triangle) triagle).color());
    }

}
