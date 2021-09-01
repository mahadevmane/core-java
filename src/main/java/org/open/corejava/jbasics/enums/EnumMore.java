package org.open.corejava.jbasics.enums;

public class EnumMore {
	public enum Status {
		STATUS_OPEN(0, "Open"), 
		STATUS_STARTED(1, "Started"), 
		STATUS_INPROGRESS(2, "In Progress"), 
		STATUS_ONHOLD(3, "On Hold"), 
		STATUS_COMPLETED(4, "Completed"), 
		STATUS_CLOSED(5, "Closed");

		private final int status;
		private final String description;

		Status(int status, String desc) {
			this.status = status;
			this.description = desc;
		}

		public int status() {
			return this.status;
		}

		public String description() {
			return this.description;
		}
	}

	private static void checkStatus(Status status) {
		switch (status) {
		case STATUS_OPEN:
			System.out.println("This is \"Open\" status.");
		case STATUS_STARTED:
			System.out.println("This is \"Started\" status.");
		case STATUS_INPROGRESS:
			System.out.println("This is \"In Progress\" status.");
		case STATUS_ONHOLD:
			System.out.println("This is \"On Hold\" status.");
		case STATUS_COMPLETED:
			System.out.println("This is \"Completed\" status.");
		case STATUS_CLOSED:
			System.out.println("This is \"Closed\" status.");
		}
	}

	public static void main(String[] args) {
		checkStatus(Status.STATUS_CLOSED);
		for (Status s : Status.values()) {
			System.out.println(s.status() + "\t" + s.description());
		}
	}
}
