package com.sist.main;
import java.sql.*;

import oracle.jdbc.OracleTypes;
// 11g (ojdbc6.jar) , 12c~21c (ojdbc8.jar)
public class EmpDAO {
    // 연결 객체 
	private Connection conn;
	// 프로시저 호출 
	private CallableStatement cs;
	// 주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. 드라이버 등록 
	public EmpDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 2. 오라클 연결 
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// conn hr/happy
		}catch(Exception ex) {}
	}
	// 3. 오라클 연결 종료 
	public void disConnection()
	{
		try
		{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
			// exit 
		}catch(Exception ex) {}
	}
	// 4. 프로시저 호출  ==> SQL문장을 노출하지 않는다 
	/*
	 *   CREATE OR REPLACE PROCEDURE empAllData(
             pResult OUT SYS_REFCURSOR
         )
	 */
	public void empAllData()
	{
		try
		{
			//1. 연결 
			getConnection();
			//2. SQL문장 
			String sql="{CALL empAllData(?)}";
			cs=conn.prepareCall(sql);// 프로시저 호출 
			// EXECUTE empAllData()
			//3. ?에 값을 채운다 
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			//                      ---- 오라클 데이터형 설정 
			//4. 실행 
			cs.executeUpdate();
			//5. 값을 받는다 
			// empno,ename,job,hiredate,sal,dname,loc,grade
			ResultSet rs=(ResultSet)cs.getObject(1);
			// CURSOR => ResultSet 
			// VARCHAR2 => String
			// NUMBER   => int ,double 
			// DATE     => java.util.Date
			while(rs.next())
			{
				System.out.println(
				   rs.getInt(1)+" "
				   +rs.getString(2)+" "
				   +rs.getString(3)+" "
				   +rs.getDate(4).toString()+" "
				   +rs.getInt(5)+" "
				   +rs.getString(6)+" "
				   +rs.getString(7)+" "
				   +rs.getInt(8)
				);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();//오류처리 
		}
		finally
		{
			disConnection();// 닫기
		}
	}
}






