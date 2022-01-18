package com.sist.main;
// 오라클 연결 
import java.util.*;
import java.sql.*;
// JDBC => ORM(Spring) => MyBatis (XML,Annotation)
public class StudentDAO {
   // 연결 객체 => 멤버(한개만 사용이 가능) => 싱글턴
   private Connection conn;
   // 스프링 , MyBatis => 라이브러리 제작 
   // => 스프링구조를 만들어서 사이트 제작
   // SQL전송 객체 
   // JDBC => 네트워크 프로그램 (오라클(서버) <==> 응용프로그램(클라이언트))
   // 오라클로부터 응답 <====> 오라클이 인식하는 언어로 전송 (SQL)
   private PreparedStatement ps;
   // 오라클 연결 주소 
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   
   // 드라이버 등록 (한번만 생성) 
   public StudentDAO()
   {
	  try
	  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch(Exception ex) 
	  {
		  ex.printStackTrace(); // null => 주소 
	  }
   }
   // 연결 
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
		   // conn hr/happy
	   }catch(Exception ex){}
   }
   // 해제 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null)conn.close();
	   }catch(Exception ex) {}
   }
   // 기능 (INSERT , UPDATE , DELETE => 오라클 자체에서 처리) =>
   // 결과값이 없다 => 리턴형이 없다 
   // 기능 1 => 데이터 추가 INSERT
   public void studentInsert(StudentVO vo)
   {
	   // 한명의 학생 정보를 추가 : StudentVO(한명의 학생 전체 정보를 가지고 있다)
       try
       {
    	   // 1. 연결 
    	   getConnection();
    	   // 2. SQL문장 
    	   String sql="INSERT INTO student VALUES("
    			     +"(SELECT NVL(MAX(hakbun)+1,1) FROM student),"
    			     +"?,?,?,?)";
    	   // 3. SQL문장 전송 
    	   ps=conn.prepareStatement(sql);
    	   // 4. ?에 값을 채운다
    	   ps.setString(1, vo.getName());
    	   ps.setInt(2, vo.getKor());
    	   ps.setInt(3, vo.getEng());
    	   ps.setInt(4, vo.getMath());
    	   // 5. 실행요청 ==> executeUpdate() => commit이 포함되어 있다
    	   ps.executeUpdate();
    	   // ==> 자동 증가 번호 => 데이터가 없는 경우에는 NVL() => 디폴트가 지정 
    	   // 데이터 수집 
       }catch(Exception ex)
       {
    	   ex.printStackTrace();// 오류 처리 
       }
       finally
       {
    	   disConnection();//닫기
       }
   }
   // 기능 2 => 전체 출력  SELECT 
   // 한명 => StudentVO , 여러명 : List<StudentVO>
   /*
    *   오라클 
    *     테이블 => List 
    *     ROW  => VO
    */
   public List<StudentVO> studentListData()
   {
	   List<StudentVO> list=new ArrayList<StudentVO>();
	   try
	   {
		   // 1. 연결 
		   getConnection();
		   // 2. SQL문장 제작
		   String sql="SELECT * FROM student";
		   // 3. SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. 실행 결과값을 받는다
		   ResultSet rs=ps.executeQuery();
		   // 5. 결과값을 List에 담는다 
		   while(rs.next())
		   {
			   // 한명에 대한 정보를 저장 
			   StudentVO vo=new StudentVO();
			   vo.setHakbun(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setKor(rs.getInt(3));
			   vo.setEng(rs.getInt(4));
			   vo.setMath(rs.getInt(5));
			   
			   // 여러명을 묶어서 사용자에게 전송 
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
   // 기능 3 => 수정 (점수) UPDATE
   public void studentUpdate(StudentVO vo)
   {
	   try
	   {
		   // 1. 연결 
		   getConnection();
		   // 2. SQL문장 제작 
		   String sql="UPDATE student SET "
				     +"kor=?,eng=?,math=? "
				     +"WHERE hakbun=?";
		   // 3. SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. ?에 값을 채운다 
		   ps.setInt(1, vo.getKor());
		   ps.setInt(2, vo.getEng());
		   ps.setInt(3, vo.getMath());
		   ps.setInt(4, vo.getHakbun());
		   
		   // 5. SQL문장을 실행 
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
   // 기능 4 => 삭제  DELETE   
   public void studentDelete(int hakbun)
   {
	   try
	   {
		   // 1. 연결 
		   getConnection();
		   // 2. SQL문장 제작 
		   String sql="DELETE FROM student "
				     +"WHERE hakbun=?";
		   // 3. SQL문장 전송 
		   ps=conn.prepareStatement(sql);
		   // 4. ?에 값을 채운다 
		   ps.setInt(1,hakbun);
		   // 5. SQL문장 실행 
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









