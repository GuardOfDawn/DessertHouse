package dessert.utility;

import java.sql.Date;

public class TestMain {

	public static void main(String[] args) {
		String a = "2016-03-21";
		Date date = DayTransformer.transform(a);
		Date date2 = DayTransformer.getNewDate(date, 1);
	}
	
}
