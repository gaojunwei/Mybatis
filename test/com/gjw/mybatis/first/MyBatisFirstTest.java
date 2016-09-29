package com.gjw.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.gjw.po.User;

public class MyBatisFirstTest {
	
	SqlSession sqlSession = null;
	
	@Before
	public void initSqlSession() throws IOException {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建回话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过回话工厂得到SqlSession
		this.sqlSession = sessionFactory.openSession();
	}
	
	/**
	 * 通过id获取用户信息
	 * */
	@Test
	public void findUserById(){
		//通过SqlSession 操作数据库
		//User user = (User)sqlSession.selectOne("test.findUserById", 1);
		Map<String, Object> map = (Map<String, Object>)sqlSession.selectOne("test.findUserById", "3340");
		System.out.println("--->>>"+map.get("XU_ID"));
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 通过用户名模糊查询
	 * */
	@Test
	public void findUserByName(){
		List<Map<String, Object>> list = sqlSession.selectList("test.findUserByName", "hy");
		System.out.println(list.size());
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 插入数据
	 * */
	@Test
	public void insertUser(){
		User user = new User();
		user.setUserName("高天佑");
		user.setUserAge("25");
		user.setUserAddress("河南郑州");
		
		int a = sqlSession.insert("test.insertUser", user);
		
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。返回的自增主键值："+user.getId());
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 根据id删除用户
	 * */
	@Test
	public void deleteUser(){
		int a = sqlSession.update("test.deleteUser",1);
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。");
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 根据id修改用户信息
	 * */
	@Test
	public void updateUser(){
		User user = new User();
		user.setId(2);
		user.setUserName("高佳俊");
		user.setUserAge("28");
		user.setUserAddress("河北邢台");
		
		int a = sqlSession.update("test.updateUser",user);
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。");
		sqlSession.close();//关闭回话释放资源
	}
}
