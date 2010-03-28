package effectivejava.item11.publicaccess;

class Item {
    private final int price;

    public Item(int price) {
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
}

public class PublicAccess {

    public static final double PI = 3.14;

    public static final Item CAR = new Item(100);

    // cannot be public
    public static final int[] ARR = { 1, 2, 3 };

    public static void main(String[] args) {
        System.out.println(ARR[1]);
        ARR[1] = 999;
        System.out.println(ARR[1]);

        // ref cannot change, but array elements yes
        // ARR = { 9,8,7 };
        
        // cannot reassign because of final
        // CAR = new Item(200);
        // CAR.price = 200;
    }

}
