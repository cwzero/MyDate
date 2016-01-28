
public class MyDate {
	private int year;
	private int month;
	private int day;

	private static String[] strMonths = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };
	private static String[] strDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	private static int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
	}

	public static boolean isValidDate(int year, int month, int day) {
		return ((year >= 1) && (year <= 9999)) && ((month >= 1) && (month <= 12))
				&& ((day >= 1) && (day <= daysInMonth[month]));
	}

	public static int getDayOfWeek(int y, int m, int d) {
		int[][] daytab = { { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int daystotal = 0;
		for (int month = 1; month <= m; month++) {
			if (isLeapYear(y)) {
				d += daytab[1][month - 1];
			} else {
				d += daytab[0][month - 1];
			}
		}
		daystotal = 365 * (y - 1) + (int) Math.floor((y - 1) / 4) - (int) Math.floor((y - 1) / 100)
				+ (int) Math.floor((y - 1) / 400) + d;
		return daystotal % 7;
	}

	public MyDate(int year, int month, int day) {
		setDate(year, month, day);
	}

	public void setDate(int year, int month, int day) {
		if (isValidDate(year, month, day)) {
			this.year = year;
			this.month = month;
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid year, month or day!");
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (isValidDate(year, month, day)) {
			this.year = year;
		} else {
			throw new IllegalArgumentException("Invalid year!");
		}
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if (isValidDate(year, month, day)) {
			this.month = month;
		} else {
			throw new IllegalArgumentException("Invalid month!");
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if (isValidDate(year, month, day)) {
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid day!");
		}
	}

	@Override
	public String toString() {
		return strDays[getDayOfWeek(year, month, day)] + " " + day + " " + strMonths[month - 1] + " " + year;
	}

	public MyDate nextDay() {
		if (day != daysInMonth[month - 1]) {
			day++;
		} else {
			if (month < 11) {
				month++;
			} else {
				month = 1;
				year++;
			}
			day = 1;
		}
		return this;
	}

	public MyDate nextMonth() {
		if (month == 12) {
			if (day == daysInMonth[month - 1] || day > daysInMonth[1]) {
				day = daysInMonth[1];
			}
			month = 1;
			year++;
		} else {
			if (day == daysInMonth[month - 1] || day > daysInMonth[month]) {
				day = daysInMonth[month];
			}
			month++;
		}
		return this;
	}

	public MyDate nextYear() {
		if (isLeapYear(year) && month == 2 && day == 29) {
			day = 28;
		}
		if (year < 9999)
			year++;
		return this;
	}

	public MyDate previousDay() {
		if (day > 1) {
			day--;
		} else {
			if (month == 3 && isLeapYear(year)) {
				month = 2;
				day = 29;
			} else if (month > 1) {
				month--;
				day = daysInMonth[month - 1];
			} else {
				year--;
				month = 12;
				day = daysInMonth[month - 1];
			}
		}
		return this;
	}

	public MyDate previousMonth() {
		if (month > 1) {
			month--;
		} else {
			year--;
			month = 12;
		}
		if (day > daysInMonth[month - 1])
			day = daysInMonth[month - 1];
		return this;
	}

	public MyDate previousYear() {
		if (year > 1) {
			if (isLeapYear(year) && month == 2 && day == 29) {
				day = 28;
			}
			year--;
		}
		return this;
	}

	public static void main(String[] args) {
		MyDate d1 = new MyDate(2012, 2, 28);
		System.out.println(d1); // Saturday 28 Feb 2015
		System.out.println(d1.nextDay()); // Monday 29 Feb 2016
		System.out.println(d1.nextDay()); // Tuesday 1 Mar 2016
		System.out.println(d1.nextMonth()); // Friday 1 Apr 2016
		System.out.println(d1.nextYear()); // Saturday 1 Apr 2017

		MyDate d2 = new MyDate(2012, 1, 2);
		System.out.println(d2); // Saturday 2 Jan 2016
		System.out.println(d2.previousDay()); // Friday 1 Jan 2016
		System.out.println(d2.previousDay()); // Thursday 31 Dec 2015
		System.out.println(d2.previousMonth()); // Wednesday 2 Dec 2015
		System.out.println(d2.previousYear()); // Tuesday 2 Dec 2014

		MyDate d3 = new MyDate(2016, 2, 29);
		System.out.println(d3.previousYear()); // Saturday 28 Feb 2015

		// MyDate d4 = new MyDate(2099, 11, 31); // Invalid year, month, or day!
		// MyDate d5 = new MyDate(2017, 2, 29); // Invalid year, month, or day!

		// Part 2 //
		System.out.println();
		System.out.println();

		MyDate date = new MyDate(2015, 2, 28);

		while (!(date.getYear() == 2016 && date.getMonth() == 3 && date.getDay() == 2)) {
			date = date.nextDay();
			System.out.println(date);
		}
	}
}
