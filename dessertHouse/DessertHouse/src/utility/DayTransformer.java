package utility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DayTransformer {

	public static String transform(Date dateTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());//获取当前时间       
		String str = formatter.format(curDate);
		return str;
	}
	
}
