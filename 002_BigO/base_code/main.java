import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	public static void main(String args[]) {
		int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		int [] nums = new int[100];
		Stopwatch s = new Stopwatch();

		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for(int i : times){
			System.out.println("Interval: " + i);
			s.start();	
			nums = new int[i];
			//  Put your method between these two comments
			BaseClass.randomize(nums);
			BaseClass.search(nums);

			//System.out.println("Starting Insertion Sort");
			//BaseClass.insertionSort(nums);
			//System.out.println("Starting Selection Sort");
			//BaseClass.selectionSort(nums);
			System.out.println("Starting Bubble Sort");
			BaseClass.bubbleSort(nums);

			//
			s.stop();
			System.out.println("Duration: " + Stopwatch.readable(s.read()));
			System.out.println("");
		}
	}
}

