package sortinglist;

public class ReverseArray {

    public static void main(String[] args) {
        int[] ints = new int[] { 1, 2, 3, 4 };
        printArray(ints);
        System.out.println("--");
        printArray(reverseArray(ints));
    }

    private static void printArray(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    private static int[] reverseArray(int ints[]) {
        for (int i = 0, j = ints.length - 1; i < j; i++, j--) {
            int tmp = ints[i];
            ints[i] = ints[j];
            ints[j] = tmp;
            System.out.println(i + ":" + j);
        }
        return ints;
    }

    @SuppressWarnings("unused")
    private static int[] reverseArray2(int ints[]) {
        for (int i = 0; i < ints.length / 2; i++) {
            int j = ints.length - 1 - i;
            System.out.println(i + ":" + j);
            int tmp = ints[i];
            ints[i] = ints[j];
            ints[j] = tmp;
        }
        return ints;
    }

}
