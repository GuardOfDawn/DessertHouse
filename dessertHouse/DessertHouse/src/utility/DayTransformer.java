package utility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DayTransformer {

	public static String transform(Date dateTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
		String str = formatter.format(curDate);
		return str;
	}
	
}
