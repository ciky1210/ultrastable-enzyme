package com.hnlg.enzyme.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具类
 */
public class StringUtil {

	public static final double MONEY_MIN = 0.01D;
	public static final double MONET_MAX = 1000000000.00D;

	/**
	 * 未知
	 */
	public static final String UNKNOWN = "unknown";
	public static final String STR_NULL = "null";
	public static final String STR_UNDEFINED = "undefined";

	private static SecureRandom secureRand = null;
	private static Random rand = null;

	private static String localhost = "";

	static {
		secureRand = new SecureRandom();
		rand = new Random(secureRand.nextLong());
	}

	public static boolean isEmpty(String str) {

		return ((str == null) || (str.trim().length() == 0) || STR_NULL.equals(str.trim()));
	}

	public static boolean isAllEmpty(String... strs) {
		for (String str : strs) {
			if (isNotEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否为手机号
	 *
	 * @return
	 */
	public static boolean isPhone(String phone) {
		phone = StringUtil.nullToStrTrim(phone);
		boolean falg = isNotEmpty(phone);
		if (falg && phone.length() == 11) {
			String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
//            String regex = "^1[\\\\d]{10}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			return m.matches();
		}
		return false;
	}


	/**
	 * 验证邮箱地址是否正确
	 */
	public static boolean isEmail(String email) {

		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证是否为微信号
	 *
	 * @param weChat
	 * @return
	 */
	public static boolean isWeChat(String weChat) {

		boolean flag = false;
		if (!StringUtil.isEmpty(weChat)) {
			if (!StringUtil.isEmpty(weChat)) {
				if (weChat.contains("@")) {  //验证邮箱号
					flag = isEmail(weChat);
				} else {
					String reg1 = "[1-9]\\d{5,19}";  //qq号 6 - 20
					String reg2 = "[a-zA-Z][-_a-zA-Z0-9]{5,19}"; //微信号带字母的 6-20
					flag = weChat.matches(reg1) || isPhone(weChat) || weChat.matches(reg2);
				}
			}

		}
		return flag;

	}

	/**
	 * 判断字符串是否为半角字符集
	 *
	 * @param str
	 * @return
	 */
	public static boolean isHalfAngle(String str) {

		str = nullToStrTrim(str);
		return str.length() == getWordLength(str);
	}

	public static boolean isInvalid(String str) {
		return (isEmpty(str) || UNKNOWN.equalsIgnoreCase(str.trim()) || STR_NULL.equalsIgnoreCase(str.trim())
				|| STR_UNDEFINED.equalsIgnoreCase(str.trim()));
	}

	public static boolean isValid(String str) {
		return !isInvalid(str);
	}

	public static boolean haveEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {

		return ((str != null) && (str.trim().length() > 0) && !str.trim().equals("null"));
	}

	public static boolean haveNotEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	public static boolean haveEmpty(Object... objs) {
		for (Object obj : objs) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if (isEmpty(str)) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return false;
	}

	public static String nullToStr(String str) {

		return str == null ? "" : str;
	}

	public static String nullToStrTrim(String str) {

		return str == null ? "" : str.trim();
	}

	public static int nullToIntZero(String str) {

		str = isEmpty(str) ? "0" : str;
		return Integer.valueOf(str.trim(), 10);
	}

	public static double nullToDoubleZero(String str) {

		str = isEmpty(str) ? "0.00" : str;
		return Double.valueOf(str.trim());
	}

	public static long nullToLongZero(String str) {

		str = isEmpty(str) ? "0" : str;
		return Long.valueOf(str.trim(), 10);
	}

	public static boolean nullToBoolean(String str) {

		return isEmpty(str) ? false : Boolean.valueOf(str.trim());
	}

	public static String nullToUnKnown(String str) {

		return isEmpty(str) ? UNKNOWN : str.trim();
	}

	public static String encodeHTML(String str) {
		if (isEmpty(str)) {
			return "";
		}
		char[] content = new char[str.length()];
		str.getChars(0, str.length(), content, 0);
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#039;");
					break;
				default:
					sb.append(content[i]);
					break;
			}
		}

		return sb.toString();
	}

	/**
	 * 获取字符串的绝对长度，一个中文字符长度为2
	 *
	 * @param str
	 * @return
	 */
	public static int getWordLength(String str) {

		return nullToStr(str).replaceAll("[^\\x00-\\xff]", "**").length();
	}

	public static String getUUID() {

		return UUID.randomUUID().toString();
	}

	/**
	 * 根据参数给定范围获取整数随机数
	 *
	 * @param accuracy
	 * @return
	 */
	public static int getRandom(int accuracy) {

		return (int) (Math.random() * accuracy);
	}

	public static String getRawRandom() {

		String str = MD5Util
				.getMD5(getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong() + localhost);
		return str.toLowerCase();
	}

	public static String getRawRandom(String string) {

		String str = MD5Util.getMD5(
				getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong() + localhost + string);
		return str.toLowerCase();
	}

	public static String getRandom() {

		String str = MD5Util.getMD5(getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong());
		return str.toLowerCase();
	}

	public static String getRandom(String string) {

		String str = MD5Util
				.getMD5(getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong() + string);
		return str.toLowerCase();
	}

	/**
	 * 去除字符串首位和末尾的指定字符
	 *
	 * @param str
	 * @param charStr
	 * @return
	 */
	public static String subStartAndEndChar(String str, String charStr) {

		if (isEmpty(str) || isEmpty(charStr)) {
			return str;
		}

		while (str.startsWith(charStr)) {
			str = str.substring(1);
		}
		while (str.endsWith(charStr)) {
			str = str.substring(0, str.length() - 1);
		}

		return nullToStrTrim(str);
	}

	public static String emptyToNull(String str) {

		if (isEmpty(str)) {
			return null;
		}

		return str;
	}

	/**
	 * 替换掉字符串内的HTML元素
	 *
	 * @param str
	 * @return
	 */
	public static String replaceAllHTML(String str, String rep) {

		if (isEmpty(str)) {
			return "";
		}
		str = replaceHTMLStyleScript(str);

		str = str.replaceAll("&nbsp;", " ");

		return str.replaceAll("<[^>]*>", nullToStrTrim(rep));
	}

	/**
	 * 替换掉字符串内的HTML元素
	 *
	 * @param str
	 * @return
	 */
	public static String replaceAllHTML(String str) {

		return replaceAllHTML(str, "");
	}

	/**
	 * 删除字符串内的style元素和script元素
	 *
	 * @return
	 */
	public static String replaceHTMLStyleScript(String str) {

		if (isEmpty(str)) {
			return "";
		}

		StringBuffer sb = new StringBuffer(str);

		int start = sb.indexOf("<style");
		int end = sb.indexOf("</style>");

		while (start != -1 && end != -1) {
			sb.delete(start, end + 8);
			start = sb.indexOf("<style");
			end = sb.indexOf("</style>");
		}

		start = sb.indexOf("<script");
		end = sb.indexOf("</script>");

		while (start != -1 && end != -1) {
			sb.delete(start, end + 9);
			start = sb.indexOf("<script");
			end = sb.indexOf("</script>");
		}

		str = sb.toString();

		str = str.replace("<style>", "");
		str = str.replace("</style>", "");
		str = str.replace("<script>", "");
		str = str.replace("</script>", "");

		return str;
	}

	/**
	 * 截取字符串
	 *
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subStr(String str, int length) {

		if (isEmpty(str) || str.length() < length) {
			return str;
		}

		if (length <= 0) {
			return "";
		}

		return str.substring(0, length);
	}

	/**
	 * 获取富文本里的图片地址
	 *
	 * @param str
	 * @return
	 */
	public static List<String> getImgLists(String str) {
		List<String> urlList = new LinkedList<String>();
		if (isEmpty(str)) {
			return null;
		}
		String img = "";
		while (true) {
			// 1.如果是截图
			int i, b;
			 i = str.indexOf("src=\"http://qieinoa");
			 b = str.indexOf("png");
			if (i > 0 && b > 0) {
				img = str.substring(i + 5, b + 3);
				urlList.add(img);
				str = str.replace("src=\"" + img + "\"", "");
			}
			// 2.如果是黏贴图
			i = str.indexOf("src=\"http");
			if (i > 0) {
				str = str.substring(i + 5, str.length());
				b = str.indexOf(">");
				if (b > 0) {
					img = str.substring(0, b - 1);
					urlList.add(img);
					str = str.replace(img + "\"", "");
				} else {
					break;
				}
			} else {
				break;
			}
		}

		return urlList;
	}

	/**
	 * 去掉富文本里的图片地址
	 *
	 * @param str
	 * @return
	 */
	public static String removeImgLists(String str) {
		if (isEmpty(str)) {
			return null;
		}
		while (true) {
			int start = str.indexOf("<img");
			int end = str.indexOf(">", start);
			if (start > 0 && end > 0) {
				String img = str.substring(start, end + 1);
				str = str.replace(img, "");
			} else {
				break;
			}
		}
		return str;
	}



	public static String encode(String str, String enc) {

		String strEncode = "";

		try {
			if (str != null)
				strEncode = URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
		}

		return strEncode;
	}



	public static int getRealLength(String str, String charsetName) {

		str = nullToStrTrim(str);

		if (isEmpty(str)) {
			return 0;
		}

		try {
			return str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}



	public static String decode(String str, String enc) {

		String strDecode = "";

		try {
			if (str != null)
				strDecode = URLDecoder.decode(str, enc);
		} catch (UnsupportedEncodingException e) {
		}

		return strDecode;
	}

	public static boolean ignoreCaseEqual(String token1, String token2) {
		if (haveEmpty(token1, token2)) {
			return false;
		}
		return nullToStrTrim(token1).toLowerCase().equals(nullToStrTrim(token2).toLowerCase());
	}

	public static String byte2hex(byte[] b) {

		String str = "";
		String stmp = "";

		int length = b.length;

		for (int n = 0; n < length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				str += "0";
			}
			str += stmp;
		}

		return str.toLowerCase();
	}

	// 判断是否是中文
	public static boolean isChinese(String str) {
		String reg = "[\\u4e00-\\u9fa5]+";
		return str.matches(reg);
	}

	/**
	 * 判断微信格式
	 *
	 * @param wechat
	 * @return
	 */
	public static boolean checkWeChat(String wechat) {

		wechat = nullToStrTrim(wechat);

		String regex = "^[a-zA-Z0-9]{1}[-_a-zA-Z0-9]{5,20}+$";

		return wechat.matches(regex);
	}

	// 判断QQ号是否合法
	public static boolean isQQCorrect(String qq) {
		qq = nullToStrTrim(qq);
		String regex = "[1-9][0-9]{4,9}";
		return qq.matches(regex);
	}

	/**
	 * 转换为下划线
	 *
	 * @param camelCaseName
	 * @return
	 */
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 转换为驼峰
	 *
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

	// 判断是否是纯数字
	public static boolean isNumber(String str) {
		String reg = "[0-9]+";
		return str.matches(reg);
	}


	/**
	 * 将一个 1,3  逗号分隔的字符串变为List
	 *
	 * @param listStr
	 * @return
	 */
	public static List<String> arrStrToList(String listStr) {
		String[] split = listStr.split(",");
		return Arrays.asList(split);
	}


	/**
	 * 转26进制字母，同时不足6位自动补6
	 *
	 * @param data
	 * @return
	 */
	public static String to26Jinzhi(int data) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int scale = str.length(); // 转化目标进制
		StringBuilder s = new StringBuilder();
		if (data == 0) {
			return str.charAt(0) + "";
		}
		while (data > 0) {
			if (data < scale) {
				s.insert(0, str.charAt(data));
				data = 0;
			} else {
				int r = data % scale;
				s.insert(0, str.charAt(r));
				data = (data - r) / scale;
			}
		}
		return String.format("%6s", s.toString()).replace(" ", "6");
	}


	/**
	 * 获取富文本里的图片地址,字符串拼接结果
	 *
	 * @param str
	 * @return
	 */
	public static String getImgStrs(String str) {
		String url = "";
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		String img = "";
		while (true) {
			// 1.如果是截图
			int i = str.indexOf("src=\"http://qieinoa");
			int b = str.indexOf("png");
			if (i > 0 && b > 0) {
				img = str.substring(i + 5, b + 3);
				url += img + "$";
				str = str.replace("src=\"" + img + "\"", "");
				continue;
			}
			// 2.如果是黏贴图
			i = str.indexOf("src=\"http");
			if (i > 0) {
				str = str.substring(i + 5, str.length());
				b = str.indexOf(">");
				if (b > 0) {
					img = str.substring(0, b - 1);
					url += img + "$";
					str = str.replace(img + "\"", "");
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (url.endsWith("$")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}

	/**
	 * 获取富文本里的图片地址,字符串拼接结果,带<img/>标签
	 *
	 * @param str
	 * @return
	 */
	public static String getImgStrsWithImg(String str) {
		String url = "";
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		String img = "";
		while (true) {
			// 1.如果是截图
			int i = str.indexOf("src=\"http://qieinoa");
			int b = str.indexOf("png");
			if (i > 0 && b > 0) {
				img = str.substring(i + 5, b + 3);
				url += ("<img style=\"width: 30px; height: 30px;\" src=\"" + img + "\">" + "$");
				str = str.replace("src=\"" + img + "\"", "");
				continue;
			}
			// 2.如果是黏贴图
			i = str.indexOf("src=\"http");
			if (i > 0) {
				str = str.substring(i + 5, str.length());
				b = str.indexOf(">");
				if (b > 0) {
					img = str.substring(0, b - 1);
					url += ("<img style=\"width: 30px; height: 30px;\" src=\"" + img + "\">" + "$");
					str = str.replace(img + "\"", "");
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (url.endsWith("$")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}

	public static String decimalFormat(Double num) {

		DecimalFormat decimalFormat = new DecimalFormat("#0.00");

		return decimalFormat.format(num);
	}

	public static double decimalFormatDouble(Double num) {

		DecimalFormat decimalFormat = new DecimalFormat("#0.00");

		return Double.parseDouble(decimalFormat.format(num));
	}

	/**
	 * 根据时间生成随机码
	 * @return
	 */
	public static String generateSerialNumber(){
		StringBuilder buffer = new StringBuilder();
		String sysTimeSLong = TimeUtil.getSysTimeLong();
		String uuid = UUID.randomUUID().toString();
		StringBuilder append = buffer.append(sysTimeSLong).append(uuid);
		return append.toString();
	}


}