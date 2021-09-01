package org.open.corejava.jbasics.multitread;

public class UsingThread extends Thread {
	private String str;
	static int cnt;

	public UsingThread(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		String thread = getName();
		for (; cnt < str.length(); cnt++) {
			try {
				System.out.println("Thread Name: " + thread + ", " + str.charAt(cnt));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
