package com.plcs.web.wsxd.smsplatform.smssend.service.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	private StringUtil() {
	}

	public static boolean isInt(String value) {
		try {
			Integer.valueOf(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isLong(String value) {
		try {
			Long.valueOf(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isDouble(String value) {
		try {
			Double.valueOf(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static Map<String, String> paserStrToMap(String respStr) {
		Map<String, String> data = new HashMap<String, String>();
		if (isNotEmpty(respStr)) {
			String[] strs = respStr.split(",");
			for (String str : strs)
				if (!isEmpty(str)) {

					int index = str.indexOf(":");

					data.put(str.substring(0, index), str.substring(index + 1));
				}
		}
		return data;
	}

	public static String strSpile(String sms) {
		String beginIndex = sms.replace("{", "");
		String endIndex = beginIndex.replace("}", "");
		String smsMsg = endIndex.replace("\"", "");
		return smsMsg;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}
}
