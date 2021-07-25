package com.jizhong.datasource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jizhong.pojo.Student;
import com.jizhong.utils.JDBCUtils;

public class DruidDataSource02 {
	public static void main(String[] args) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try{
			//5. 通过数据库连接池对象的getConnection()方法获取连接
			connection = JDBCUtils.getConnection();
			//Statement：执行静态SQL语句
			//Statement statement = connection.createStatement();
			
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
//				int schoolId = resultSet.getInt("school_id");
				
				//封装数据到student对象中
				Student student = new Student();
				student.setId(id);
				student.setName(name);
				student.setAge(age);
				student.setBirthday(birthday);
//				student.setSchoolId(schoolId);
				System.out.println(student);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//8. 关闭资源
			JDBCUtils.close(resultSet,pStatement,connection);
		}
	}
}
