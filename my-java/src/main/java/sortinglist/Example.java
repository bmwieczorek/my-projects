package sortinglist;

class MyList {
    public int i;
    public MyList next;

    public MyList(int i, MyList next) {
        this.i = i;
        this.next = next;
    }
}

public class Example {
    public static void main(String[] args) {
        MyList myList = new MyList(1, new MyList(2, new MyList(3, new MyList(4, null))));
        printList(myList);
        System.out.println("--");
        printList(reverseList(myList));
    }

    private static void printList(MyList myList) {
        while (myList != null) {
            System.out.println(myList.i);
            myList = myList.next;
        }
    }

    private static MyList reverseList(MyList myList) {
        MyList result = null;
        MyList tmp = null;
        while (myList != null) {
            result = new MyList(myList.i, myList.next);
            result.next = tmp;
            tmp = result;
            myList = myList.next;
        }
        return result;
    }
}
