package com.plcs.web.wsxd.smsplatform.smssend.service.util;

public class MobileTools {
	public MobileTools() {
	}

	public static String leftFill(String src, int count, String in) {
		if (count > 0) {
			if (src.getBytes().length < count) {
				String fills = "";
				if ((in == null) || (in.equals("")))
					in = "0";
				for (int i = 0; i < count; i++) {
					fills = fills + in;
				}
				return fills.substring(0, fills.getBytes().length - src.getBytes().length) + src;
			}

			return src;
		}

		return src;
	}

	public static String rightFill(String src, int count, String in) {
		if (count > 0) {
			if (src.getBytes().length < count) {
				String fills = "";
				if ((in == null) || (in.equals("")))
					in = "0";
				for (int i = 0; i < count; i++) {
					fills = fills + in;
				}

				return src + fills.substring(0, fills.getBytes().length - src.getBytes().length);
			}

			System.out.println("src" + src.length());
			return src;
		}

		System.out.println("src" + src.length());
		return src;
	}

	public static byte[] rightFill_byte(byte[] src, int count, String in) {
		if ((count > 0) && (src.length >= 0) && (count > src.length)) {
			byte[] result = new byte[count];
			int srclength = src.length;

			for (int i = 0; i < srclength - 1; i++) {
				result[i] = src[i];
			}
			for (int i = srclength; i < count - 1; i++) {
				result[i] = new Byte(" ").byteValue();
			}
			return result;
		}
		return null;
	}

	public static void rightFill_byte(byte[] resultBytes, byte[] src, int fillcount, int beginlength) {
		if ((fillcount > 0) && (fillcount >= src.length)) {
			int srclength = src.length;

			for (int i = 0; i < srclength; i++) {
				resultBytes[beginlength] = src[i];
				beginlength++;
			}

			for (int i = 1; i < fillcount - srclength; i++) {
				resultBytes[beginlength] = 32;
				beginlength++;
			}
		}
	}

	public static String splitIt(byte[] orgByte, int beg, int cutByte) {
		int cutLength = 0;
		int byteNum = cutByte;

		if (cutByte > 1) {
			for (int i = beg; i < beg + byteNum; i++) {
				if (orgByte[i] < 0) {
					cutLength++;
				}
			}
			if (cutLength % 2 == 0) {
				cutLength /= 2;
			} else {
				cutLength = 0;
			}
		}

		int result = byteNum;
		if (cutLength != 0) {
			result = cutLength + --byteNum;
		}

		if (result > cutByte) {
			result = cutByte;
		}
		if (cutByte == 1) {
			if (orgByte[(beg + byteNum)] < 0) {
				result += 2;
			} else {
				result++;
			}
		}
		return new String(orgByte, beg, result);
	}

	public static String getLostFirstZero(String str) {
		String temp = str;
		if (str.startsWith("0")) {
			temp = getLostFirstZero(temp.substring(1));
		}
		return temp;
	}

	public static String getLostLastZero(String str) {
		String temp = str;
		if (str.endsWith("0")) {
			temp = getLostLastZero(str.substring(0, temp.length() - 1));
		}
		return temp;
	}

	public static void main(String[] args) {
		String oRate = "1.0000000";
		String ss = oRate.toString();

		if (ss.indexOf(".") > -1) {
			if (ss.charAt(0) == '0') {
				ss = ss.replace(".", "");

				if (ss.equals("00000000")) {
					ss = "0";
				} else if (ss.startsWith("00")) {
					if (ss.charAt(3) != '0') {
						ss = getLostFirstZero(ss);

						ss = ss.substring(0, 1).concat(".").concat(ss.substring(1));
						ss = getLostLastZero(ss);
					} else {
						ss = ss.substring(2, 3).concat(".").concat(ss.substring(3));
					}
				} else {
					ss = getLostFirstZero(ss);

					if (ss.substring(2).equals("00000")) {
						ss = ss.substring(0, 2);
					} else {
						ss = ss.substring(0, 2).concat(".").concat(ss.substring(2));
					}
				}
			} else {
				ss = ss.replace(".", "");

				if (ss.substring(1).equals("0000000")) {
					ss = ss.substring(0, 3);
				} else if (ss.substring(2).equals("000000")) {
					ss = ss.substring(0, 4);
				} else if (ss.substring(3).equals("00000")) {
					ss = ss.substring(0, 5);
				} else if (ss.substring(4).equals("0000")) {
					ss = ss.substring(0, 4);
					ss = ss.substring(0, 3).concat(".").concat(ss.substring(3));
				} else if (ss.substring(5).equals("000")) {
					ss = ss.substring(0, 5);
					ss = ss.substring(0, 3).concat(".").concat(ss.substring(3));
				} else if (ss.substring(6).equals("00")) {
					ss = ss.substring(0, 6);
					ss = ss.substring(0, 3).concat(".").concat(ss.substring(3));
				} else if (ss.substring(7).equals("0")) {
					ss = ss.substring(0, 7);
					ss = ss.substring(0, 3).concat(".").concat(ss.substring(3));
				} else {
					ss = ss.substring(0, 3).concat(".").concat(ss.substring(3));
				}
			}
		}
	}
}
