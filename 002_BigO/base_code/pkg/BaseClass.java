package pkg;
import java.util.Scanner;


public class BaseClass {

	public BaseClass() { }

	public static void randomize(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 200001);
		}
	}

	public static boolean search(int[] arr) {
		int target = (int)(Math.random() * 200001);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) return true;
		}
		return false;
	}

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean swapped = false;
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) return;
		}
	}

	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int a = arr[i];
			arr[i] = arr[min];
			arr[min] = a;
		}
	}

}
