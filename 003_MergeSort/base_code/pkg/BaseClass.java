package pkg;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class BaseClass {
	public BaseClass() {

	}

	public static void randomize(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 200001);
		}
	}

	public static void mergeSort(int[] arr) {
		int[] temp = new int[arr.length];
		mergeSortHelper(arr, 0, arr.length - 1, temp);
	}

	private static void mergeSortHelper(int[] arr, int from, int to, int[] temp) {
		if (from < to) {
			int middle = (from + to) / 2;
			mergeSortHelper(arr, from, middle, temp);
			mergeSortHelper(arr, middle + 1, to, temp);
			merge(arr, from, middle, to, temp);
		}
	}

	private static void merge(int[] arr, int from, int middle, int to, int[] temp) {
		int i = from;
		int j = middle + 1;
		int k = from;

		while (i <= middle && j <= to) {
			if (arr[i] <= arr[j]) {
				temp[k] = arr[i];
				i++;
			}
			else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			temp[k] = arr[i];
			i++;
			k++;
		}

		while (j <= to) {
			temp[k] = arr[j];
			j++;
			k++;
		}

		for (k = from; k <= to; k++) {
			arr[k] = temp[k];
		}
	}

	public static ArrayList<DayBirth> readBirths(String fileName) {
		ArrayList<DayBirth> list = new ArrayList<DayBirth>();
		try {
			Scanner sc = new Scanner(new File(fileName));
			sc.nextLine();

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split(",");

				if (parts.length == 5 && !line.contains("null")) {
					int year = Integer.parseInt(parts[0]);
					int month = Integer.parseInt(parts[1]);
					int day = Integer.parseInt(parts[2]);
					String gender = parts[3];
					int births = Integer.parseInt(parts[4]);

					DayBirth d = new DayBirth(year, month, day, gender, births);
					list.add(d);
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not find " + fileName);
		}
		return list;
	}

	public static DayBirth[] filterByGender(ArrayList<DayBirth> list, String gender) {
		int count = 0;
		for (DayBirth d : list) {
			if (d.getGender().equals(gender)) count++;
		}

		DayBirth[] result = new DayBirth[count];
		int index = 0;
		for (DayBirth d : list) {
			if (d.getGender().equals(gender)) {
				result[index] = d;
				index++;
			}
		}
		return result;
	}

	public static void mergeSort(DayBirth[] arr) {
		DayBirth[] temp = new DayBirth[arr.length];
		mergeSortHelper(arr, 0, arr.length - 1, temp);
	}

	private static void mergeSortHelper(DayBirth[] arr, int from, int to, DayBirth[] temp) {
		if (from < to) {
			int middle = (from + to) / 2;
			mergeSortHelper(arr, from, middle, temp);
			mergeSortHelper(arr, middle + 1, to, temp);
			merge(arr, from, middle, to, temp);
		}
	}

	private static void merge(DayBirth[] arr, int from, int middle, int to, DayBirth[] temp) {
		int i = from;
		int j = middle + 1;
		int k = from;

		while (i <= middle && j <= to) {
			if (arr[i].getBirths() <= arr[j].getBirths()) {
				temp[k] = arr[i];
				i++;
			}
			else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			temp[k] = arr[i];
			i++;
			k++;
		}

		while (j <= to) {
			temp[k] = arr[j];
			j++;
			k++;
		}

		for (k = from; k <= to; k++) {
			arr[k] = temp[k];
		}
	}

	public static void exportCsv(DayBirth[] arr, String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName));
			out.println("year,month,day,gender,births");
			for (int i = 0; i < arr.length; i++) {
				out.println(arr[i].toCsv());
			}
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not write " + fileName);
		}
	}

}
