package com.jizhong.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jizhong.pojo.Student;

public class DruidDataSource {
	public static void main(String[] args) throws Exception {
		//0. 通过Properties对象加载配置文件
		//1. 创建Properties对象
		Properties pro = new Properties();
		//2. 获取配置文件的字节输入流
		InputStream input = DruidDataSource.class.getClassLoader().getResourceAsStream("druid.properties");
		//3. Properties对象的load()方法来加载配置文件
		pro.load(input);
		
		//4. 通过DruidDataSourceFactory工厂类创建一个数据库连接池对象
		DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
		
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try{
			//5. 通过数据库连接池对象的getConnection()方法获取连接
			connection = dataSource.getConnection();
			//Statement：执行静态SQL语句
//			Statement statement = connection.createStatement();
			
			int i = 1;
			//6. 定义执行的SQL语句
			//?：表示通配符
			String sql = "select * from student where id = ?";
			//PreparedStatement：执行动态SQL语句
			pStatement = connection.prepareStatement(sql);
			/**
			 * parameterIndex：表示要为第几个问号"?"赋值
			 * x：值
			 */
			pStatement.setInt(1, i);
			//7. 执行SQL语句
			resultSet = pStatement.executeQuery();
			//next()：游标下移，判断是否还有数据
			//getXxx(column)：Xxx：表示获取列的数据类型 	column：表示获取的列名
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				Date birthday = resultSet.getDate("birthday");
				int schoolId = resultSet.getInt("school_id");
				
				
				//封装数据到student对象中
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//8. 关闭资源
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStatement != null) {
				pStatement.close();
			}
			if (connection != null) {
				connection.close();//归还连接
			}
		}
	}
}
