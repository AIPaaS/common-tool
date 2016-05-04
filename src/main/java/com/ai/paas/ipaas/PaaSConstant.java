package com.ai.paas.ipaas;

public class PaaSConstant {

	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String CHARSET_GBK = "GBK";
	public static final String CHARSET_GBK18030 = "GB18030";
	public static final String UNIX_SEPERATOR = "/";
	public static final int ERROR_RESULT = -1;
	
	/**
	 * 远程调用成功
	 */
	public static final String RPC_CALL_OK = "000000";

	public static final class ExceptionCode {
		// 系统级异常[其它系统级异常，未知异常]900000-999999系统异常
		public static final String SYSTEM_ERROR = "900000";
		/**
		 * 传入的{0}参数为空
		 */
		public static final String PARAM_IS_NULL = "910000";

		/**
		 * 参数类型不正确{0}
		 */
		public static final String PARAM_TYPE_NOT_RIGHT = "910010";

		// 未配置系统参数或未刷新缓存
		public static final String NO_DATA_OR_CACHE_ERROR = "910020";

		// 未查询到记录[查询无记录]
		public static final String NO_RESULT = "920000";

		/**
		 * 用户异常1000000-199999
		 */
		public static final String USER_AUTH_ERROR = "100000";

	}

	/**
	 * JAVA日期格式
	 */
	public static final String DATETIME_JAVA_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * java日期格式
	 */
	public static final String DATE_JAVA_FORMAT = "yyyy-MM-dd";

	/**
	 * java日期格式--仅年月
	 */
	public static final String DATE_JAVA_YEAR_MONTH_FORMAT = "yyyy-MM";

	/**
	 * java日期格式-仅显示年
	 */
	public static final String DATE_JAVA_YEAR_FORMAT = "yyyy";

	/**
	 * java日期格式--精确到分
	 */
	public static final String DATE_JAVA_YEAR_MONTH_HOUR_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * oracle 日期格式带时间
	 */
	public static final String DATETIME_ORACLE_FORMAT = "YYYY-MM-DD HH24:MI:SS";

	/**
	 * Oracle日期格式，不带时间
	 */
	public static final String DATE_ORACLE_FORMAT = "YYYY-MM-DD";
	/**
	 * 文件名日期格式yyyyMMdd
	 */
	public static final String DATE_FILE_FORMAT = "yyyyMMdd";
	/**
	 * 文件名日期格式yyyyMM
	 */
	public static final String DATE_FILE_FORMAT_SHORT = "yyyyMM";

	/**
	 * hp接口的日期格式
	 */
	public static final String DATETIME_ALL_FORMAT = "yyyyMMddHHmmss";

	/**
	 * 当前操作系统换行符
	 */
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	/**
	 * TAB
	 */
	public static final String TAB = "\t";
}
