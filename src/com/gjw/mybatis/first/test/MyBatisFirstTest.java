package com.gjw.mybatis.first.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
		//mybatis�����ļ�
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����ػ�����
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ���ػ������õ�SqlSession
		this.sqlSession = sessionFactory.openSession();
	}
	
	/**
	 * ͨ��id��ȡ�û���Ϣ
	 * */
	@Test
	public void findUserById(){
		//ͨ��SqlSession �������ݿ�
		User user = (User)sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user.getUserName());
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ͨ���û���ģ����ѯ
	 * */
	@Test
	public void findUserByName(){
		List<User> list = sqlSession.selectList("test.findUserByName", "��");
		System.out.println(list);
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ��������
	 * */
	@Test
	public void insertUser(){
		User user = new User();
		user.setUserName("������");
		user.setUserAge("25");
		user.setUserAddress("����֣��");
		
		int a = sqlSession.insert("test.insertUser", user);
		
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С����ص���������ֵ��"+user.getId());
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ����idɾ���û�
	 * */
	@Test
	public void deleteUser(){
		int a = sqlSession.update("test.deleteUser",1);
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С�");
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ����id�޸��û���Ϣ
	 * */
	@Test
	public void updateUser(){
		User user = new User();
		user.setId(2);
		user.setUserName("�߼ѿ�");
		user.setUserAge("28");
		user.setUserAddress("�ӱ���̨");
		
		int a = sqlSession.update("test.updateUser",user);
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С�");
		sqlSession.close();//�رջػ��ͷ���Դ
	}
}
