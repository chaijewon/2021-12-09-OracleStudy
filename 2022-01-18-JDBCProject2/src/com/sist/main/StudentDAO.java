package com.sist.main;
// ����Ŭ ���� 
import java.util.*;
import java.sql.*;
// JDBC => ORM(Spring) => MyBatis (XML,Annotation)
public class StudentDAO {
   // ���� ��ü => ���(�Ѱ��� ����� ����) => �̱���
   private Connection conn;
   // ������ , MyBatis => ���̺귯�� ���� 
   // => ������������ ���� ����Ʈ ����
   // SQL���� ��ü 
   // JDBC => ��Ʈ��ũ ���α׷� (����Ŭ(����) <==> �������α׷�(Ŭ���̾�Ʈ))
   // ����Ŭ�κ��� ���� <====> ����Ŭ�� �ν��ϴ� ���� ���� (SQL)
   private PreparedStatement ps;
   // ����Ŭ ���� �ּ� 
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   
   // ����̹� ��� (�ѹ��� ����) 
   public StudentDAO()
   {
	  try
	  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch(Exception ex) 
	  {
		  ex.printStackTrace(); // null => �ּ� 
	  }
   }
   // ���� 
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   // conn hr/happy
	   }catch(Exception ex){}
   }
   // ���� 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null)conn.close();
	   }catch(Exception ex) {}
   }
   // ��� (INSERT , UPDATE , DELETE => ����Ŭ ��ü���� ó��) =>
   // ������� ���� => �������� ���� 
   // ��� 1 => ������ �߰� INSERT
   public void studentInsert(StudentVO vo)
   {
	   // �Ѹ��� �л� ������ �߰� : StudentVO(�Ѹ��� �л� ��ü ������ ������ �ִ�)
       try
       {
    	   // 1. ���� 
    	   getConnection();
    	   // 2. SQL���� 
    	   String sql="INSERT INTO student VALUES("
    			     +"(SELECT NVL(MAX(hakbun)+1,1) FROM student),"
    			     +"?,?,?,?)";
    	   // 3. SQL���� ���� 
    	   ps=conn.prepareStatement(sql);
    	   // 4. ?�� ���� ä���
    	   ps.setString(1, vo.getName());
    	   ps.setInt(2, vo.getKor());
    	   ps.setInt(3, vo.getEng());
    	   ps.setInt(4, vo.getMath());
    	   // 5. �����û ==> executeUpdate() => commit�� ���ԵǾ� �ִ�
    	   ps.executeUpdate();
    	   // ==> �ڵ� ���� ��ȣ => �����Ͱ� ���� ��쿡�� NVL() => ����Ʈ�� ���� 
    	   // ������ ���� 
       }catch(Exception ex)
       {
    	   ex.printStackTrace();// ���� ó�� 
       }
       finally
       {
    	   disConnection();//�ݱ�
       }
   }
   // ��� 2 => ��ü ���  SELECT 
   // �Ѹ� => StudentVO , ������ : List<StudentVO>
   /*
    *   ����Ŭ 
    *     ���̺� => List 
    *     ROW  => VO
    */
   public List<StudentVO> studentListData()
   {
	   List<StudentVO> list=new ArrayList<StudentVO>();
	   try
	   {
		   // 1. ���� 
		   getConnection();
		   // 2. SQL���� ����
		   String sql="SELECT * FROM student";
		   // 3. SQL���� ���� 
		   ps=conn.prepareStatement(sql);
		   // 4. ���� ������� �޴´�
		   ResultSet rs=ps.executeQuery();
		   // 5. ������� List�� ��´� 
		   while(rs.next())
		   {
			   // �Ѹ� ���� ������ ���� 
			   StudentVO vo=new StudentVO();
			   vo.setHakbun(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setKor(rs.getInt(3));
			   vo.setEng(rs.getInt(4));
			   vo.setMath(rs.getInt(5));
			   
			   // �������� ��� ����ڿ��� ���� 
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   // ��� 3 => ���� (����) UPDATE
   public void studentUpdate(StudentVO vo)
   {
	   try
	   {
		   // 1. ���� 
		   getConnection();
		   // 2. SQL���� ���� 
		   String sql="UPDATE student SET "
				     +"kor=?,eng=?,math=? "
				     +"WHERE hakbun=?";
		   // 3. SQL���� ���� 
		   ps=conn.prepareStatement(sql);
		   // 4. ?�� ���� ä��� 
		   ps.setInt(1, vo.getKor());
		   ps.setInt(2, vo.getEng());
		   ps.setInt(3, vo.getMath());
		   ps.setInt(4, vo.getHakbun());
		   
		   // 5. SQL������ ���� 
		   ps.executeUpdate();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   // ��� 4 => ����  DELETE   
   public void studentDelete(int hakbun)
   {
	   try
	   {
		   // 1. ���� 
		   getConnection();
		   // 2. SQL���� ���� 
		   String sql="DELETE FROM student "
				     +"WHERE hakbun=?";
		   // 3. SQL���� ���� 
		   ps=conn.prepareStatement(sql);
		   // 4. ?�� ���� ä��� 
		   ps.setInt(1,hakbun);
		   // 5. SQL���� ���� 
		   ps.executeUpdate();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
}









