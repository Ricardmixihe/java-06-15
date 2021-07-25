package com.jizhong.test;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jizhong.utils.JDBCUtils;

public class Test01 {
	//update()：执行DML语句。增、删、改语句
	private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
	
	//Junit：单元测试
	@Test
	public void inset(){
		/**
		 * ?：通配符
		 */
		String sql = "insert into student(id,name,age) values(?,?,?)";
		/**
		 * String sql：被执行的SQL语句
		 * Object... args：通配符传参
		 * 		可变参数，问号"?"有几个，参数就传递几个，与问号位置一一对应
		 */
		int count = jt.update(sql,16,"十六号",18);
		System.out.println("添加数据执行成功：" + count);
	}
	
	@Test
	public void update(){
		String sql = "update student set name = ? where id = ?";
		int count = jt.update(sql,"100号",16);
		System.out.println("修改数据执行成功：" + count);
	}
	
	@Test
	public void delete(){
		String sql = "delete from student where id = ?";
		int count = jt.update(sql,16);
		System.out.println("删除数据执行成功：" + count);
	}
	
	
}
