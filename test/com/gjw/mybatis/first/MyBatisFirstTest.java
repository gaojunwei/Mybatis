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
		String con = " and xu_id=3340 ";
		Map<String, Object> map = (Map<String, Object>)sqlSession.selectOne("test.findUserById", con);
		System.out.println("--->>>"+map.toString());
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ͨ���û���ģ����ѯ
	 * */
	@Test
	public void findUserByName(){
		List<Map<String, Object>> list = sqlSession.selectList("test.findUserByName", "hy");
		System.out.println(list.size());
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ��������
	 * */
	@Test
	public void insertUser(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", "�߼ѿ�1");
		dataMap.put("userage", "2");
		dataMap.put("useraddress", "����");
		dataMap.put("value", "xd_user");
		
		int a = sqlSession.insert("test.insertUser", dataMap);
		
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С����ص���������ֵ��"+dataMap);
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ����idɾ���û�
	 * */
	@Test
	public void deleteUser(){
		String con = " and username='�߼ѿ�'";
		int a = sqlSession.update("test.deleteUser",con);
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С�");
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ����id�޸��û���Ϣ
	 * */
	@Test
	public void updateUser(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("username", "�ߴ���66");
		dataMap.put("userage", 25);
		dataMap.put("useraddress", "�ӱ�");
		dataMap.put("con", " and id=28896");
		
		int a = sqlSession.update("test.updateUser",dataMap);
		
		sqlSession.commit();//�ύ����
		System.out.println("Ӱ�� "+a+" �С�");
		sqlSession.close();//�رջػ��ͷ���Դ
	}
	/**
	 * ���ô洢����
	 * */
	@Test
	public void callProcedures(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("user_id", 28896);
		sqlSession.selectOne("test.callProcedures",dataMap);
		System.out.println(dataMap);
		sqlSession.close();//�رջػ��ͷ���Դ
	}
}
