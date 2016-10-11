package com.gjw.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
		String con = " and xu_id=3340 ";
		Map<String, Object> map = (Map<String, Object>)sqlSession.selectOne("test.findUserById", con);
		System.out.println("--->>>"+map.toString());
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
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", "高佳俊1");
		dataMap.put("userage", "2");
		dataMap.put("useraddress", "北京");
		dataMap.put("value", "xd_user");
		
		int a = sqlSession.insert("test.insertUser", dataMap);
		
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。返回的自增主键值："+dataMap);
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 根据id删除用户
	 * */
	@Test
	public void deleteUser(){
		String con = " and username='高佳俊'";
		int a = sqlSession.update("test.deleteUser",con);
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。");
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 根据id修改用户信息
	 * */
	@Test
	public void updateUser(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", "高大侠66");
		dataMap.put("userage", 25);
		dataMap.put("useraddress", "河北");
		dataMap.put("con", " and id=28896");
		
		int a = sqlSession.update("test.updateUser",dataMap);
		
		sqlSession.commit();//提交事物
		System.out.println("影响 "+a+" 行。");
		sqlSession.close();//关闭回话释放资源
	}
	/**
	 * 调用存储过程
	 * */
	@Test
	public void callProcedures(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("user_id", 28896);
		sqlSession.selectOne("test.callProcedures",dataMap);
		System.out.println(dataMap);
		sqlSession.close();//关闭回话释放资源
	}
}
