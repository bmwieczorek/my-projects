import java.util.Arrays;

public class SortSample {
    public void printSorted(int[] arrayToSort) {

        Arrays.sort(arrayToSort);
        System.out.print("Sorted array is ");
        for (int j = 0; j < arrayToSort.length; j++) {
            System.out.print(arrayToSort[j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new SortSample().printSorted(new int[] { 1, 9, 7, 4, 3, 6 });
        int i = 0;
        for (; i == 0; ++i) {
            System.out.println(i);
        }
        int x = 1, y = 1, z = 3;
        x += (++y + z--);
        System.out.println(x);
        System.exit(0);
    }
}