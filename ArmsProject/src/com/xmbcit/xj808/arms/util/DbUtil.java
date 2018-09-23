package com.xmbcit.xj808.arms.util;

/**
 * 数据库工具
 * @author Administrator
 *
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DbUtil  {

	// 数据库连接池对象
	private static BasicDataSource dbs = null;
	protected static Logger log = Logger.getLogger(DbUtil.class);
	static {
		dbs = new BasicDataSource();
		java.util.Properties ps = new java.util.Properties();
		//确定配置文件所在位置
		//File file = new File(
			//	"E:\\共享文件\\java\\xj808exam3\\ArmsProject\\src\\com\\xmbcit\\xj808\\arms\\util\\mysqlConfig.properties");
		try {
			//FileInputStream fis = new FileInputStream(file);
			ps.load(DbUtil.class.getResourceAsStream("mysqlConfig.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		//从配置文件中获取配置信息
		String driverClassName = ps.getProperty("driverClassName");
		String url = ps.getProperty("url");
		String username = ps.getProperty("username");
		String password = ps.getProperty("password");
		int initialSizeInt = Integer.parseInt(ps.getProperty("initialSize"));
		int maxActiveInt = Integer.parseInt(ps.getProperty("maxActive"));
		int minIdleInt = Integer.parseInt(ps.getProperty("minIdle"));
		int maxWaitInt = Integer.parseInt(ps.getProperty("maxWait"));
		
		dbs.setDriverClassName(driverClassName);// 设置驱动
		dbs.setUrl(url);// 设置URL
		dbs.setUsername(username);// 设置用户名
		dbs.setPassword(password);// 设置密码
		dbs.setInitialSize(initialSizeInt);// 设置初始化获取连接数
		dbs.setMaxActive(maxActiveInt);// 设置最大连接数 （mysql的最大连接数200）
		dbs.setMinIdle(minIdleInt);// 设置最小连接数
		dbs.setMaxWait(maxWaitInt);// 设置最大等待时间

	}

	

	/** 获得连接 */

	public static Connection getConnection() {

		Connection conn = null;

		try {
			conn = dbs.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取数据库连接异常");
		}

		return conn;

	}

	/** 关闭资源 */
	public static void closeR(Connection conn, PreparedStatement psm, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (psm != null) {
				psm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("数据库关闭异常");
		}

	}

	/** 关闭资源 */
	public static void closeR(Connection conn, PreparedStatement psm) {
		closeR(conn, psm, null);
	}

	/** 获取UUID */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 无法显示获得对象
	private DbUtil() {

	}

}
