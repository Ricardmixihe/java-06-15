package com.jizhong.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jizhong.pojo.Student;
import com.jizhong.utils.JDBCUtils;

public class Test02 {
	/**
		* queryForMap()：查询结果，将结果集封装为map集合(只能封装一条数据)
		* queryForList()：查询结果，将结果集封装为list集合
		* query()：查询结果，将结果封装为JavaBean对象
		* queryForObject()：查询结果，将结果封装为对象（一般为基本数据类型对象）
		*/
	private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Test
	public void queryForMap(){
		//queryForMap()：查询结果，将结果集封装为map集合(只能封装一条数据)
		String sql = "select * from student where id = ?";
		Map<String, Object> map = jt.queryForMap(sql,1);
		for (String key : map.keySet()) {//id  name age....
			System.out.println(key + "：" + map.get(key));
		}
	}
	
	//queryForList()：查询结果，将结果集封装为list集合
	//List集合的泛型为Map集合
	@Test
	public void queryForList(){
		String sql = "select * from student";
		List<Map<String, Object>> list = jt.queryForList(sql);
		//遍历list集合
		for (Map<String, Object> map : list) {
			//遍历map集合
			for (String key : map.keySet()) {
				System.out.println(key + "：" + map.get(key));
			}
		}
	}
	
	//query()：查询结果，将结果封装为JavaBean对象
	@Test
	public void query(){
		String sql = "select * from student";
		/**
		 * String sql：
		 * RowMapper rowmapper：封装对象的方式
		 * 		new BeanPropertyRowMapper<数据类型>(数据类型.class)
		 */
		List<Student> students = jt.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	//queryForObject()：查询结果，将结果封装为对象（一般为基本数据类型对象）
	@Test
	public void queryForObject(){
		//返回查询总条数
		String sql = "select count(*) from student";
		/**
		 * Integer.class：返回值类型
		 */
		int count = jt.queryForObject(sql,Integer.class);
		System.out.println(count);
	}
	
	
	
	
	
	
}
