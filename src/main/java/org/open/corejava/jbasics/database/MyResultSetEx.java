package org.open.corejava.jbasics.database;

import java.util.List;

public class MyResultSetEx {

	public static void main(String[] a) throws Exception {
		JavaDBConnection obj = new JavaDBConnection();

		List<AirportModel> result = obj.getAirports();

		System.out.println(result.get(3000));
	}
}