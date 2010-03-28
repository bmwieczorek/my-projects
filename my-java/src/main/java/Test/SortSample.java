package Test;

public class SortSample {
    public void printSorted(int arrayToSort[]) {

        java.util.Arrays.sort(arrayToSort);
        // Insert code to sort the array here

        System.out.print("Sorted array is ");
        for (int j = 0; j < arrayToSort.length; j++)
            System.out.print(arrayToSort[j] + " ");
        System.out.println();
    }

}