package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
     * 获取前一天日期yyyyMMdd
     * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @see calendar.set(Calendar.YEAR, 2013);
     * @see calendar.set(Calendar.MONTH, 0);
     * @see calendar.set(Calendar.DATE, 1);
     * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyy-MM-dd
     */
	/**
	 * 格式化日期
	 * @param type   格式化类型
	 * @param date   要格式化的日期
	 * @return
	 */
	public static String getSimpleDateFormatDays(String type, Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat(type);

		return sdf.format(date);
	}


    public static String getYestoday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取当前的日期yyyyMMdd
     */
    public static String getToday(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    /**
     * 获取明天
     * @return
     */
    public static String getTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    /**
     * 获取最后一天/月
     * @return
     * @throws ParseException 
     */
    public static String lastDayOfMonth(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		int m = Integer.parseInt(d.split("-")[1]);
		 Date date = new Date(sdf.parse(d.split("-")[0]+"-"+(m+1)+"-01").getTime()-24*60*60*1000);
        
        String lastday = sdf.format(date);  
        
		return lastday;
	}

	/**
	 * 获取某个时间月份下的最后一天
	 * @param year   年份
	 * @param month  月份
	 * @return
	 */
	public static String getLastDayOfMonth(int year,int month) {
		if(month < 1 || month > 12) {
			return "参数错误,请检查!";
		}
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	/**
	 * 获取当前周的周一到周日
 	 * @param day  获取的周几
	 * @return
	 */
    public static String getCurrentMondayToSunday(int day) {

    	if(day < 1 || day > 7) {
    		return "参数错误,请选择周一到周日";
		}
    	Calendar c = Calendar.getInstance();
    	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
    	  if (day_of_week == 0){
    		  day_of_week = 7;
    	  }
    	  c.add(Calendar.DATE, -day_of_week + day);
    	  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
    } 
    
    public static void main(String[] args) throws ParseException {
		
    	System.out.println(getSimpleDateFormatDays("yyyyMMddHHmmss",new Date()));
    	System.out.println(getYestoday());
    	System.out.println(getToday());
    	System.out.println(getTomorrow());
    	System.out.println(lastDayOfMonth("2017-12-12"));
    	System.out.println(getLastDayOfMonth(2017,2));
    	System.out.println(getCurrentMondayToSunday(7));

    	/*Double money = 0.00;
    	double m = 0.01;
    	for(int i=1;i<=30;i++){
    		if(i != 1){
    			m = m*2;
    			money += m;
    		}else{
    			money = m;
    		}
    		//System.out.println(i+"  "+m+"   "+money);
    	}
    	System.out.println("总金额："+money);*/
	}
}
