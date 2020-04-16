package com.rms.recreativos.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringUtil {

	/**
	 * Devuelve una lista de String a partir de un String separador por un limitador (Ej: lista de palabras separadas por comas) 
	 */
	public static List<String> delimitedStringToArray(String str, String delimiter){
		int limit = occurs(str, delimiter);
       List<String> listaStr = new ArrayList<String>(limit+1);
       int start = 0;
       int nextEnd;
       String palabra;
       for (int i = 0; i < limit; i++) {
           nextEnd = str.indexOf(delimiter, start);
           palabra = str.substring(start, nextEnd);
           listaStr.add(palabra);
           start = nextEnd + 1;
       }
       palabra = str.substring(start);
       listaStr.add(palabra);
		return listaStr;
	}
	
	/**
	 * Devuelve un String separador por el separador introducido y los valores de la lista de Strings 
	 */
	public static String arrayToDelimitedString(List<String> lista, String delimiter){
		String retorno = null;
		Iterator<String> it = lista.iterator();
		while (it.hasNext()){
			if (isNullOrEmptyString(retorno)){
				retorno = it.next();
			} else {
				retorno += delimiter + it.next();
			}
		}
		return retorno;
	}
	
	/**
	 * Devuelve cuantas veces aparece un String en otro 
	 */
	public static int occurs(String str, String ch){
       int count = 0;
       int offset = str.indexOf(ch, 0);
       while (offset != -1) {
           count++;
           offset = str.indexOf(ch, offset + 1);
       }
       return(count);
   }
	
	/**
	 * Devuelve si un String es null o vacio
	 */
	public static boolean isNullOrEmptyString(String str){
		return (str == null) || "".equals(str);
	}
	
	/**
	 * Devuelve si un String es null o vacio o espacios
	 */
	public static boolean isNullOrEmptyOrBlankString(String str){
		if (str == null){
			return true;
		}
		str = str.replaceAll(" ", "");
		return "".equals(str);
	}
	
	public static String replaceAllCustom(String textoOriginal, String cadenaSustituir, String nuevaCadena) {
		if (textoOriginal == null || textoOriginal.length() == 0) {
			return textoOriginal;
		}
		if (cadenaSustituir == null || cadenaSustituir.length() == 0) {
			return textoOriginal;
		}
		if (nuevaCadena == null) {
			nuevaCadena = "";
		}
		int tamQuitar = cadenaSustituir.length();
		String retorno = textoOriginal;
		int rmplEn = retorno.indexOf(cadenaSustituir);
		for (; retorno.indexOf(cadenaSustituir) != -1; rmplEn = retorno.indexOf(cadenaSustituir)) {
			retorno = retorno.substring(0, rmplEn) + nuevaCadena + retorno.substring(rmplEn + tamQuitar);
		}
		return retorno;
	}
	
	public static boolean HasNumber(String txt){
		
		String numeros="0123456789";
		
		for(int i=0; i<txt.length(); i++){
			if (numeros.indexOf(txt.charAt(i),0)!=-1){
				return true;
			}
		}
		return false;
	}

	public static boolean HasUpperCase(String txt){
		String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0; i<txt.length(); i++){
			if (mayu.indexOf(txt.charAt(i),0)!=-1){
				return true;
			}
		}
		return false;		
	}
}