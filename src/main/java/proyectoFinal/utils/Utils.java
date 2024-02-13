package proyectoFinal.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static String dateToString (Date d) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    return df.format(d);
	}
	
	public static String dateEspToEng (String fecha) {
		 // AAAA-MM-DD 
		String y= fecha.substring(6, 10);
		String m= fecha.substring(3, 5);
		String d= fecha.substring(0, 2);
	    return (y+"-"+m+"-"+d);
	}
	
	public static Date stringToDate (String fecha) {
		
		Calendar calendario = Calendar.getInstance();
		int y = Integer.parseInt(fecha.substring(6, 10));
		int m = Integer.parseInt(fecha.substring(3, 5)) - 1;
		int d = Integer.parseInt(fecha.substring(0, 2));

        calendario.set(Calendar.YEAR, y);
        calendario.set(Calendar.MONTH, m);
        calendario.set(Calendar.DAY_OF_MONTH, d);

        return calendario.getTime();
	}
	
	
public static Date string2ToDate (String fecha) {
	
	
	// 1985-01-03 año-mes-dia. asi se recibe
	
		Calendar calendario = Calendar.getInstance();
		int y,m,d;
		
		if (fecha != null & fecha !="") {
			y = Integer.parseInt(fecha.substring(0, 4));
			m = Integer.parseInt(fecha.substring(5, 7)) - 1;
			d = Integer.parseInt(fecha.substring(8, 10));
		}
		else {
			y = 2000;
			m = 0;
			d = 1;
		}
		
        calendario.set(Calendar.YEAR, y);
        calendario.set(Calendar.MONTH, m);
        calendario.set(Calendar.DAY_OF_MONTH, d);

        return calendario.getTime();

	}
	
	
}
