package com.xmbcit.xj808.arms.util;

import java.util.List;

/**
 * 通用工具类
 * @author Administrator
 *
 */
public class CommonUtil {

	/** 判空 */
	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	/** 判空 */
	public static boolean isNull(String target) {
		boolean result = false;
		if (target == null || "".equals(target)) {
			result = true;
		}
		return result;
	}

	/** 判空 */
	public static boolean isNull(List<?> target) {
		boolean result = false;
		if (target == null || target.size() < 0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * String[] 转 String
	 * 
	 * @param target
	 *            目标数组
	 * @param mark
	 *            分隔符号 ["a","bc","d"] "-"
	 * @return "a-bc-d"
	 */
	public static String arrayToString(String[] target, String mark) {
		StringBuilder strBuilder = new StringBuilder("");
		for (String str : target) {
			strBuilder.append(str);
			strBuilder.append(mark);
		}
		return strBuilder.length() > 1 ? strBuilder.substring(0, strBuilder.length() - 1) : "";
	}

	/**
	 * 数据类型转换<br/>
	 * A a = convert(obj,A.class);
	 * @param o 需要转化你的类型
	 * @param cls 类型.class
	 * @param <E>
	 * @return  转换成功返回E类型，失败返回null
	 */
	public static <E> E convert(Object o ,Class<E> cls){
		if(o==null){
			return null;
		}
		Class<?> c = o.getClass();
		if(c == cls){

		}
		try {
			if (c.asSubclass(cls) != null) {
				return (E)o;
			}
		}catch (Exception e){

		}
		return null;
	}

	/**
	 * 字符串转换为Float，如果str中包含字母不包含数字将返回0
	 * 例如:
	 * str   Float
	 * "a"    0
	 * "a1"   1
	 * ""
	 * @param str
	 * @return
	 */
	public static float toFloat(String str){
		if(isNull(str)){
			return 0;
		}else{
			str = str.replaceAll("[^\\d|-|\\.]","");
			if(isNull(str)){
				return 0;
			}else{
				try {
					return Float.parseFloat(str);
				}catch (NumberFormatException e){
					return 0;
				}

			}
		}
	}
	/**
	 * 字符串转换为int，如果str中包含字母不包含数字将返回0
	 * 例如:
	 * str   int
	 * "a"    0
	 * "a1"   1
	 * ""
	 * @param str
	 * @return
	 */
	public static int toInt(String str){
		if(isNull(str)){
			return 0;
		}else{
			str = str.replaceAll("[^\\d|-]","");
			if(isNull(str)){
				return 0;
			}else{
				return Integer.parseInt(str);
			}
		}
	}
	/**
	 * 字符串转换为Float，如果str中包含字母不包含数字将返回0
	 * 例如:
	 * str   double
	 * "a"    0
	 * "a1"   1
	 * ""
	 * @param str
	 * @return
	 */
	public static double toDouble(String str){
		if(isNull(str)){
			return 0;
		}else{
			str = str.replaceAll("[^\\d|-|\\.]","");
			if(isNull(str)){
				return 0;
			}else{
				try {
					return Double.parseDouble(str);
				}catch (NumberFormatException e){
					return 0;
				}
			}
		}
	}


	//无法显示构造对象
	private CommonUtil() {
		
	}

	public static <E> E getFirst(List<E> l){
		if(l!=null && !l.isEmpty()){
			return l.get(0);
		}
		return null;
	}
	
}
