package pkg;


public class DayBirth {
	private int year;
	private int month;
	private int day;
	private String gender;
	private int births;

	public DayBirth(int year, int month, int day, String gender, int births) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.births = births;
	}

	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public String getGender() {
		return gender;
	}
	public int getBirths() {
		return births;
	}

	public String toCsv() {
		return year + "," + month + "," + day + "," + gender + "," + births;
	}

	public String toString() {
		return month + "/" + day + "/" + year + " " + gender + ": " + births;
	}
}
