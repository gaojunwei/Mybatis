package com.gjw.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.gjw.po.User;

public class MyBatisFirst {
	/**
	 * ͨ��id��ȡ�û���Ϣ
	 * */
	private void findUserById() throws IOException{
		//mybatis�����ļ�
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����ػ�����
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ���ػ������õ�SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		//ͨ��SqlSession �������ݿ�
		User user = (User)sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user.getUserName());
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ͨ���û���ģ����ѯ
	 * */
	private void findUserByName() throws IOException{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sessionFactory.openSession();
		List<User> list = sqlSession.selectList("test.findUserByName", "��");
		System.out.println(list);
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ��������
	 * */
	private void insertUser() throws IOException{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sessionFactory.openSession();
		
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
	private void deleteUser() throws IOException{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sessionFactory.openSession();
		
		int a = sqlSession.update("test.deleteUser",1);
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С�");
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ����id�޸��û���Ϣ
	 * */
	private void updateUser() throws IOException{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sessionFactory.openSession();
		
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
	public static void main(String[] args) throws IOException {
		//new MyBatisFirst().findUserById();
		//new MyBatisFirst().findUserByName();
		//new MyBatisFirst().insertUser();
		//new MyBatisFirst().deleteUser();
		new MyBatisFirst().updateUser();
	}
}
