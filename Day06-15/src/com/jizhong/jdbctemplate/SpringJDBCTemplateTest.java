package com.jizhong.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jizhong.utils.JDBCUtils;

public class SpringJDBCTemplateTest {
	public static void main(String[] args) {
		//1. 导入jar包
		//2. 创建JdbcTemplate对象，依赖于数据源的DataSource
		JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
		//3. 执行SQL语句
		/**
		 	调用JdbcTemplate的方法来完成CRUD的操作
			* update()：执行DML语句。增、删、改语句
			* queryForMap()：查询结果，将结果集封装为map集合
			* queryForList()：查询结果，将结果集封装为list集合
			* query()：查询结果，将结果封装为JavaBean对象
			* queryForObject()：查询结果，将结果封装为对象（一般为基本数据类型对象）
		 */
		String sql = "insert into student(id,name,age) values(?,?,?)";
		/**
		 * String sql：被执行的SQL语句
		 * Object... args：通配符传参
		 */
		int count = jt.update(sql,16,"十六号",18);
		System.out.println(count);
		
//		JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
//		String sql = "insert into student(id,name,age) values(?,?,?)";
//		int count = jt.update(sql,16,"十六号",18);
		
//		System.out.println(count);
	}
}
