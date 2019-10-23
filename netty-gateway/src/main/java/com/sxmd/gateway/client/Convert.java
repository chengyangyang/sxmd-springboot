package com.sxmd.gateway.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Convert {
	private static final Logger LOGGER = LoggerFactory.getLogger(Convert.class);

	/**
	 * The high digits lookup table.
	 */
	private static final byte[] highDigits;

	/**
	 * The low digits lookup table.
	 */
	private static final byte[] lowDigits;

	/**
	 * Initialize lookup tables.
	 */
	static {
		final byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		int i;
		byte[] high = new byte[256];
		byte[] low = new byte[256];

		for (i = 0; i < 256; i++) {
			high[i] = digits[i >>> 4];
			low[i] = digits[i & 0x0F];
		}

		highDigits = high;
		lowDigits = low;
	}

	public static String getHexdump(byte[] bytes) {
		int size = bytes.length;

		StringBuilder out = new StringBuilder(size * 3 + 3);

		int index = 0;
		// fill the first
		int byteValue = bytes[index] & 0xFF;
		out.append((char) highDigits[byteValue]);
		out.append((char) lowDigits[byteValue]);
		size--;
		index++;
		// and the others, too
		for (; size > 0; size--) {
			out.append(' ');
			byteValue = bytes[index] & 0xFF;
			index++;
			out.append((char) highDigits[byteValue]);
			out.append((char) lowDigits[byteValue]);
		}
		return out.toString();
	}

	/**
	 * 
	 * ASC码转换字符串
	 * 
	 * 方法添加日期: 2013年12月30日 创建者:刘源
	 */
	public static String asc2String(byte[] ascSoucre) {
		String stringResult = "";
		for (int i = 0; i < ascSoucre.length; i++) {
			stringResult += (char) ascSoucre[i];
		}
		return stringResult;
	}

	/**
	 * 
	 * Short 转换字节数组低字节在前
	 * 
	 * 方法添加日期: 2014年2月24日 创建者:刘源
	 */
	public static byte[] short2Bytes(short n) {
		byte[] bytes = new byte[2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) ((n >> (i << 3)) & 0xFF);
		}
		return bytes;
	}

	/**
	 * 
	 * Short 转换字节数组高字节在前
	 * 
	 * 方法添加日期: 2014年3月11日 创建者:刘源
	 */
	public static byte[] short2BytesHight(short n) {
		byte b[] = new byte[2];
		b[0] = (byte) (n >> 8);
		b[1] = (byte) (n >> 0);
		return b;
	}

	/**
	 * 
	 * 十六进制字符串地址转换byte数组
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static byte[] hexString2Address(String address) {
		byte[] result = new byte[] { 0, 0, 0, 0, 0 };
		address = address.trim();
		if (address.length() == 10) {
			for (int i = 0; i < 6; i += 2) {
				int high = address.charAt(i) - '0';
				int low = address.charAt(i + 1) - '0';

				result[i / 2] = (byte) ((high << 4) + low);
			}

			byte[] stationNumber = hexToBytes(address.substring(6));

			result[3] = stationNumber[0];
			result[4] = stationNumber[1];
		}

		return result;
	}

	/**
	 * 
	 * byte数组转换十六进制字符串地址(入库用)
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String address2HexString(int offset, byte[] address) {
		return bytesToHex(offset, 5, address);
	}

	/**
	 * 
	 * byte数组转换十进制字符串地址(前台展示用)
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String addrss2HumanString(int offset, byte[] address) {
		return address2AreaCode(offset, address) + String.format("%d", address2StationCode(offset + 3, address));
	}

	/*
	 * 根据数据库中的16进制地址码转换成byte数组
	 * 
	 * @param [in] address 数据库中的地址码，地址码 XXXXXX, 前6位是BCD码，后4位是10进制数，左补零
	 */
	public static byte[] string2Address(String address) {
		byte[] result = new byte[] { 0, 0, 0, 0, 0 };
		address = address.trim();
		if (address.length() == 10) {
			for (int i = 0; i < 6; i += 2) {
				int high = address.charAt(i) - '0';
				int low = address.charAt(i + 1) - '0';

				result[i / 2] = (byte) ((high << 4) + low);
			}

			short stationNumber = Short.parseShort(address.substring(6));

			result[3] = (byte) (stationNumber);
			result[4] = (byte) (stationNumber >> 8);
		}

		return result;
	}

	/**
	 * 
	 * 根据byte数组地址码转换成字符串
	 *
	 * @param  address 数据库中的地址码，地址码 XXXXXX, 前6位是BCD码，后4位是10进制数，左补零 方法添加日期:
	 *        2013-9-5 创建者:yjh
	 */
	public static String address2String(int offset, byte[] address) {
		return address2AreaCode(offset, address) + String.format("%04d", address2StationCode(offset + 3, address));
	}

	public static String address2AreaCode(int offset, byte[] address) {
		StringBuilder areaCode = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			areaCode.append(((address[i + offset] >> 4) & 0x0f));
			areaCode.append((address[i + offset]) & 0x0f);
		}

		return areaCode.toString();
	}

	public static int address2StationCode(int offset, byte[] address) {
		int stationNumber = 0;
		stationNumber = ((address[0 + offset]) & 0xFF) + ((address[1 + offset] & 0xFF) << 8);

		return stationNumber;
	}

	/**
	 * 
	 * 十进制测站地址转换十六进制地址
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String intAddress2Hex(String address) {
		String subAddress = address.substring(6);
		int intAddress = Integer.parseInt(subAddress);
		byte[] bytes = new byte[2];
		bytes[0] = (byte) intAddress;
		bytes[1] = (byte) (intAddress >> 8);
		String codeStr = bytesToHex(bytes);
		return address.substring(0, 6) + codeStr;
	}

	/**
	 * 
	 * 十六进制测站地址转换十进制地址
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String hexAddress2Int(String address) {
		String subAddress = address.substring(6);
		byte[] bytes = hexToBytes(subAddress);
		int intAddress = address2StationCode(0, bytes);
		return address.substring(0, 6) + String.format("%04d", intAddress);
	}

	/**
	 * 
	 * 十六进制测站监测点ID转换十进制监测点ID
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String hexPointID2Int(String pointID) {
		String subAddress = pointID.substring(6, 10);
		byte[] bytes = hexToBytes(subAddress);
		int intAddress = address2StationCode(0, bytes);
		return pointID.substring(0, 6) + String.format("%04d", intAddress) + pointID.substring(10, 13);
	}

	/**
	 * 
	 * 十进制测站监测点ID转换十六进制监测点ID
	 * 
	 * 方法添加日期: 2013-11-5 创建者:刘源
	 */
	public static String intPointID2Hex(String pointID) {
		String subAddress = pointID.substring(6, pointID.length() - 3);
		int intAddress = Integer.parseInt(subAddress);
		byte[] bytes = new byte[2];
		bytes[0] = (byte) intAddress;
		bytes[1] = (byte) (intAddress >> 8);
		String codeStr = bytesToHex(bytes);
		return pointID.substring(0, 6) + codeStr + pointID.substring(pointID.length() - 3);

	}

	/**
	 * 
	 * 获得空占位符
	 * 
	 * 方法添加日期: 2013-10-28 创建者:刘源
	 */
	public static byte[] getNullBytes(int length) {
		byte[] nullBytes = new byte[length];
		for (int i = 0; i < length; i++) {
			nullBytes[i] = (byte) 0xAA;
		}
		return nullBytes;
	}

	/**
	 * 
	 * 将科学计数法转换为字符串
	 * 
	 * 方法添加日期: 2013-10-25 创建者:刘源
	 */

	public static String getNumberString(Object s) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		return nf.format(s);

	}

	/**
	 * 
	 * byte数组转换字符串
	 * 
	 * 方法添加日期: 2013-9-4 创建者:刘源
	 */
	public static String bytesToHex(byte[] bytes) {
		return bytesToHex(0, bytes.length, bytes);
	}

	public static String bytesToHex(int offset, int len, byte[] bytes) {
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[len * 2];
		int v;
		for (int j = 0; j < len; j++) {
			v = bytes[j + offset] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	/**
	 * 
	 * 字节数组转换为整型
	 * 
	 * 方法添加日期: 2013-8-28 创建者:刘源
	 */
	public static int getInt(byte[] bytes) {
		return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16))
				| (0xff000000 & (bytes[3] << 24));
	}


	public static byte[] hexToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 
	 * BCD码转换时间(针对固件数据查询)
	 * 
	 * 方法添加日期: 2013-7-29 创建者:刘源
	 */
	public static Date Bcd2TimestampFoSSD(byte[] buf) {
		int hours = bcd2Int(buf[0]);
		int date = bcd2Int(buf[1]);
		int month = bcd2Int(buf[2]) - 1;
		int year = bcd2Int(buf[3]) - 1900 + 2000;
		Date now = new Date();
		now.setYear(year);
		now.setMonth(month);
		now.setDate(date);
		now.setHours(hours);
		return now;
	}

	/**
	 * 
	 * BCD码转换时间(针对内存数据查询)
	 * 
	 * 方法添加日期: 2013-10-14 创建者:刘源
	 */
	public static Date Bcd2TimestampFoRam(byte[] buf) {
		int minutes = bcd2Int(buf[0]);
		int hours = bcd2Int(buf[1]);
		int date = bcd2Int(buf[2]);
		int month = bcd2Int(buf[3]) - 1;
		Date now = new Date();
		now.setMonth(month);
		now.setDate(date);
		now.setHours(hours);
		now.setMinutes(minutes);
		return now;
	}

	/**
	 * 
	 * BCD码转换时间
	 * 
	 * 方法添加日期: 2013-7-29 创建者:刘源
	 */
	@Deprecated
	public static Date Bcd2Timestamp(int offset, byte[] buf) {

		int second = bcd2Int(buf[offset]);
		int minute = bcd2Int(buf[offset + 1]);
		int hour = bcd2Int(buf[offset + 2]);
		int date = bcd2Int(buf[offset + 3]);
		Date now = new Date();
		Date timestamp = new Date(now.getYear(), now.getMonth(), date, hour, minute, second);
		// System.out.println("=======================");
		// System.out.println(second+"秒");
		// System.out.println(minute+"分");
		// System.out.println(hour+"时");
		// System.out.println(date+"日");
		// System.out.println("=======================");
		return timestamp;
	}

	/**
	 * 图片召测时间转换
	 *
	 * 
	 * 方法添加日期: 2014年4月3日 创建者:刘源
	 */
	public static String bcd2PictureTimestamp(byte[] timeBytes) {

		int year = bcd2Int(timeBytes[0]);
		int month = bcd2Int(timeBytes[1]);
		int date = bcd2Int(timeBytes[2]);
		int hour = bcd2Int(timeBytes[3]);
		int minute = bcd2Int(timeBytes[4]);
		Date timestamp = new Date(year - 1900 + 2000, month - 1, date, hour, minute);
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH.mm");
		return a.format(timestamp);

	}

	/**
	 * 
	 * BCD转换时间. 高字节在前.
	 * 
	 * 方法添加日期（格式：YYYY-MM-DD）2015年8月17日 创建者:刘源
	 *
	 * @param offset
	 * @param buf
	 * @return
	 */
	public static Date newBcd2TimestampHigh(int offset, byte[] buf) {

		int year = bcd2Int(buf[offset]);
		int month = bcd2Int(buf[offset + 1]);
		int date = bcd2Int(buf[offset + 2]);
		int hour = bcd2Int(buf[offset + 3]);
		int minute = bcd2Int(buf[offset + 4]);
		int second = bcd2Int(buf[offset + 5]);
		Date timestamp = new Date(year - 1900 + 2000, month - 1, date, hour, minute, second);
		// System.out.println("=======================");
		// System.out.println(second+"秒");
		// System.out.println(minute+"分");
		// System.out.println(hour+"时");
		// System.out.println(date+"日");
		// System.out.println("=======================");
		return timestamp;
	}

	/**
	 * 
	 * BCD转换时间. 低字节在前.
	 * 
	 * 方法添加日期（格式：YYYY-MM-DD）2015年8月17日 创建者:刘源
	 *
	 * @param offset
	 * @param buf
	 * @return
	 */
	public static Date newBcd2Timestamp(int offset, byte[] buf) {

		int second = bcd2Int(buf[offset]);
		int minute = bcd2Int(buf[offset + 1]);
		int hour = bcd2Int(buf[offset + 2]);
		int date = bcd2Int(buf[offset + 3]);
		int month = bcd2Int(buf[offset + 4]);
		int year = bcd2Int(buf[offset + 5]);
		Date now = new Date();
		Date timestamp = new Date(year - 1900 + 2000, month - 1, date, hour, minute, second);
		// System.out.println("=======================");
		// System.out.println(second+"秒");
		// System.out.println(minute+"分");
		// System.out.println(hour+"时");
		// System.out.println(date+"日");
		// System.out.println("=======================");
		return timestamp;
	}


	/**
	 * 
	 * BCD码转换int型
	 * 
	 * 方法添加日期: 2013-7-29 创建者:刘源
	 */
	public static int bcd2Int(byte bcdValue) {
		return ((bcdValue >> 4) * 10 + (bcdValue & 0x0f));
	}

	public static int bcd2Int(int len, byte[] buf) {
		int dec = 0;
		int k = 1;

		for (int i = 0; i < len; i++) {
			dec = dec + (int) (((buf[i] & 0xf0) >> 4) * 10 + (buf[i] & 0x0f)) * k;
			k = k * 100;
		}
		return dec;
	}

	/**
	 * 
	 * 高字节在前BCD转Int
	 * 
	 * 方法添加日期: 2014年1月8日 创建者:刘源
	 */
	public static int bcd2HighInt(int len, byte[] buf) {
		int dec = 0;
		int k = 1;

		for (int i = len; i > 0; i--) {
			dec = dec + (int) (((buf[i - 1] & 0xf0) >> 4) * 10 + (buf[i - 1] & 0x0f)) * k;
			k = k * 100;
		}
		return dec;

	}

	public static int bcd2Int(int offset, int len, byte[] buf) {
		int dec = 0;
		int k = 1;

		for (int i = 0; i < len; i++) {
			dec = dec + (int) (((buf[i + offset] & 0xf0) >> 4) * 10 + (buf[i + offset] & 0x0f)) * k;
			k = k * 100;
		}
		return dec;
	}

	/**
	 * 
	 * 从byte数组中截取byte数组
	 * 
	 * 方法添加日期: 2013-7-30 创建者:刘源
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}

	/**
	 * double类型精确位数
	 * 
	 * by:源数据 accuracy:精确到多少位小数 subtrahend:减数 a:保留多少位小数
	 * 
	 * 方法添加日期: 2013-7-30 创建者:刘源
	 */
	public static double doubleToAccuracy(byte[] by, double accuracy, int subtrahend, int a) {
		double s = bcd2Int(by.length, by) / accuracy - subtrahend;
		BigDecimal b = new BigDecimal(s);
		double temperature = b.setScale(a, BigDecimal.ROUND_HALF_UP).doubleValue();
		return temperature;
	}

	/**
	 * 
	 * double类型精确位数
	 * 
	 * 方法添加日期（格式：YYYY-MM-DD）2016年3月30日 创建者:刘源
	 *
	 * @param source
	 *            源数据.
	 * @param scale
	 *            精确到多少位小数.
	 * @return
	 */
	public static double doubleToAccuracy(double source, int scale) {
		return new BigDecimal(source).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * 将byte字节转换为十六进制字符
	 * 
	 * 方法添加日期: 2013-8-2 创建者:刘源
	 */
	public static String byteToHex(byte s) {
		return Integer.toHexString(s & 0xFF);
	}

	/**
	 * 
	 * 通过byte数组取得float
	 * 
	 * 方法添加日期: 2013-7-30 创建者:刘源 a:保留多少位小数
	 */
	public static float byteToFloat(byte[] b, int a) {
		BigDecimal bigdecimal = new BigDecimal(getFloat(b, 0));
		float temperature = bigdecimal.setScale(a, BigDecimal.ROUND_HALF_UP).floatValue();
		return temperature;
	}

	public static float getFloat(byte[] b, int index) {
		int l;
		l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

	/**
	 * 
	 * 通过BCD转换手机号码
	 * 
	 * 方法添加日期: 2013-8-29 创建者:刘源
	 */
	public static String bcd2Telephone(int offset, int len, byte[] body) {
		String telephone = bytesToHex(offset, len, body);
		if (telephone == null || telephone.length() != 12) {
			return null;
		}

		return telephone.substring(0, telephone.length() - 1);
	}

	/**
	 * 
	 * 密码PW转换BCD
	 * 
	 * 方法添加日期: 2013-9-3 创建者:刘源
	 */
	public static byte[] Password2Bcd(short cryptoAlgorithm, short secretKey) {
		byte[] result = new byte[2];

		result[0] = (byte) (((cryptoAlgorithm & 0x0f) << 4) + ((secretKey & 0x0f00) >> 8));
		result[1] = (byte) (secretKey & 0xff);

		return result;
	}

	/**
	 * 
	 * 获取系统时间转换BCD
	 * 
	 * 方法添加日期: 2013-9-4 创建者:刘源
	 */
	public static byte[] DateTime2Bcd() {
		byte[] bytes = new byte[4];
		Date date = new Date();
		bytes[0] = Int2Bcd(date.getSeconds());
		bytes[1] = Int2Bcd(date.getMinutes());
		bytes[2] = Int2Bcd(date.getHours());
		bytes[3] = Int2Bcd(date.getDate());
		return bytes;
	}

	/**
	 * 
	 * Int转换BCD码(byte)
	 * 
	 * 方法添加日期: 2013-9-4 创建者:刘源
	 */
	public static byte Int2Bcd(int value) {
		return Int2Bcd(1, value)[0];
	}

	/**
	 * 
	 * Int转换BCD码(byte数组)
	 * 
	 * 方法添加日期: 2013-9-4 创建者:刘源
	 */
	public static byte[] Int2Bcd(int n, int dec) {
		byte high, low;
		byte[] buf = new byte[n];
		for (int i = 0; i < n; i++) {
			low = (byte) (dec % 10);
			dec /= 10;
			high = (byte) (dec % 10);
			dec /= 10;
			buf[i] = (byte) ((high << 4) + (low & 0x0f));
		}
		return buf;
	}

	/**
	 * 
	 * 字符串转换BCD码(byte数组) 高字节在前. 方法添加日期: 2013-9-4 创建者:刘源
	 */
	public static byte[] str2BcdHigh(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * 
	 * Long转换BCD码(byte数组)
	 * 
	 * 方法添加日期: 2013-9-25 创建者:刘源
	 */
	public static byte[] long2Bcd(int n, Long dec) {
		byte high, low;
		byte[] buf = new byte[n];
		for (int i = 0; i < n; i++) {
			low = (byte) (dec % 10);
			dec /= 10;
			high = (byte) (dec % 10);
			dec /= 10;
			buf[i] = (byte) ((high << 4) + (low & 0x0f));
		}
		return buf;
	}

	/**
	 * 
	 * 时间参数转换byte数组(针对查询固态存储数据)
	 * 
	 * 方法添加日期: 2013-10-10 创建者:刘源
	 */
	public static byte[] querySSDDataTime2Bcd(String time) {
		byte[] bytes = new byte[4];
		Date date = null;
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			date = a.parse(time);
			bytes[0] = Int2Bcd(date.getHours());
			bytes[1] = Int2Bcd(date.getDate());
			bytes[2] = Int2Bcd(date.getMonth() + 1);
			bytes[3] = Int2Bcd(date.getYear() + 1900 - 2000);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 
	 * 时间参数转换byte数组(针对查询内存存储数据)
	 * 
	 * 方法添加日期: 2013-10-10 创建者:刘源
	 */
	public static byte[] queryRamDataTime2Bcd(String time) {
		byte[] bytes = new byte[4];
		Date date = null;
		SimpleDateFormat a = new SimpleDateFormat("MM-dd HH:mm");
		try {
			date = a.parse(time);
			bytes[0] = Int2Bcd(date.getMinutes());
			bytes[1] = Int2Bcd(date.getHours());
			bytes[2] = Int2Bcd(date.getDate());
			bytes[3] = Int2Bcd(date.getMonth() + 1);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 
	 * 时间参数转换byte数组(高字节在前,BCD码)
	 * 
	 * 方法添加日期: 2013-9-5 创建者:刘源
	 */
	public static byte[] timeParameter2bytes(String time) {
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		byte[] bytes = new byte[6];
		try {
			date = a.parse(time);
			bytes[0] = Int2Bcd(date.getYear() + 1900 - 2000);
			bytes[1] = Int2Bcd(date.getMonth() + 1);
			bytes[2] = Int2Bcd(date.getDate());
			bytes[3] = Int2Bcd(date.getHours());
			bytes[4] = Int2Bcd(date.getMinutes());
			bytes[5] = Int2Bcd(date.getSeconds());
		} catch (ParseException e) {
			LOGGER.error("parse time and convert to bcd format failed.", e);
		}
		return bytes;
	}

	/**
	 * 
	 * 当前系统时间参数转换byte数组(BCD码) (高字节在前) 方法添加日期: 2013-9-5 创建者:刘源
	 */
	public static byte[] systemTime2HighBcd() {
		Date date = new Date();
		byte[] bytes = new byte[6];
		bytes[0] = Int2Bcd(date.getYear() + 1900 - 2000);
		bytes[1] = Int2Bcd(date.getMonth() + 1);
		bytes[2] = Int2Bcd(date.getDate());
		bytes[3] = Int2Bcd(date.getHours());
		bytes[4] = Int2Bcd(date.getMinutes());
		bytes[5] = Int2Bcd(date.getSeconds());
		return bytes;
	}


    /**
	 * 
	 * 时间参数转换byte数组(BCD码) (高字节在前) 方法添加日期: 2013-9-5 创建者:刘源
	 */
	public static byte[] dateTime2HighBcd(Date time) {
		byte[] bytes = new byte[4];
		bytes[0] = Int2Bcd(time.getYear() - 2000 + 1900);
		bytes[1] = Int2Bcd(time.getMonth() + 1);
		bytes[2] = Int2Bcd(time.getDate());
		bytes[3] = Int2Bcd(time.getHours());
		return bytes;
	}

	/**
	 * 
	 * 时间参数转换字符串(低位在前，高位在后)精确到秒
	 * 
	 * 方法添加日期: 2013-9-5 创建者:刘源
	 */
	public static String timeParameter2String(byte[] time) {
		Date date = new Date(bcd2Int(time[5]) + 2000 - 1900, bcd2Int((byte) (time[4] & 0x1F)) - 1, bcd2Int(time[3]),
				bcd2Int(time[2]), bcd2Int(time[1]), bcd2Int(time[0]));
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return a.format(date);
	}

	/**
	 * 
	 * 时间参数转换字符串(高位在前，低位在后)精确到分钟
	 * 
	 * 方法添加日期: 2013-9-5 创建者:刘源
	 */
	public static String timeHighParameter2String(byte[] time) {
		Date date = new Date(bcd2Int(time[0]) + 2000 - 1900, bcd2Int((byte) (time[1] & 0x1F)) - 1, bcd2Int(time[2]),
				bcd2Int(time[3]), bcd2Int(time[4]));
		SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		return a.format(date);
	}

	public static long bcd2Long(int offset, int n, byte[] buf) {
		long dec = 0;
		long k = 1;

		for (int i = 0; i < n; i++) {
			dec = dec + (long) (((buf[offset + i] & 0xf0) >> 4) * 10 + (buf[offset + i] & 0x0f)) * k;
			k = k * 100;
		}
		return dec;
	}

	/**
	 * 
	 * 转换水位值
	 * 
	 * 方法添加日期: 2013-9-11 创建者:刘源
	 */
	public static double bcd2WaterLevel(int offset, byte[] data) {
		double result = bcd2Int(offset, 3, data);
		result = (data[offset + 3] & 0x0F) * 1000 + result / 1000.0;
		if ((data[offset + 3] & 0xF0) == 0xF0) {
			result = 0 - result;
		}
		return result;
	}

	/**
	 * 
	 * 转换流速值
	 * 
	 * 方法添加日期: 2013-9-11 创建者:刘源
	 */
	public static double bcd2FlowRate(int offset, byte[] data) {

		double result = bcd2Int(offset, 2, data);
		result = (data[offset + 2] & 0x0F) * 10 + result / 1000.0;
		if ((data[offset + 2] & 0xF0) == 0xF0) {
			result = 0 - result;
		}
		return result;

	}

	/**
	 * 
	 * BCD转换流量值
	 * 
	 * 方法添加日期: 2013-9-12 创建者:刘源
	 */
	public static double bcd2Discharge(int offset, byte[] data) {
		double result = bcd2Int(offset, 4, data);
		result = (data[offset + 4] & 0x0F) * 100000 + result / 1000.0;
		if ((data[offset + 4] & 0xF0) == 0xF0) {
			result = 0 - result;
		}
		return result;

	}

	/**
	 * 
	 * BCD转换风速
	 * 
	 * 方法添加日期: 2013-9-12 创建者:刘源
	 */
	public static double bcd2WindSpeed(int offset, byte[] data) {
		double result = bcd2Int(offset, 2, data);
		result = (data[offset + 2] & 0x0F) * 100 + result / 100.0;
		return result;
	}

	/**
	 * 
	 * BCD转换风向
	 * 
	 * 方法添加日期: 2013-9-12 创建者:刘源
	 */
	public static int bcd2WindDirection(int offset, byte[] data) {
		int result = ((data[offset + 2] & 0xF0) >> 4);
		return result;
	}

	/**
	 * 
	 * Double转换BCD
	 * 
	 * 方法添加日期: 2013-9-29 创建者:刘源 accuracy 原数据需乘以10的多少倍
	 */
	public static byte[] double2Bcd(int len, int accuracy, Double dec) {
		Long shreshold = new Double(dec * Math.pow(10, accuracy)).longValue();
		Boolean isNegtive = (shreshold < 0);
		byte[] bytes = long2Bcd(len, Math.abs(shreshold));
		if (isNegtive) {
			bytes[len - 1] |= 0xF0;
		}
		return bytes;
	}

	/**
	 * 
	 * 流量值转换BCD
	 * 
	 * 方法添加日期: 2013-9-12 创建者:刘源
	 */
	public static byte[] discharge2Bcd(Double discharge) {
		Long shreshold = new Double(discharge * 1000).longValue();
		Boolean isNegtive = (shreshold < 0);
		byte[] waterFlow = long2Bcd(5, Math.abs(shreshold));
		if (isNegtive) {
			waterFlow[4] |= 0xF0;
		}
		return waterFlow;
	}

	/**
	 * 
	 * 风速转换BCD
	 * 
	 * 方法添加日期: 2013年12月3日 创建者:刘源
	 */
	public static byte[] windSpeed2Bcd(Double windSpeed) {
		Long shreshold = new Double(windSpeed * 100).longValue();
		byte[] waterFlow = long2Bcd(3, Math.abs(shreshold));
		waterFlow[2] &= 0x0F;
		return waterFlow;

	}

	/**
	 * 
	 * 转换水量值
	 * 
	 * 方法添加日期: 2013-9-12 创建者:刘源
	 */
	public static Double bcd2WaterVolume(int offset, byte[] data) {

		double result = bcd2Long(offset, 5, data);

		return result;
	}

	/**
	 * 
	 * 将4个字节的字节数组转换为Int值 由高位到低位
	 * 
	 * @param bytes
	 * @return result 整型
	 * 
	 *         方法添加日期: 2014年3月3日 创建者:刘源
	 */
	public static int byte2Int(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result += ((int) (bytes[i] & 0xFF)) << (8 * (bytes.length - i - 1));
		}
		return result;
	}

	/**
	 * 
	 * 转换时间(60进制) 单位小时
	 * 
	 * 方法添加日期: 2013-9-18 创建者:刘源
	 */
	@Deprecated
	public static double getDoubleTime(long cux) {
		int timestamp = (int) (cux / (1000 * 60));
		int hour = timestamp / 60;
		int minute = timestamp % 60;
		String time = String.format("%d.%02d", hour, minute);
		return Double.parseDouble(time);

	}

	public static double getDoubleTime(int cux) {
		int hour = cux / 60;
		int minute = cux % 60;
		String time = String.format("%d.%02d", hour, minute);
		return Double.parseDouble(time);
	}

	public static byte[] time2Bcd(Date date) throws Exception {
		if (date == null) {
			throw new Exception("date is null");
		}

		byte[] bytes = new byte[6];
		try {
			bytes[0] = Int2Bcd(date.getSeconds());
			bytes[1] = Int2Bcd(date.getMinutes());
			bytes[2] = Int2Bcd(date.getHours());
			bytes[3] = Int2Bcd(date.getDate());
			bytes[4] = (byte) ((((int) ((date.getDay() == 0) ? 7 : date.getDay())) << 5) + Int2Bcd(date.getMonth() + 1));
			bytes[5] = Int2Bcd(date.getYear() + 1900 - 2000);
		} catch (Exception e) {
			LOGGER.error("parse time and convert to bcd format failed.", e);
		}
		return bytes;
	}

	/**
	 * 
	 * byte数组转换Short 低字节在前
	 * 
	 * 方法添加日期: 2013年11月20日 创建者:刘源
	 */
	public static short byteToShort(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最低位
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * 
	 * byte数组转换Short 高字节在前
	 * 
	 * 方法添加日期: 2013年11月20日 创建者:刘源
	 */
	public static short byteToShortHight(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最高位
		short s1 = (short) (b[1] & 0xff);
		s0 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < tmp.length / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 根据自己传入数据的格式而定yyyy-MM-dd hh:mm:ss
	 * 
	 * @param day
	 * @param x
	 * @return 方法添加日期: 2014年03月03日 创建者:zzx
	 */
	public static String addDate(String day, int x) {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");// 24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	/**
	 * 
	 * Int转byte[]
	 * 
	 * 方法添加日期（格式：YYYY-MM-DD）2015年4月15日 创建者:刘源
	 *
	 * @param i
	 * @return
	 */
	public static byte[] int2Bytes(int i) {
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);//0004   0400
		return result;
	}
	public  static  byte[] int2bytesToLow2(int i){
		byte[] result=new byte[2];
		//由低位到高位
		result[1] = (byte) ((i >> 8) & 0xFF);
		result[0] = (byte) (i & 0xFF);
		return result ;

	}

	public  static  byte[] int2bytesToLow4(int i){//1025
		byte[] result=new byte[4];//0401   0104
		//由低位到高位
		result[3] = (byte) ((i >> 24) & 0xFF);
		result[2] = (byte) ((i >> 16) & 0xFF);
		result[1] = (byte) ((i >> 8) & 0xFF);
		result[0] = (byte) (i & 0xFF);
		return result ;

	}

	/**
	 * int转化成byte[]类型 由低位到高位
	 * @param i
	 * @param length
	 * @return
	 */

	public  static  byte[] int2Bytes(int i ,int length){
		byte[] result = new byte[length];
		for(int j=0;j<length;j++){
			result[j]=(byte)((i>>8*j) & 0xFF);
		}
        return result;
	}

	public static int hexByteToInt(byte[] bytes) {
		String s = Convert.bytesToHex(bytes);
		return Integer.parseInt(s, 16);

	}
	  /* byte数组转换字符串(以空格分隔每个字节)
     * <p>
     * 方法添加日期: 2013-9-4 创建者:刘源
     */
	public static String bytesToHexBlankSpaceStr(byte[] bytes) {
		return bytesToHexBlankSpaceStr(0, bytes.length, bytes);
	}
	public static String bytesToHexBlankSpaceStr(int offset, int len, byte[] bytes) {
		final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		char[] hexChars = new char[len * 3];
		int v;
		for (int j = 0; j < len; j++) {
			v = bytes[j + offset] & 0xFF;
			hexChars[j * 3] = hexArray[v >>> 4];
			hexChars[j * 3 + 1] = hexArray[v & 0x0F];
			hexChars[j * 3 + 2] = ' ';
		}
		return new String(hexChars);
	}

    public static byte[] time2bcd(Date date) {
        byte[] bytes = new byte[6];
        bytes[0] = Convert.Int2Bcd(date.getYear() + 1900);
        bytes[1] = Convert.Int2Bcd(date.getMonth() + 1);
        bytes[2] = Convert.Int2Bcd(date.getDate());
        bytes[3] = Convert.Int2Bcd(date.getHours());
        bytes[4] = Convert.Int2Bcd(date.getMinutes());
        bytes[5] = Convert.Int2Bcd(date.getSeconds());
        return bytes;

    }


}
