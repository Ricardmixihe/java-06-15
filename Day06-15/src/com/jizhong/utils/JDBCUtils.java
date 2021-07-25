package com.jizhong.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jizhong.datasource.DruidDataSource;

public class JDBCUtils {
	private static DataSource dataSource;
	
	static {
		try{
			//0. 通过Properties对象加载配置文件
			//1. 创建Properties对象
			Properties pro = new Properties();
			//2. 获取配置文件的字节输入流
			InputStream input = DruidDataSource.class.getClassLoader().getResourceAsStream("druid.properties");
			//3. Properties对象的load()方法来加载配置文件
			pro.load(input);
			
			//4. 通过DruidDataSourceFactory工厂类创建一个数据库连接池对象
			dataSource = DruidDataSourceFactory.createDataSource(pro);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//获取连接池对象
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	//获取连接
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//归还连接（更新数据关闭资源）
	public static void close(Statement statement,Connection connection){
//		if(statement != null){
//			try{
//				statement.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			
//		}
//		
//		if(connection != null){
//			try{
//				connection.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			
//		}
		close(null,statement,connection);
	}
	
	//归还连接（查询数据关闭资源）
	public static void close(ResultSet resultSet,Statement statement,Connection connection){
		if(resultSet != null){
			try{
				resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if(statement != null){
			try{
				statement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if(connection != null){
			try{
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
