package dessert.utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DayTransformer {

	public static String transform(Date dateTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
		String str = formatter.format(dateTime);
		return str;
	}
	
	public static String transformToMonth(Date dateTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");// HH:mm:ss
		String str = formatter.format(dateTime);
		return str;
	}

	public static Date transform(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			java.util.Date d = formatter.parse(dateString);
			date = new Date(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getNewDate(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date newDate = new Date(calendar.getTimeInMillis());
		return newDate;
	}
	
	public static Date getNewMonth(Date date,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, month);
		Date newDate = new Date(calendar.getTimeInMillis());
		return newDate;
	}
	
}
