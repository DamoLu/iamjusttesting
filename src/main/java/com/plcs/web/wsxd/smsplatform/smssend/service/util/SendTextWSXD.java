package com.plcs.web.wsxd.smsplatform.smssend.service.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plcs.web.wsxd.smsplatform.smssend.entity.SmsSendResultBO;

public class SendTextWSXD {
	private String host;
	private int port;
	private Socket socket;

	public SendTextWSXD(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			socket = new Socket(this.host, this.port);
		} catch (Exception e) {
			System.out.println("Initialize socket failed...");
			System.exit(-1);
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] getSmsBytes(String mobile, String msg_content) throws Exception {
		byte[] resultBytes = new byte['ক'];

		String top = "2449";
		String username = "wsxd";
		String password = "wsxd";
		String channel = "1";
		String retain = " ";

		MobileTools.rightFill_byte(resultBytes, top.getBytes("UTF-8"), 4, 0);
		MobileTools.rightFill_byte(resultBytes, mobile.getBytes("UTF-8"), 12, 4);
		MobileTools.rightFill_byte(resultBytes, username.getBytes("UTF-8"), 100, 26);
		MobileTools.rightFill_byte(resultBytes, password.getBytes("UTF-8"), 50, 126);
		MobileTools.rightFill_byte(resultBytes, channel.getBytes("UTF-8"), 2, 186);
		MobileTools.rightFill_byte(resultBytes, msg_content.getBytes("UTF-8"), 2000, 253);
		MobileTools.rightFill_byte(resultBytes, retain.getBytes("UTF-8"), 200, 2253);

		return resultBytes;
	}

	public String put(String mobile, String content) {
		StringBuffer sb = new StringBuffer();
		try {
			DataOutputStream byteOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream is = new DataInputStream(socket.getInputStream());
			byte[] sendBytes = getSmsBytes(mobile, content);
			byteOut.write(sendBytes);
			byteOut.flush();

			byte[] bs = new byte['Ϩ'];
			int it = 0;
			if ((it = is.read(bs)) != -1) {
				sb.append(new String(bs, 0, it, "utf-8"));
			}

			System.out.println("SendTextWSXD:手机号:" + mobile + "响应结果：" + sb.toString());

			return sb.toString();
		} catch (SocketTimeoutException t) {
			System.out.println("等待超时!" + t.getMessage());
			t.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public SmsSendResultBO put(String mobile, String content, int i) {
		StringBuffer sb = new StringBuffer();
		SmsSendResultBO result = new SmsSendResultBO();
		try {
			DataOutputStream byteOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream is = new DataInputStream(socket.getInputStream());
			byte[] sendBytes = getSmsBytes(mobile, content);
			byteOut.write(sendBytes);
			byteOut.flush();

			byte[] bs = new byte['Ϩ'];
			int it = 0;
			if ((it = is.read(bs)) != -1) {
				sb.append(new String(bs, 0, it, "utf-8"));
			}

			if(sb.toString().contains("1")) {
				result.setFlag(true);
			}

		} catch (SocketTimeoutException t) {
			System.out.println("等待超时!" + t.getMessage());
			t.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String toString() {
		return "SendTextWSXD []";
	}

	public byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[(i - begin)] = src[i];
		return bs;
	}

	public static String getLeftUsefulStr(String str, int length) {
		int strLen = str.getBytes().length;
		int last = 0;
		if (strLen < length) {
			last = length - strLen;
		}
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < last; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
				|| (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)) {

			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = 0.0F;
		float count = 0.0F;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count += 1.0F;
				}
				chLength += 1.0F;
			}
		}
		float result = count / chLength;
		if (result > 0.4D) {
			return true;
		}
		return false;
	}

	public static String toChinese(String msg) {
		if (isMessyCode(msg)) {
			try {
				return new String(msg.getBytes("ISO8859-1"), "UTF-8");
			} catch (Exception localException) {
			}
		}
		return msg;
	}

	public static String[] lisUtils() {
		String[] arr = new String[0];
		return arr;
	}
}
