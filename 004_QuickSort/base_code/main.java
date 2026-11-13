import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	static final long createdNano = System.nanoTime();

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

			BaseClass.randomize(nums);
			BaseClass.quickSort(nums);

			s.stop();
			System.out.println("Duration: " + Stopwatch.readable(s.read()));
			System.out.println("");
		}

		ArrayList<DayBirth> all = BaseClass.readBirths("births.csv");
		DayBirth[] males = BaseClass.filterByGender(all, "M");
		DayBirth[] females = BaseClass.filterByGender(all, "F");
		BaseClass.quickSort(males);
		BaseClass.quickSort(females);
		BaseClass.exportCsv(males, "males_sorted.csv");
		BaseClass.exportCsv(females, "females_sorted.csv");
		System.out.println("Exported " + males.length + " male and " + females.length + " female days");
	}
}
