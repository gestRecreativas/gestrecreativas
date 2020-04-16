package com.rms.recreativos.util;

import java.util.List;

public class ListUtil {

	public static boolean nullOrEmptyList(List<?> list){
		return (list == null) || (list.isEmpty());
	}
}