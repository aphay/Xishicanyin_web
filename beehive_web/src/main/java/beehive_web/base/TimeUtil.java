package beehive_web.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public static String now() {
        Date now = new Date();
        return sdf1.format(now);
    }
    public static long nowLong() {
        long now = new Date().getTime();
        return now;
    }
    public static String today() {
        Date now = new Date();
        return sdf2.format(now) + " 00:00:00";
    }
    public static String date2String(Date date) {
        return sdf1.format(date);
    }
    public static Date string2Date(String time) {
        try {
            return sdf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
    public static String tomorrow(String today) {
    	Date t = string2Date(today+" 00:00:00");
    	t.setTime(t.getTime()+24*3600*1000);
    	return sdf2.format(t);
    }
}
