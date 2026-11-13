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

	public static void quickSort(int[] arr) {
		if (arr.length < 2) return;
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high);
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int mid = (low + high) / 2;
		int temp = arr[mid];
		arr[mid] = arr[high];
		arr[high] = temp;

		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
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

	public static void quickSort(DayBirth[] arr) {
		if (arr.length < 2) return;
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(DayBirth[] arr, int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high);
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}

	private static int partition(DayBirth[] arr, int low, int high) {
		int mid = (low + high) / 2;
		DayBirth temp = arr[mid];
		arr[mid] = arr[high];
		arr[high] = temp;

		int pivot = arr[high].getBirths();
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j].getBirths() < pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
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
