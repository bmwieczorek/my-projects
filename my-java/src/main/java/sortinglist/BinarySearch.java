package sortinglist;

public class BinarySearch {
	static int i = 1;

	public static void main(String[] args) {
		int[] data = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		
		for (int i = 0; i < data.length; i++){
			//loopSearch(data, i);
			//recursiveSearch(data, 0, data.length-1, i);
			recursiveSearch1(data, 0, data.length-1, i);
		}
	}

	static void loopSearch(int[] data, int search) {
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			System.out.println(i++);
			int middle = (high + low) / 2;
			if (data[low] == search || data[high] == search
					|| data[middle] == search) {
				System.out.println("Done" + search);
				return;
			}
			if (data[middle] < search) {
				low = middle;
			} else {
				high = middle;
			}
			low++;
			high--;
		}
		System.out.println("Not found" + search);
	}

	static void recursiveSearch(int[] data, int low, int high, int search) {
		System.out.println(i++);
		if (high < low) {
			System.out.println("Not found" + search);
			return;
		}
		int middle = (high + low) / 2;
		if (data[middle] == search) {
			System.out.println("Done" + search);
			return;
		}
		if (data[middle] < search) {
			recursiveSearch(data, middle + 1, high, search);
		} else {
			recursiveSearch(data, low, middle - 1, search);
		}
	}

	static void recursiveSearch1(int[] data, int low, int high, int search) {
		System.out.println(i++);
		if (high < low) {
			System.out.println("Not found" + search);
			return;
		}
		int middle = (high + low) / 2;
		if (data[low] == search || data[high] == search
				|| data[middle] == search) {
			System.out.println("Done" + search);
			return;
		}
		if (data[middle] < search) {
			low = middle;
		} else {
			high = middle;
		}
		recursiveSearch1(data, low++, high--, search);
	}
}
