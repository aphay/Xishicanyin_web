package beehive_web.base;

import java.util.Calendar;

//生成取餐号的类，每天从1开始。
public class TakeNo {
    private static int takeNo = 0;
    private static String timeStamp = "";
    
    private static void init() {
        takeNo = 0;
        Calendar cal=Calendar.getInstance();  
        int y =cal.get(Calendar.YEAR);
        int m =cal.get(Calendar.MONTH);
        int d =cal.get(Calendar.DATE);
        timeStamp = ""+y+m+d;
    }
    
    public static int generate() {
        Calendar cal=Calendar.getInstance();  
        int y =cal.get(Calendar.YEAR);    
        int m =cal.get(Calendar.MONTH);    
        int d =cal.get(Calendar.DATE);  
        String today = ""+y+m+d;
        if(!timeStamp.equals(today)) {
            init();
        }
        takeNo++;
        return takeNo;
    }
}
