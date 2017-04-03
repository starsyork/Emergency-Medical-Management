package com.cecwj.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormat {
	public static String timeStampToString(Timestamp timestamp) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(timestamp);
		return str;
	}

	public static String timeStampToStringB(Timestamp timestamp) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String str = df.format(timestamp);
		return str;
	}

	public static String timeStampToStringA(Timestamp timestamp) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(timestamp);
		return str;
	}

	public static Timestamp TimestampToTimestamp(Timestamp timestamp) {
		String str = timeStampToString(timestamp);
		str = str.substring(0, 10);
		String str2 = " 00:00:00";
		str = str + str2;
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}

	public static Timestamp stringToTimestamp(String str) {
		str = str.replaceAll("/", "-");
		str = str.replaceAll("T", " ");
		Timestamp ts = Timestamp.valueOf(str);
		return ts;
	}

	public static Timestamp TimestampAddOneDay(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String data = timeStampToString(timestamp);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(data));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String dateStr = sdf.format(calendar.getTime());
			timestamp = stringToTimestamp(dateStr);
			timestamp = TimestampToTimestamp(timestamp);
			return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			e.printStackTrace();
		}
		return null;
	}

	public static long Timestampsub(Timestamp timestamp1, Timestamp timestamp2) {

		Date time1 = new Date(timestamp1.getTime());
		Date time2 = new Date(timestamp2.getTime());
		long l = time2.getTime() - time1.getTime();
		long day = l / (24 * 60 * 60 * 1000);

		return day;

	}
}
