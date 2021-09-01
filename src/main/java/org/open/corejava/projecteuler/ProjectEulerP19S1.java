package org.open.corejava.projecteuler;

public class ProjectEulerP19S1 {
	public static void main(String[] args) {
		int result = 0;
		MyDate md = new MyDate(2, 1, 1, 1901);

		System.out.println(md);
		while (md.getYear() <= 2000) {
			if (md.getDay() == 0) {
				result++;
				System.out.println(md);
			}

			md.addDays(md.getMonthDays());
		}

		System.out.println(md);
		System.out.println(result);
	}
}

class MyDate {
	private final static int MMDAYS = 28;
	private final static int YDays = 365;
	private final static int LYDays = 366;
	private final static String[] WeekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	private int day, date, month, year;

	public MyDate() {
		this.day = 1;
		this.date = 1;
		this.month = 1;
		this.year = 1900;
	}

	public MyDate(int day, int date, int month, int year) {
		this.day = day;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public MyDate(MyDate myDate) {
		this.day = myDate.day;
		this.date = myDate.date;
		this.month = myDate.month;
		this.year = myDate.year;
	}

	public void addDays(int days) {
		if (days < 1)
			return;

		this.day = (this.day + days) % 7;
		int m = this.month, d = this.date, monthDays;

		while (days >= YDays) {
			if (isLeapYear(this.year)) {
				if (days < LYDays)
					break;
				days -= LYDays;
			} else {
				days -= YDays;
			}

			this.year++;
		}

		while (days >= MMDAYS) {
			monthDays = getMonthDays();
			if (days < monthDays) {
				break;
			}

			days -= monthDays;
			this.month = (this.month % 12) + 1;
		}

		this.date += days;
		while (this.date > (monthDays = getMonthDays())) {
			if (this.date > monthDays) {
				this.date = this.date % monthDays;
			}

			if (this.date < d) {
				this.month = (this.month % 12) + 1;
			}
		}

		if (this.month < m) {
			this.year++;
		}
	}

	public int getMonthDays() {
		if (this.month == 2) {
			if (isLeapYear(this.year))
				return 29;
			return 28;
		} else {
			if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11)
				return 30;
			return 31;
		}
	}

	public boolean isLeapYear() {
		return isLeapYear(this.year);
	}

	public boolean isLeapYear(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }

	@Override
	public String toString() {
		return this.date + "/" + this.month + "/" + this.year + ", " + WeekDays[this.day];
	}

	public int getDay() {
		return day;
	}

	public int getDate() {
		return date;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
}