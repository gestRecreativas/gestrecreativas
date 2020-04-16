package com.rms.recreativos.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateUtil {
	
	private static final Logger logger = Logger.getLogger(DateUtil.class);
	
	/**
	 * Devuelve un objeto Date con la fecha actual 
	 */
	public static Date getNow(){
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * Devuelve una fecha formateada con el formato dado
	 */
	public static String getStringFromDate(Date date, String formato){
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String fecha = sdf.format(date);
		
		return fecha;
	}
	
	public static String getStringFromDate(Date date, String formato, Locale locale){
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formato, locale);
		String fecha = sdf.format(date);
		return fecha;
	}
	/**
	 *  Devuelve negativo si es del pasado, 0 si es hoy  y positivo si es del futuro
	 * @param date
	 * @return
	 */
	public static int esHoy(Date dte){
		if(dte==null) return -1;
		Date dAct=new Date();		
		Calendar date = Calendar.getInstance();
		date.setTime(dte);		
		Calendar dActual = Calendar.getInstance();
		dActual.setTime(dAct);
		if(date.get(Calendar.YEAR) < dActual.get(Calendar.YEAR)) return -1;
		if(date.get(Calendar.YEAR) > dActual.get(Calendar.YEAR)) return 1;		
		return date.get(Calendar.DAY_OF_YEAR) - dActual.get(Calendar.DAY_OF_YEAR);		 
	}
	
	/**
	 * Devuelve una fecha con el String introducido
	 */
	public static Date getDateFromString(String date, String formato){
		Date fecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		sdf.setLenient(false);
		try {
			fecha = sdf.parse(date);
		} catch(Exception e){
			logger.error("[ERROR] >>> La fecha introducida no es valida: " + date);
		}
		return fecha;
	}
}